package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveBaseSUB;
import frc.robot.subsystems.IntakeLiftSUB;
import frc.robot.subsystems.IntakeWheelSUB;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeCMD extends CommandBase {
	private final IntakeLiftSUB m_intakeLift;
	private final IntakeWheelSUB m_intakeWheel;
	private final DriveBaseSUB m_driveBase;
	private boolean up = true;
	private double speed;
	private double driveBaseSpeed;

	public IntakeCMD(final IntakeLiftSUB intakeLift, final IntakeWheelSUB intakeWheel, final DriveBaseSUB driveBase) {
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
