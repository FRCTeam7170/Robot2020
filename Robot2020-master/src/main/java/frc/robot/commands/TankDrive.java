package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveBase;

public class TankDrive extends CommandBase{
  private DriveBase m_driveBase;

  public TankDrive(DriveBase driveBase){
    m_driveBase = driveBase;
    addRequirements(m_driveBase);
  }

  @Override
  public void initialize(){
    m_driveBase.stop();
  }

  @Override
  public void execute(){
    m_driveBase.drive(
  }
}