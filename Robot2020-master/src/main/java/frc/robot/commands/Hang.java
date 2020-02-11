package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Climb;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Hang extends CommandBase {

	public final Climb m_climb;
	private final XboxController m_xboxController;
	private double teleUp;
	private double teleDown;

	public Hang(final Climb climbing) {
		m_climb = climbing;
		m_xboxController = new XboxController(Constants.Controller.CONTROLLER_PORT);
	}

	public void execute() {
		teleUp = m_xboxController.getTriggerAxis(Hand.kRight);
		teleDown = m_xboxController.getTriggerAxis(Hand.kLeft);
		if (teleUp != 0.0) {
			m_climb.TeleUp(teleUp);
		}
		if (teleDown != 0.0) {
			m_climb.TeleDown(teleDown);
		}
		if (m_xboxController.getBumperPressed(Hand.kRight)) {
			m_climb.ClimbUp();
		}
		/* We might not be able to move the bot down because of a ratchet
		if (m_xboxController.getBumperPressed(Hand.kLeft)) {
			m_climb.ClimbDown();
		}
		*/
	}
}
