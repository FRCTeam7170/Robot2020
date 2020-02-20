package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.IntakeLift;
import frc.robot.subsystems.IntakeWheel;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Intake extends CommandBase {
	private final IntakeLift m_intakeLift;
	private final IntakeWheel m_intakeWheel;
	private final DriveBase m_driveBase;
	private boolean up = true;
	private double speed;
	private double driveBaseSpeed;

	public Intake(final IntakeLift intakeLift, final IntakeWheel intakeWheel, final DriveBase driveBase) {
		m_intakeLift = intakeLift;
		m_intakeWheel = intakeWheel;
		m_driveBase = driveBase;
		addRequirements(m_intakeLift, m_intakeWheel, m_driveBase);
	}

	public void initialize() {
		driveBaseSpeed = m_driveBase.getAvgSpeed(); 
		if (driveBaseSpeed <= Constants.Measurements.MIN_DRIVEBASE_SPEED){
			speed = Constants.Measurements.MIN_DRIVEBASE_SPEED * 12;
		} else {
			speed = driveBaseSpeed * 12;
		}
		up = !up;
		if (up) {
			m_intakeLift.up();
			m_intakeWheel.off();
		} else {
			m_intakeLift.down();
			m_intakeWheel.on(speed);
		}
	}

	public boolean isFinished() {
		return false;
	}
}
