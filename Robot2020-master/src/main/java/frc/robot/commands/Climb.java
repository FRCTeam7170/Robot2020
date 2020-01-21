package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Hang;
import edu.wpi.first.wpilibj.XboxController;

public class Climb extends CommandBase{

    public final Hang m_climbing;
    private XboxController m_xboxController;

    public Climb(Hang climbing) {
        m_climbing = climbing;
        m_xboxController = new XboxController(Constants.Controller.CONTROLLER_PORT);
    }

    public void execute(){
        if (m_xboxController.getBButtonPressed()){
            m_climbing.RaiseLiftArm();
        }

        if (m_xboxController.getYButtonPressed()){
            m_climbing.LowerLiftArm();
        }

        if (m_xboxController.getXButtonPressed()){
            m_climbing.Climb();
        }
    }
}
