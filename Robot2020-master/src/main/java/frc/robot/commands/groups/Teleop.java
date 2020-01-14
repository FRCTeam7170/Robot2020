package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.DriveBase;
import frc.robot.commands.TankDrive;

public class Teleop extends ParallelCommandGroup{
    public Teleop(XboxController xboxController, DriveBase driveBase) {
        addCommands(new TankDrive(driveBase, xboxController));
    }
}