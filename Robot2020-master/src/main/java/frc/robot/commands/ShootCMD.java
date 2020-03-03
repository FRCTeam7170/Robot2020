package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlyWheelSUB;
import frc.robot.subsystems.IndexerSUB;

public class ShootCMD extends CommandBase{
    private final FlyWheelSUB m_flyWheel;
    private final IndexerSUB m_indexer;
    private final Timer m_timer = new Timer();

    public ShootCMD(final FlyWheelSUB flyWheel, final IndexerSUB indexer){
        m_flyWheel = flyWheel;
        m_indexer = indexer;
        addRequirements(m_flyWheel, m_indexer);
    }

    public void initialize(){
        m_flyWheel.setRPM(6650);
        m_timer.start();
    }
    public void execute(){
        m_flyWheel.setFlyWheel();
        System.out.println(m_flyWheel.getSpeed());
        if (m_flyWheel.getSpeed() >= 80){
            m_indexer.bangbang();
        }
    }
    public boolean isFinished(){
        return m_timer.hasElapsed(14.);
    }
}