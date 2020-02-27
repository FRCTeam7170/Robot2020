package frc.robot.commands;

import java.util.List;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBaseSUB;
import frc.robot.subsystems.FlyWheelSUB;
import frc.robot.subsystems.IndexerSUB;

public class RamseteShootCMD {
	private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-spooky");
	private final DriveBaseSUB m_driveBase;
	private final FlyWheelSUB m_flyWheel;
	private final IndexerSUB m_indexer;
	private double ty, r, move;

	public RamseteShootCMD(DriveBaseSUB driveBase, FlyWheelSUB flyWheel, IndexerSUB indexer) {
		m_driveBase = driveBase;
		m_flyWheel = flyWheel;
		m_indexer = indexer;

	}

	public Command getAutoCommand() {
		ty = table.getEntry("ty").getDouble(0.0);
		table.getEntry("led").setNumber(1);

		// to get the radius we have to know the mounting angle and height of the
		// limelight
		r = (Constants.Measurements.TARGET_HEIGHT - Constants.Measurements.LIMELIGHT_HEIGHT)
				/ Math.tan(Constants.Measurements.LIMELIGHT_ANGLE + ty);
		move = Constants.Measurements.SHOOTING_RANGE - r;

		var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
				new SimpleMotorFeedforward(Constants.Measurements.SVOLTS, Constants.Measurements.SVOLT_SECOND_PER_METER,
						Constants.Measurements.SVOLT_SECOND_PER_METER_SQUARED),
				Constants.Measurements.DRIVE_KINEMATICS, 10);

		// Create config for trajectory
		TrajectoryConfig config = new TrajectoryConfig(Constants.Measurements.MAX_SPEED,
				Constants.Measurements.MAX_ACCELERATION)
						// Add kinematics to ensure max speed is actually obeyed
						.setKinematics(Constants.Measurements.DRIVE_KINEMATICS)
						// Apply the voltage constraint
						.addConstraint(autoVoltageConstraint);

		Trajectory Trajectroy = TrajectoryGenerator.generateTrajectory(
				// Start at the origin facing the +X direction
				new Pose2d(0, 0, new Rotation2d(0)),
				// Pass through waypoints
				List.of(),
				// End position and angle
				new Pose2d(move, 0, new Rotation2d(0)),
				// Pass config
				config);

		RamseteCommand ramseteCommand = new RamseteCommand(Trajectroy, m_driveBase::getPose,
				new RamseteController(Constants.Measurements.RAMSETE_B, Constants.Measurements.RAMSETE_ZETA),
				new SimpleMotorFeedforward(Constants.Measurements.SVOLTS, Constants.Measurements.SVOLT_SECOND_PER_METER,
						Constants.Measurements.SVOLT_SECOND_PER_METER_SQUARED),
				Constants.Measurements.DRIVE_KINEMATICS, m_driveBase::getWheelSpeeds,
				new PIDController(Constants.Measurements.KP_DRIVE_VELOCITY, 0, 0),
				new PIDController(Constants.Measurements.KP_DRIVE_VELOCITY, 0, 0),
				// RamseteCommand passes volts to the callback
				m_driveBase::tankDriveVolts, m_driveBase);

		return new TurnOnSpotCMD(m_driveBase).andThen(ramseteCommand.andThen(() -> m_driveBase.tankDriveVolts(0, 0))
				.andThen(() -> new FlyWheelCMD(m_flyWheel).alongWith(new LoadBallCMD(m_indexer)
						.andThen(new WaitCommand(1).andThen(() -> m_flyWheel.stop(), m_flyWheel)))));
	}
}
