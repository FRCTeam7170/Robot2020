package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveBase extends SubsystemBase {
  private WPI_TalonSRX m_motorLeft1, m_motorLeft2, m_motorRight1, m_motorRight2; 
  private DifferentialDrive m_drive;
  
  public DriveBase(WPI_TalonSRX motorLeft1, WPI_TalonSRX motorLeft2, WPI_TalonSRX motorRight1, WPI_TalonSRX motorRight2) {
    m_motorLeft1 = motorLeft1;
    m_motorLeft2 = motorLeft2;
    m_motorRight1 = motorRight1;
    m_motorRight2 = motorRight2;

    m_motorLeft2.follow(m_motorLeft1);
    m_motorLeft2.setSafetyEnabled(false);
    m_motorRight2.follow(m_motorRight1);
    m_motorRight2.setSafetyEnabled(false);

    m_drive = new DifferentialDrive(m_motorLeft1, m_motorRight1);
  }

  @Override
  public void periodic() {
  }
  public void stop() {
    m_drive.stopMotor();
  }
  public void drive(double speedLeft, double speedRight) {
    m_drive.tankDrive(speedLeft, speedRight);
  }
}
