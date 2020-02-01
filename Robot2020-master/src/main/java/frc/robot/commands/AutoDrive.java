package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoDrive extends CommandBase{
    private final DriveBase m_driveBase;

    public AutoDrive(DriveBase autoDriveBase){
        m_driveBase = autoDriveBase;
        addRequirements(m_driveBase);
    }
    public void initialize(){
        m_driveBase.setDistance(60);
    }
    public void execute(){
        m_driveBase.drive();
    }
    public boolean isFinished(){
        return true;
    }
    public void end(){
        m_driveBase.stop();
    }
}