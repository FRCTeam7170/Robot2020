/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveBase extends SubsystemBase {
  private final TalonSRX motorLeft1 = new TalonSRX(Constants.MOTOR_LEFT_1_ID);
  private final TalonSRX motorLeft2 = new TalonSRX(Constants.MOTOR_LEFT_2_ID);
  private final TalonSRX motorRight1 = new TalonSRX(Constants.MOTOR_RIGHT_1_ID);
  private final TalonSRX motorRight2 = new TalonSRX(Constants.MOTOR_RIGHT_2_ID);  
  
  public DriveBase() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setLeftMotors(final double speed) {
    motorLeft1.set(ControlMode.PercentOutput, -speed);
    motorLeft2.set(ControlMode.PercentOutput, -speed);
}
  public void setRightMotors(final double speed) {
    motorRight1.set(ControlMode.PercentOutput, speed);
    motorRight2.set(ControlMode.PercentOutput, speed);
}
}
