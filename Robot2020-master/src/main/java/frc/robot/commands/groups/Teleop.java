package frc.robot.commands.groups;

import frc.robot.commands.TankDrive;
import frc.robot.subsystems.DriveBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class Teleop extends ParallelCommandGroup{
    public Teleop(XboxController xboxController, DriveBase driveBase) {
        addCommands(new TankDrive(driveBase, xboxController));
    }
}