package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.PIDDrive;
import frc.robot.subsystems.AutoDriveBase;

public class Autonomous extends SequentialCommandGroup{
    private final AutoDriveBase m_autoDriveBase;
    public Autonomous(AutoDriveBase autoDriveBase){
        m_autoDriveBase = autoDriveBase;
        addCommands(new PIDDrive(m_autoDriveBase));
    }
}