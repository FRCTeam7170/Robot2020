package frc.robot.commands;

import frc.robot.subsystems.IntakeLiftSUB;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeLiftCMD extends CommandBase {
	private final IntakeLiftSUB m_intakeLift;
	private boolean up = true;
	private boolean done;

	public IntakeLiftCMD(final IntakeLiftSUB intakeLift) {
		m_intakeLift = intakeLift;
		addRequirements(m_intakeLift);
	}

	public void initialize() {
		done = false;
		up = !up;
	}

	public void execute(){
		if (up) {
			m_intakeLift.up();
			done = true;
		} else {
			m_intakeLift.down();
			done = true;
		}
	}

	public boolean isFinished() {
		return done;
	}
}
