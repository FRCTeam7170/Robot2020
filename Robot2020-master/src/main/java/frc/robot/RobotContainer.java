/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveBase;

public class RobotContainer {
  private static XboxController m_controller = new XboxController(Constants.Controller.CONTROLLER_PORT);
  private final DriveBase m_drivebase;
  private final TankDrive m_tankDrive;

  public RobotContainer() {
    configureButtonBindings();
    m_drivebase = new DriveBase(new WPI_TalonSRX(Constants.Motors.MOTOR_LEFT_1),
                                new WPI_TalonSRX(Constants.Motors.MOTOR_LEFT_2),
                                new WPI_TalonSRX(Constants.Motors.MOTOR_RIGHT_1),
                                new WPI_TalonSRX(Constants.Motors.MOTOR_RIGHT_2));

    m_tankDrive = new TankDrive(m_drivebase, 
                                Constants.Controller.LEFT_STICK_Y, 
                                Constants.Controller.RIGHT_STICK_Y);
  }
  
  private void configureButtonBindings() {}
  
  public static XboxController getController(){
    return m_controller;
  }
}
