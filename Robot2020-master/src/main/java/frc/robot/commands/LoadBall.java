package frc.robot.commands;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class LoadBall extends CommandBase{
    private final Ultrasonic m_sensor = new Ultrasonic(0, 1); 
    private final Indexer m_indexer;
    private final double treshold = 4.8;
    private double initDist;
    private double distance;

    public LoadBall(final Indexer indexer) {
        m_indexer = indexer;
        addRequirements(m_indexer);
    }
    public void initialize(){
        m_sensor.setEnabled(true);
        m_indexer.setRPM(500);
        initDist = m_sensor.getRangeInches();
    }
    public void execute(){
        while (initDist - distance >= treshold){
            m_indexer.setIndexer();
            distance = m_sensor.getRangeInches();
        }
    }
    public boolean isFinished(){
        return true;
    }
    public void end(){
        m_indexer.stop();
        m_sensor.setEnabled(false);
    }
}
