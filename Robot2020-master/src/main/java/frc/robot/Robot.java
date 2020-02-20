/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.Constants;
import frc.robot.commands.Hang;
import frc.robot.commands.Intake;
import frc.robot.commands.RamseteDrive;
import frc.robot.commands.RamseteShoot;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.IntakeLift;
import frc.robot.subsystems.IntakeWheel;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command autoCommand;
  //private final Climb s_climbing = new Climb();
  private final Indexer s_indexer = new Indexer();
  private final FlyWheel s_flyWheel = new FlyWheel();
  //private final DriveBase s_driveBase = new DriveBase();
  //private final IntakeLift s_intakeLift = new IntakeLift();
  //private final IntakeWheel s_intakeWheel = new IntakeWheel();
  //private final UsbCamera ballCamera = new UsbCamera("Ball Camera", 1);
  //private final UsbCamera driverCamera = CameraServer.getInstance().startAutomaticCapture("Driver Camera", 0);
  //private final RamseteDrive c_ramseteDrive = new RamseteDrive(s_driveBase);
  //private final RamseteShoot c_ramseteShoot = new RamseteShoot(s_driveBase, s_flyWheel, s_indexer);
  private final XboxController m_xboxController = new XboxController(Constants.Controller.CONTROLLER_PORT);


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    //ballCamera.setVideoMode(PixelFormat.kMJPEG, 144, 81, 2);
    //CameraServer.getInstance().startAutomaticCapture(ballCamera);
    //driverCamera.setVideoMode(PixelFormat.kMJPEG, 320, 180, 24);
    //Shuffleboard.getTab("Camera").add("Camera Driver", driverCamera);

    CommandScheduler.getInstance().registerSubsystem(s_indexer,
                                                     s_flyWheel
                                                     //s_climbing,
                                                     //s_intakeLift,
                                                     //s_intakeWheel,
                                                     //s_driveBase
                                                     );

                                                     
    //s_driveBase.setDefaultCommand(new RunCommand(()-> s_driveBase.tankDriveVolts(
      //                                                  m_xboxController.getRawAxis(Constants.Controller.LEFT_STICK_Y)*12, 
        //                                                m_xboxController.getRawAxis(Constants.Controller.RIGHT_STICK_Y)*12), 
          //                                              s_driveBase));
    //s_climbing.setDefaultCommand(new Hang(s_climbing));

    //getButton("A").whenPressed(new Intake(s_intakeLift, s_intakeWheel));
    //getButton("X").whenPressed(c_ramseteShoot.getAutoCommand());
    //getButton("X").whenPressed(new FlyWheelSpin(s_flyWheel).alongWith(new LoadBall(s_indexer).andThen(new WaitCommand(1).andThen(() -> s_flyWheel::stop, s_flyWheel))));
    getButton("X").whenPressed(new RunCommand(() -> s_flyWheel.setFlyWheel(), s_flyWheel).alongWith(new RunCommand(s_flyWheel::returnRPM)));
    s_indexer.setDefaultCommand(new RunCommand(() -> s_indexer.spinTest(
      m_xboxController.getRawAxis(Constants.Controller.LEFT_STICK_Y)),
        s_indexer));
    //s_flyWheel.setDefaultCommand(new RunCommand(() -> s_flyWheel.spinManual(
      //m_xboxController.getRawAxis(Constants.Controller.LEFT_STICK_Y)),
        //s_flyWheel));
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
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void disabledPeriodic() {
  }
  /**
   * This autonomous runs the autonomous command selected by your {@link } class.
   */

  public void autonomousInit() {
    //autoCommand = c_ramseteDrive.getAutoCommand();
    autoCommand.schedule();
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
    if (autoCommand != null) {
      autoCommand.cancel();
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
