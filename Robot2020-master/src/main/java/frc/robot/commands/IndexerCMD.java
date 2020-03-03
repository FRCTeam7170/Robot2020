package frc.robot.commands;

import frc.robot.subsystems.IndexerSUB;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexerCMD extends CommandBase {
	private final IndexerSUB m_indexer;

	public IndexerCMD(final IndexerSUB indexer) {
		m_indexer = indexer;
		addRequirements(m_indexer);
	}

	public void initialize() {
	    //m_indexer.setRPM(500);
	}

	public void execute() {
        //m_indexer.setIndexer();
        m_indexer.bangbang();
	}

	public boolean isFinished() {
		return false;
	}
}