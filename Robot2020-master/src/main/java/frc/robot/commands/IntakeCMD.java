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
	private boolean done;
	private double speed;
	private double driveBaseSpeed;

	public IntakeCMD(final IntakeLiftSUB intakeLift, final IntakeWheelSUB intakeWheel, final DriveBaseSUB driveBase) {
		m_intakeLift = intakeLift;
		m_intakeWheel = intakeWheel;
		m_driveBase = driveBase;
		addRequirements(m_intakeLift, m_intakeWheel, m_driveBase);
	}

	public void initialize() {
		done = false;
		driveBaseSpeed = m_driveBase.getAvgSpeed(); 
		if (driveBaseSpeed <= Constants.Measurements.MIN_DRIVEBASE_SPEED){
			speed = Constants.Measurements.MIN_DRIVEBASE_SPEED * 12;
		} else {
			speed = driveBaseSpeed * 12;
		}
		up = !up;
	}

	public void execute(){
		if (up) {
			m_intakeLift.up();
			System.out.println("ja");
			//m_intakeWheel.off();
			done = true;
		} else {
			m_intakeLift.down();
			System.out.println("nein");
			//m_intakeWheel.on(speed);
			done = true;
		}
	}

	public boolean isFinished() {
		return done;
	}
}
