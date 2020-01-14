package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class AutoDrive extends CommandBase{
    private DriveBase m_driveBase;
    public AutoDrive(DriveBase driveBase){
        m_driveBase = driveBase;
    }
}