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
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Indexer;

public class RamseteShoot {
    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-spooky");
	private final DriveBase m_driveBase;
	private final FlyWheel m_flyWheel;
	private final Indexer m_indexer;
	private double tx, ty, r, angle;

	public RamseteShoot(DriveBase driveBase, FlyWheel flyWheel, Indexer indexer) {
		m_driveBase = driveBase;
		m_flyWheel = flyWheel;
		m_indexer = indexer;
	}

	public Command getAutoCommand() {
        table.getEntry("led").setNumber(3);
        tx = table.getEntry("tx").getDouble(0.0);
        ty = table.getEntry("ty").getDouble(0.0);
		table.getEntry("led").setNumber(1);
		
		if (Math.abs(tx) >= 1){
			//to get the radius we have to know the mounting angle and height of the limelight 
			r = (Constants.Measurements.TARGET_HEIGHT-Constants.Measurements.LIMELIGHT_HEIGHT)/Math.tan(Constants.Measurements.LIMELIGHT_ANGLE+ty);
			angle = 2*Math.PI*r*(tx/360);

			var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
					new SimpleMotorFeedforward(Constants.Measurements.SVOLTS, Constants.Measurements.SVOLT_SECOND_PER_METER,
							Constants.Measurements.SVOLT_SECOND_PER_METER_SQUARED),
					Constants.Measurements.DRIVE_KINEMATICS, 10);

			// Create config for trajectory
			TrajectoryConfig config = new TrajectoryConfig(Constants.Measurements.MAX_SPEED,
					Constants.Measurements.MAX_ACCELERATION)
							// Add kinematics to ensure max speed is actually obeyed
							.setKinematics(Constants.Measurements.DRIVE_KINEMATICS);
							// Apply the voltage constraint
							//.addConstraint(autoVoltageConstraint);

			Trajectory Trajectroy = TrajectoryGenerator.generateTrajectory(
					// Start at the origin facing the +X direction
					new Pose2d(0, 0, new Rotation2d(0)),
					// Pass through waypoints
					List.of(),
					// End position and angle
					new Pose2d(0, 0, new Rotation2d(angle)),
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

			return ramseteCommand.andThen(() -> m_driveBase.tankDriveVolts(0, 0))
			.andThen(() -> new FlyWheelSpin(m_flyWheel).alongWith(new LoadBall(m_indexer)
			.andThen(new WaitCommand(1).andThen(() -> m_flyWheel.stop(), m_flyWheel))));
		} else {
			return new FlyWheelSpin(m_flyWheel).alongWith(new LoadBall(m_indexer)
					.andThen(new WaitCommand(1).andThen(() -> m_flyWheel.stop(), m_flyWheel)));
		}
	}
}
