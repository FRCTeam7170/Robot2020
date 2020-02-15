package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class LoadBall extends CommandBase {
	private final Ultrasonic m_sensor = new Ultrasonic(new DigitalOutput(0), new DigitalInput(1));
	private final Indexer m_indexer;
	private final double treshold = 5;
	private double distance = 0;

	public LoadBall(final Indexer indexer) {
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
