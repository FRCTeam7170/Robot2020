package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveBaseSUB;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.networktables.NetworkTableInstance;

public class MoveStraightLineCMD extends CommandBase{
    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-spooky");
    private final DriveBaseSUB m_driveBase;
    private double kP0 = 0.0254;
    private double kI0 = 0.03;
    private double kD0 = 0;
    private double kP1 = 0.0254;
    private double kI1 = 0.03;
    private double kD1 = 0;
    private final PIDController pid0;
    private final PIDController pid1;
    private double initAngle, angle, ty, speed, turn, r;

    public MoveStraightLineCMD(DriveBaseSUB drivebase){
        table.getEntry("led").setNumber(3);
        table.getEntry("camMode").setNumber(0);
        m_driveBase = drivebase;
        pid0 = new PIDController(kP0, kI0, kD0);
        pid1 = new PIDController(kP1, kI1, kD1);
        addRequirements(m_driveBase);
        initAngle = m_driveBase.getHeading();
    }
    public void initialize(){
    }
    public void execute(){
        ty = table.getEntry("ty").getDouble(0.0);
        r = (Constants.Measurements.TARGET_HEIGHT - Constants.Measurements.LIMELIGHT_HEIGHT)
				/ Math.tan(Constants.Measurements.LIMELIGHT_ANGLE + ty);
        angle = m_driveBase.getHeading();
        speed = pid0.calculate(Constants.Measurements.SHOOTING_RANGE - r);
        turn = pid1.calculate(initAngle + angle);
        m_driveBase.arcadeDrive(speed, turn);
    }
    public boolean isFinished(){
        return r == Constants.Measurements.SHOOTING_RANGE;
    }
}