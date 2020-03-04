package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlyWheelSUB;
import frc.robot.subsystems.IndexerSUB;

public class ShootCMD extends CommandBase{
    private final XboxController m_xboxController;
    private final FlyWheelSUB m_flyWheel;
    private final IndexerSUB m_indexer;

    public ShootCMD(final FlyWheelSUB flyWheel, final IndexerSUB indexer, final XboxController xboxController){
        m_xboxController = xboxController;
        m_flyWheel = flyWheel;
        m_indexer = indexer;
        addRequirements(m_flyWheel, m_indexer);
    }

    public void initialize(){
        m_flyWheel.setRPM(6650);
    }
    public void execute(){
        m_flyWheel.setFlyWheel();
        if (m_flyWheel.getSpeed() >= 80){
            m_indexer.bangbang();
        }
    }
    public boolean isFinished(){
        return !m_xboxController.getBButton();
    }
}