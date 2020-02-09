package frc.robot.commands;

import java.util.List;

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
import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;

public class RamseteDrive {
	private final DriveBase m_driveBase = new DriveBase();

	public RamseteDrive() {
	}

	public Command getAutoCommand() {
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

		// An example trajectory to follow. All units in meters.
		Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
				// Start at the origin facing the +X direction
				new Pose2d(0, 0, new Rotation2d(0)),
				// Pass through these two interior waypoints, making an 's' curve path
				List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
				// End 3 meters straight ahead of where we started, facing forward
				new Pose2d(3, 0, new Rotation2d(0)),
				// Pass config
				config);

		RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, m_driveBase::getPose,
				new RamseteController(Constants.Measurements.RAMSETE_B, Constants.Measurements.RAMSETE_ZETA),
				new SimpleMotorFeedforward(Constants.Measurements.SVOLTS, Constants.Measurements.SVOLT_SECOND_PER_METER,
						Constants.Measurements.SVOLT_SECOND_PER_METER_SQUARED),
				Constants.Measurements.DRIVE_KINEMATICS, m_driveBase::getWheelSpeeds,
				new PIDController(Constants.Measurements.KP_DRIVE_VELOCITY, 0, 0),
				new PIDController(Constants.Measurements.KP_DRIVE_VELOCITY, 0, 0),
				// RamseteCommand passes volts to the callback
				m_driveBase::tankDriveVolts, m_driveBase);

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> m_driveBase.tankDriveVolts(0, 0));
	}
}
