package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AutoDriveBase;

public class PIDDrive extends CommandBase{
    private final AutoDriveBase m_autoDriveBase;
    public PIDDrive(AutoDriveBase autoDriveBase){
        m_autoDriveBase = autoDriveBase;
    }
}