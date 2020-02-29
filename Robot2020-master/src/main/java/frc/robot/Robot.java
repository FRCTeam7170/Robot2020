/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.Constants;
import frc.robot.commands.ClimbCMD;
import frc.robot.commands.FlyWheelCMD;
import frc.robot.commands.IntakeLiftCMD;
import frc.robot.commands.IntakeWheelCMD;
import frc.robot.commands.TurnOnSpotCMD;
import frc.robot.commands.RamseteDriveCMD;
import frc.robot.commands.RamseteShootCMD;
import frc.robot.subsystems.ClimbSUB;
import frc.robot.subsystems.IndexerSUB;
import frc.robot.subsystems.FlyWheelSUB;
import frc.robot.subsystems.DriveBaseSUB;
import frc.robot.subsystems.IntakeLiftSUB;
import frc.robot.subsystems.IntakeWheelSUB;
import frc.robot.subsystems.TelescopeSUB;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
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
  private final ClimbSUB s_climbing = new ClimbSUB();
  private final IndexerSUB s_indexer = new IndexerSUB();
  private final FlyWheelSUB s_flyWheel = new FlyWheelSUB();
  private final DriveBaseSUB s_driveBase = new DriveBaseSUB();
  private final TelescopeSUB s_telescope = new TelescopeSUB();
  private final IntakeLiftSUB s_intakeLift = new IntakeLiftSUB();
  private final IntakeWheelSUB s_intakeWheel = new IntakeWheelSUB();
  private final RamseteDriveCMD c_ramseteDrive = new RamseteDriveCMD(s_driveBase);
  private final RamseteShootCMD c_ramseteShoot = new RamseteShootCMD(s_driveBase, s_flyWheel, s_indexer);
  private final UsbCamera ballCamera = CameraServer.getInstance().startAutomaticCapture("Ball Camera", 1);
  private final UsbCamera driverCamera = CameraServer.getInstance().startAutomaticCapture("Driver Camera", 0);
  private final XboxController m_xboxController = new XboxController(Constants.Controller.CONTROLLER_PORT);


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    //ballCamera.setVideoMode(PixelFormat.kMJPEG, 144, 81, 2);
    //Shuffleboard.getTab("Camera").add("Camera Ball", ballCamera);
    //driverCamera.setVideoMode(PixelFormat.kMJPEG, 320, 180, 24);
    //Shuffleboard.getTab("Camera").add("Camera Driver", driverCamera);

    CommandScheduler.getInstance().registerSubsystem(s_indexer,
                                                     s_telescope,
                                                     s_flyWheel,
                                                     s_climbing,
                                                     s_intakeLift,
                                                     s_intakeWheel,
                                                     s_driveBase
                                                     );

                                                     
    s_driveBase.setDefaultCommand(new RunCommand(()-> s_driveBase.tankDriveVolts(
                                                        m_xboxController.getRawAxis(Constants.Controller.LEFT_STICK_Y)*12, 
                                                        m_xboxController.getRawAxis(Constants.Controller.RIGHT_STICK_Y)*12), 
                                                        s_driveBase));

    s_intakeWheel.setDefaultCommand(new IntakeWheelCMD(s_intakeWheel, s_intakeLift));
    //getButton("X").whenPressed(new TurnOnSpotCMD(s_driveBase));
    getButton("Y").whenHeld(new FlyWheelCMD(s_flyWheel));

    //s_indexer.setDefaultCommand(new RunCommand(() -> s_indexer.spinTest(
      //m_xboxController.getRawAxis(Constants.Controller.LEFT_STICK_Y)),
        //s_indexer));
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
    s_indexer.stop();
    s_flyWheel.stop();
    s_climbing.stop();
    s_driveBase.stop();
    s_telescope.stop();
    s_intakeWheel.stop();
    s_intakeLift.up();
    s_telescope.zeroSensor();
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
