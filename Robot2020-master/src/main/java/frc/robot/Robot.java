/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.Constants;
import frc.robot.commands.Intake;
import frc.robot.commands.groups.Autonomous;
import frc.robot.subsystems.Hang;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.IntakeLift;
import frc.robot.subsystems.IntakeWheel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Autonomous autonomous;
  private Hang m_Climbing = new Hang();
  private FlyWheel m_flyWheel = new FlyWheel();
  private DriveBase m_driveBase = new DriveBase();
  private IntakeLift m_intakeLift = new IntakeLift();
  private IntakeWheel m_intakeWheel = new IntakeWheel();
  private XboxController m_xboxController = new XboxController(Constants.Controller.CONTROLLER_PORT);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    CommandScheduler.getInstance().registerSubsystem(m_flyWheel,
                                                     m_Climbing,
                                                     m_intakeLift,
                                                     m_intakeWheel,
                                                     m_driveBase);

    m_driveBase.setDefaultCommand(new RunCommand(()-> m_driveBase.tankDrive(
                                                      m_xboxController.getRawAxis(Constants.Controller.LEFT_STICK_Y)*12, 
                                                      m_xboxController.getRawAxis(Constants.Controller.RIGHT_STICK_Y)*12), 
                                                      m_driveBase));

    getButton("A").whenPressed(new Intake(m_intakeLift, m_intakeWheel));

  }
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }


  /**
   * This autonomous runs the autonomous command selected by your {@link } class.
   */

  public void autonomousInit() {
    autonomous = new Autonomous(m_driveBase);
    autonomous.schedule();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomous != null) {
      autonomous.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  private JoystickButton getButton(final String name) {
    return new JoystickButton(m_xboxController, XboxController.Button.valueOf("k"+name).value);
  }
}
