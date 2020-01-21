package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.AutoDriveBase;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoDrive extends CommandBase{
    private final AutoDriveBase m_autoDriveBase;

    public AutoDrive(AutoDriveBase autoDriveBase){
        m_autoDriveBase = autoDriveBase;
        addRequirements(m_autoDriveBase);
    }
    public void initialize(){
        m_autoDriveBase.setDistance(Constants.Measurements.AUTO_DRIVE_DISTANCE);
    }
    public void execute(){
        m_autoDriveBase.move();
    }
    public boolean isFinished(){
        return true;
    }
}