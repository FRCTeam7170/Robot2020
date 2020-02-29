package frc.robot.commands;

import frc.robot.subsystems.IndexerSUB;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LoadBallCMD extends CommandBase {
	private double distance = 0;
	private final IndexerSUB m_indexer;
	private final double treshold = 5;
	private final Ultrasonic m_sensor = new Ultrasonic(new DigitalOutput(0), new DigitalInput(1));

	public LoadBallCMD(final IndexerSUB indexer) {
		m_indexer = indexer;
		addRequirements(m_indexer);
	}

	public void initialize() {
		m_sensor.setEnabled(true);
		m_sensor.setAutomaticMode(true);
		m_indexer.setRPM(500);
	}

	public void execute() {
		m_indexer.setIndexer();
	}

	public boolean isFinished() {
		distance = m_sensor.getRangeMM();
		System.out.println(distance);
		return distance <= treshold;
	}

	public void end() {
		m_indexer.stop();
		m_sensor.setEnabled(false);
	}
}
