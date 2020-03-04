package frc.robot.commands;

import frc.robot.subsystems.DriveBaseSUB;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.networktables.NetworkTableInstance;

public class TurnOnSpotCMD extends CommandBase{
    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-spooky");
    private final DriveBaseSUB m_driveBase;
    private double kP = 0.0254;
    private double kI = 0.03;
    private double kD = 0;
    private final double deadband = 2;
    private final PIDController pid;
    private double tx, speed;

    public TurnOnSpotCMD(DriveBaseSUB drivebase){
        m_driveBase = drivebase;
        pid = new PIDController(kP, kI, kD);
        addRequirements(m_driveBase);
    }

    public void initialize(){
        table.getEntry("led").setNumber(3);
        table.getEntry("camMode").setNumber(0);
    }
    public void execute(){
        tx = table.getEntry("tx").getDouble(0.0);
        speed = pid.calculate(tx);
        System.out.println(tx);
        m_driveBase.tankDrive(speed, -speed);
    }
    public boolean isFinished(){
        //return Math.abs(tx) <= deadband;
        return true;
    }
    public void end(){
        table.getEntry("led").setNumber(1);
    }
}