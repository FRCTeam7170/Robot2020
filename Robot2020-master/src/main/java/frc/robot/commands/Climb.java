package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hang;

public class Climb extends CommandBase{
    public final Hang m_climbing;

    public Climb(Hang climbing) {
        m_climbing = climbing;
    }

    public void execute(){
        
    }
}
