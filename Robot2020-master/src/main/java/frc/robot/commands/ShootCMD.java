package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlyWheelSUB;
import frc.robot.subsystems.IndexerSUB;
import frc.robot.subsystems.IntakeLiftSUB;

public class ShootCMD extends CommandBase {
	private final XboxController m_xboxController;
	private final FlyWheelSUB m_flyWheel;
	private final IndexerSUB m_indexer;
	private final IntakeLiftSUB m_intakeLift;

	public ShootCMD(final FlyWheelSUB flyWheel, final IndexerSUB indexer, final IntakeLiftSUB intakeLift,
			final XboxController xboxController) {
		m_xboxController = xboxController;
		m_intakeLift = intakeLift;
		m_flyWheel = flyWheel;
		m_indexer = indexer;
		addRequirements(m_flyWheel, m_indexer, m_intakeLift);
	}

	public void initialize() {
		m_intakeLift.up();
		m_flyWheel.setRPM(6300);
	}

	public void execute() {
		m_flyWheel.setFlyWheel();
		System.out.println(m_flyWheel.getSpeed());
		if (m_flyWheel.getSpeed() >= 75) {
			m_indexer.bangbang();
		} else {
			m_indexer.stop();
		}
	}

	public boolean isFinished() {
		return !m_xboxController.getBButton();
	}
}