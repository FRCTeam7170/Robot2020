/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveBase;
import edu.wpi.first.wpilibj2.command.Command;


public class RobotContainer {
  private static XboxController m_controller = new XboxController(Constants.Controller.CONTROLLER_PORT);
  private final DriveBase m_drivebase;
  private final TankDrive m_tankDrive;

  public RobotContainer() {
    configureButtonBindings();
    m_drivebase = new DriveBase(new TalonSRX(Constants.Motors.MOTOR_LEFT_1_ID),
                                new TalonSRX(Constants.Motors.MOTOR_LEFT_2_ID),
                                new TalonSRX(Constants.Motors.MOTOR_RIGHT_1_ID),
                                new TalonSRX(Constants.Motors.MOTOR_RIGHT_2_ID));

    m_tankDrive = new TankDrive(m_drivebase);
  }
  
  private void configureButtonBindings() {}
  
  public static XboxController Controller(){
    return m_controller;
  }
}
