package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class LoadBall extends CommandBase{
    private final Indexer m_indexer;
    public LoadBall(Indexer indexer){
        m_indexer = indexer;
        addRequirements(m_indexer);
    }
    public void initialize(){
        m_indexer.stop();
    }
    public void periodic(){
        
    }
}