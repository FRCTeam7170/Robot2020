package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Hang;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Climb extends CommandBase{

    public final Hang m_climbing;
    private final XboxController m_xboxController;
    private double teleUp;
    private double teleDown;

    public Climb(final Hang climbing) {
        m_climbing = climbing;
        m_xboxController = new XboxController(Constants.Controller.CONTROLLER_PORT);
    }

    public void execute(){
        teleUp = m_xboxController.getTriggerAxis(Hand.kRight);
        teleDown = m_xboxController.getTriggerAxis(Hand.kLeft);
        if (teleUp != 0.0){
            m_climbing.TeleUp(teleUp);
        }
        if (teleDown != 0.0){
            m_climbing.TeleDown(teleDown);
        }
        if (m_xboxController.getBumperPressed(Hand.kRight)){
            m_climbing.ClimbUp();
        }
        if (m_xboxController.getBumperPressed(Hand.kLeft)){
            m_climbing.ClimbDown();
        }
    }
}
