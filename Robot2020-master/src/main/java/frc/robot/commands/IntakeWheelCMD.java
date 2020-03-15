package frc.robot.commands;

import frc.robot.subsystems.IntakeLiftSUB;
import frc.robot.subsystems.IntakeWheelSUB;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeWheelCMD extends CommandBase {
	private final IntakeWheelSUB m_intakeWheel;

	public IntakeWheelCMD(final IntakeWheelSUB intakeWheel) {
		m_intakeWheel = intakeWheel;
		addRequirements(m_intakeWheel);
	}

	public void initialize() {
	}

	public void execute() {
		if (IntakeLiftSUB.getState()) {
			m_intakeWheel.bangbang();
		} else {
			m_intakeWheel.stop();
		}
	}
}