package frc.robot.commands.groups;

import frc.robot.commands.AutoDrive;
import frc.robot.subsystems.DriveBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Autonomous extends SequentialCommandGroup{
    private final DriveBase m_driveBase;
    public Autonomous(final DriveBase driveBase) {
        m_driveBase = driveBase;
        addCommands(new AutoDrive(m_driveBase));
    }
}
