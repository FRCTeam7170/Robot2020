package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.TelescopeSUB;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TelescopeCMD extends CommandBase {

	public final TelescopeSUB m_telescope;
	private final XboxController m_xboxController;

	public TelescopeCMD(final TelescopeSUB telescopeSUB) {
		m_telescope = telescopeSUB;
		m_xboxController = new XboxController(Constants.Controller.CONTROLLER_PORT);
		addRequirements(m_telescope);
	}

	public void execute() {
		if (m_xboxController.getTriggerAxis(Hand.kRight) != 0) {
			m_telescope.move(m_xboxController.getTriggerAxis(Hand.kRight));

		} else if (m_xboxController.getTriggerAxis(Hand.kLeft) != 0) {
			m_telescope.move(-m_xboxController.getTriggerAxis(Hand.kLeft));
			
		} else {
			m_telescope.move(0);
		}
	}
}