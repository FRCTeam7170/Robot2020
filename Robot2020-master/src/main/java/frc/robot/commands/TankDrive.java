package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TankDrive extends CommandBase{
  private final XboxController m_xboxController;
  private final DriveBase m_driveBase;
  private final int m_LeftStickY = Constants.Controller.LEFT_STICK_Y;
  private final int m_RightStickY = Constants.Controller.RIGHT_STICK_Y;
  //private final int m_RightStickX = Constants.Controller.RIGHT_STICK_X; 

  public TankDrive(final DriveBase driveBase, final XboxController xboxController) {
    m_xboxController = xboxController;
    m_driveBase = driveBase;
    addRequirements(m_driveBase);
  }

  @Override
  public void initialize() {
    //Stops drive base
    m_driveBase.stop();
  }

  @Override
  public void execute() {
    m_driveBase.tankDrive(m_xboxController.getRawAxis(m_LeftStickY), m_xboxController.getRawAxis(m_RightStickY));
    //m_driveBase.arcadeDrive(m_xboxController.getRawAxis(m_LeftStickY)*speed, m_xboxController.getRawAxis(m_RightStickX)*speed);
  }
  @Override
	public boolean isFinished() {
		return false;
	}
}