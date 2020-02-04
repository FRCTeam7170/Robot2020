package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoDrive;
import frc.robot.subsystems.DriveBaseOld;

public class Autonomous extends SequentialCommandGroup{
    private final DriveBaseOld m_driveBase;
    public Autonomous(DriveBaseOld driveBase){
        m_driveBase = driveBase;
        addCommands(new AutoDrive(m_driveBase));
    }
}
