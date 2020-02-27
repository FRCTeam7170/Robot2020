package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ClimbSUB;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbCMD extends CommandBase {

	public final ClimbSUB m_climb;
	private final XboxController m_xboxController;

	public ClimbCMD(final ClimbSUB climbing) {
		m_climb = climbing;
		m_xboxController = new XboxController(Constants.Controller.CONTROLLER_PORT);
	}
	public void execute() {
		if (m_xboxController.getBumperPressed(Hand.kRight)) {
			m_climb.ClimbUp();
		}
		if (m_xboxController.getBumperPressed(Hand.kLeft)) {
			m_climb.ClimbDown();
		}
	}
}
