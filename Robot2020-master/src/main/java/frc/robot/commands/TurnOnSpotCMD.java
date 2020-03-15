package frc.robot.commands;

import frc.robot.subsystems.DriveBaseSUB;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.networktables.NetworkTableInstance;

public class TurnOnSpotCMD extends CommandBase {
	private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-spooky");
	private final DriveBaseSUB m_driveBase;
	private final double kP = 0.0254;
	private final double kI = 0.03;
	private final double kD = 0;
	private final double deadband = 1;
	private final PIDController pid;
	private double tx, speed;
	private final Timer m_timer = new Timer();

	public TurnOnSpotCMD(final DriveBaseSUB drivebase) {
		m_driveBase = drivebase;
		pid = new PIDController(kP, kI, kD);
		addRequirements(m_driveBase);
	}

	public void initialize() {
		m_timer.start();
		table.getEntry("led").setNumber(3);
		table.getEntry("camMode").setNumber(0);
	}

	public void execute() {
		tx = table.getEntry("tx").getDouble(0.0);
		speed = pid.calculate(tx);
		m_driveBase.tankDrive(speed, -speed);
	}

	public boolean isFinished() {
		return Math.abs(tx) <= deadband || m_timer.hasElapsed(3);
	}

	public void end() {
		table.getEntry("led").setNumber(1);
	}
}