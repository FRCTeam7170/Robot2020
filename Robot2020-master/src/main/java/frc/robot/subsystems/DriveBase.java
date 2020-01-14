package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveBase extends SubsystemBase {
  private final WPI_TalonSRX m_motorLeft1, m_motorLeft2, m_motorRight1, m_motorRight2;
  private final DifferentialDrive m_drive;

  public DriveBase(final WPI_TalonSRX motorLeft1, final WPI_TalonSRX motorLeft2, final WPI_TalonSRX motorRight1,
                   final WPI_TalonSRX motorRight2) {
                     
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

  public void drive(final double speedLeft, final double speedRight) {
    m_drive.tankDrive(-speedLeft, -speedRight);
  }
}
