package frc.robot.commands;

import frc.robot.subsystems.IndexerSUB;
import frc.robot.subsystems.FlyWheelSUB;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoShootCMD extends CommandBase {
	private final FlyWheelSUB m_flyWheel;
	private final IndexerSUB m_indexer;
	private final Timer m_timer = new Timer();

	public AutoShootCMD(final FlyWheelSUB flyWheel, final IndexerSUB indexer) {
		m_flyWheel = flyWheel;
		m_indexer = indexer;
		addRequirements(m_flyWheel, m_indexer);
	}

	public void initialize() {
		m_flyWheel.setRPM(6650);
		m_timer.start();
	}

	public void execute() {
		m_flyWheel.setFlyWheel();
		if (m_flyWheel.getSpeed() >= 80) {
			m_indexer.bangbang();
		}
	}

	public boolean isFinished() {
		return m_timer.hasElapsed(10);
	}
}