package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.TelescopeSUB;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TelescopeCMD extends CommandBase {

	public final TelescopeSUB m_telescopeSUB;
	private final XboxController m_xboxController;
	private final double maxCount = 5000; 
	private double teleUp;
	private double teleDown;

	public TelescopeCMD(final TelescopeSUB telescopeSUB) {
		m_telescopeSUB = telescopeSUB;
		m_xboxController = new XboxController(Constants.Controller.CONTROLLER_PORT);
	}
    public void execute(){
        teleUp = m_xboxController.getTriggerAxis(Hand.kRight);
        teleDown = m_xboxController.getTriggerAxis(Hand.kLeft);
        if (teleUp != 0.0) {
            m_telescopeSUB.TeleUp(teleUp);
        }
        if (teleDown != 0.0) {
            m_telescopeSUB.TeleDown(teleDown);
        }
    }
        public boolean isFinished() {
            return m_telescopeSUB.getCounterValue() >= maxCount || !m_telescopeSUB.getButtonPressed(); 
    }
}