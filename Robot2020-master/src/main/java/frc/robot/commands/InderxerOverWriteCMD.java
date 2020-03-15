package frc.robot.commands;

import frc.robot.subsystems.IndexerSUB;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class InderxerOverWriteCMD extends CommandBase {
	private final IndexerSUB m_indexer;
	private final XboxController m_xboxController;

	public InderxerOverWriteCMD(final IndexerSUB indexer, final XboxController xboxController) {
		m_indexer = indexer;
		m_xboxController = xboxController;
		addRequirements(m_indexer);
	}

	public void execute() {
		m_indexer.bangbang();
	}

	public boolean isFinished() {
		return !m_xboxController.getBumper(Hand.kLeft);
	}
}