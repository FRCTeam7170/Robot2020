package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveBase extends SubsystemBase {
  private SpeedController m_motorLeft1, m_motorLeft2, m_motorRight1, m_motorRight2; 
  private SpeedControllerGroup m_left, m_right;
  private DifferentialDrive m_drive;
  
  public DriveBase(SpeedController motorLeft1, SpeedController motorLeft2, SpeedController motorRight1, SpeedController motorRight2) {
    m_motorLeft1 = motorLeft1;
    m_motorLeft2 = motorLeft2;
    m_motorRight1 = motorRight1;
    m_motorRight2 = motorRight2;

    m_left = new SpeedControllerGroup(m_motorLeft1, m_motorLeft2);
    m_right = new SpeedControllerGroup(m_motorRight1, m_motorRight2);

    m_drive = new DifferentialDrive(m_left, m_right);
  }

  @Override
  public void periodic() {
  }
  public void stop() {
    
  }
  public void drive(double speedLeft, double speedRight) {
    m_drive.tankDrive(speedLeft, speedRight);
  }
}
