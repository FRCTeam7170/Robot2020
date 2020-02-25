package frc.robot.commands;

import frc.robot.subsystems.FlyWheelSUB;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FlyWheelCMD extends CommandBase {
	private final FlyWheelSUB m_flyWheel;

	public FlyWheelCMD(final FlyWheelSUB flyWheel) {
		m_flyWheel = flyWheel;
		addRequirements(m_flyWheel);
	}

	public void initialize() {
		m_flyWheel.setRPM(6000);
	}

	public void execute() {
		m_flyWheel.setFlyWheel();
	}

	public boolean isFinished() {
		return false;
	}

	public void stop() {
		m_flyWheel.stop();
	}
}
