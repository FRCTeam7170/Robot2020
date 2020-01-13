package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveBase;

public class TankDrive extends CommandBase{
  private final XboxController xboxController = RobotContainer.getController();
  private final DriveBase m_driveBase;
  private final int m_LeftStickY, m_RightStickY;

  public TankDrive(final DriveBase driveBase, final int LeftStickY, final int RightStickY) {
    m_driveBase = driveBase;
    m_LeftStickY = LeftStickY;
    m_RightStickY = RightStickY;
    addRequirements(m_driveBase);
  }

  @Override
  public void initialize() {
    m_driveBase.stop();
  }

  @Override
  public void execute() {
    m_driveBase.drive(xboxController.getRawAxis(m_LeftStickY), xboxController.getRawAxis(m_RightStickY));
  }
  @Override
	public boolean isFinished() {
		return false;
	}
}