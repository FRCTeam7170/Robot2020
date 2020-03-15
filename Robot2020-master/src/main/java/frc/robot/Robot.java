/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.Constants;
import frc.robot.commands.ClimbCMD;
import frc.robot.commands.ShootCMD;
import frc.robot.commands.AutoShootCMD;
import frc.robot.commands.TelescopeCMD;
import frc.robot.commands.IntakeLiftCMD;
import frc.robot.commands.TurnOnSpotCMD;
import frc.robot.commands.IntakeWheelCMD;
import frc.robot.commands.InderxerOverWriteCMD;
import frc.robot.subsystems.ClimbSUB;
import frc.robot.subsystems.IndexerSUB;
import frc.robot.subsystems.FlyWheelSUB;
import frc.robot.subsystems.DriveBaseSUB;
import frc.robot.subsystems.IntakeLiftSUB;
import frc.robot.subsystems.IntakeWheelSUB;
import frc.robot.subsystems.ReleaseSUB;
import frc.robot.subsystems.TelescopeSUB;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Robot extends TimedRobot {
  private Command autoCommand;
  private final ClimbSUB s_climbing = new ClimbSUB();
  private final IndexerSUB s_indexer = new IndexerSUB();
  private final ReleaseSUB s_release = new ReleaseSUB();
  private final FlyWheelSUB s_flyWheel = new FlyWheelSUB();
  private final DriveBaseSUB s_driveBase = new DriveBaseSUB();
  private final TelescopeSUB s_telescope = new TelescopeSUB();
  private final IntakeLiftSUB s_intakeLift = new IntakeLiftSUB();
  private final IntakeWheelSUB s_intakeWheel = new IntakeWheelSUB();
  private final UsbCamera ballCamera = CameraServer.getInstance().startAutomaticCapture("Ball Camera", 1);
  private final UsbCamera driverCamera = CameraServer.getInstance().startAutomaticCapture("Driver Camera", 0);
  private final XboxController m_xboxController = new XboxController(Constants.Controller.CONTROLLER_PORT);

  @Override
  public void robotInit() {

    Shuffleboard.getTab("Camera").add("Camera Ball", ballCamera);
    Shuffleboard.getTab("Camera").add("Camera Driver", driverCamera);

    CommandScheduler.getInstance().registerSubsystem(s_release, s_indexer, s_telescope, s_flyWheel, s_climbing, s_intakeLift, s_intakeWheel, s_driveBase);
    
    s_climbing.setDefaultCommand(new ClimbCMD(s_climbing));
    
    s_telescope.setDefaultCommand(new TelescopeCMD(s_telescope));
    
    s_intakeWheel.setDefaultCommand(new IntakeWheelCMD(s_intakeWheel));

    s_driveBase.setDefaultCommand(new RunCommand(() -> s_driveBase.tankDrive(
    m_xboxController.getRawAxis(Constants.Controller.LEFT_STICK_Y),
    m_xboxController.getRawAxis(Constants.Controller.RIGHT_STICK_Y)),  
    s_driveBase));
    
    getButton("X").whenPressed(new TurnOnSpotCMD(s_driveBase));

    getButton("A").whenPressed(new IntakeLiftCMD(s_intakeLift));
    
    getButton("Start").whenPressed(s_release::release, s_release);
    
    getButton("Y").whenHeld(new RunCommand(s_intakeWheel::reverse, s_intakeWheel));

    getButton("BumperLeft").whenPressed(new InderxerOverWriteCMD(s_indexer, m_xboxController).andThen(s_indexer::stop, s_indexer));

    getButton("B").whenPressed(new ShootCMD(s_flyWheel, s_indexer, s_intakeLift, m_xboxController)
                  .andThen(s_indexer::stop, s_indexer).andThen(s_flyWheel::stop, s_flyWheel).andThen(s_intakeLift::down, s_intakeLift));
  }
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    CommandScheduler.getInstance().cancelAll();
    if (autoCommand != null) {
      autoCommand.cancel();
    }
    s_release.off();
    s_intakeLift.up();
  }

  @Override
  public void disabledPeriodic() {
  }

  public void autonomousInit() {
    autoCommand = new TurnOnSpotCMD(s_driveBase)
                  .andThen(new AutoShootCMD(s_flyWheel, s_indexer)
                  .andThen(s_indexer::stop, s_indexer).andThen(s_flyWheel::stop, s_flyWheel))
                  .andThen(() -> s_driveBase.tankDrive(0.5, 0.5), s_driveBase)
                  .andThen(new WaitCommand(2))
                  .andThen(s_driveBase::stop, s_driveBase); //best auto command ever!!

    autoCommand.schedule();
  }

  @Override
  public void autonomousPeriodic() { 
  }

  @Override
  public void teleopInit() {
    if (autoCommand != null) {
      autoCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }

  private JoystickButton getButton(final String name) {
    return new JoystickButton(m_xboxController, XboxController.Button.valueOf("k"+name).value);
  }
}
