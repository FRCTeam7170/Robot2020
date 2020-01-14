/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.TankDrive;
import frc.robot.subsystems.DriveBase;

import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class RobotContainer {
  private static XboxController m_controller = new XboxController(Constants.Controller.CONTROLLER_PORT);
  private final DriveBase m_driveBase;
  private final TankDrive m_tankDrive;

  public RobotContainer() {
    configureButtonBindings();
    m_driveBase = new DriveBase(new WPI_TalonSRX(Constants.Motors.MOTOR_LEFT_1),
                                new WPI_TalonSRX(Constants.Motors.MOTOR_LEFT_2),
                                new WPI_TalonSRX(Constants.Motors.MOTOR_RIGHT_1),
                                new WPI_TalonSRX(Constants.Motors.MOTOR_RIGHT_2));

    m_tankDrive = new TankDrive(m_driveBase, m_controller);
  }
  
  private void configureButtonBindings() {}
}
