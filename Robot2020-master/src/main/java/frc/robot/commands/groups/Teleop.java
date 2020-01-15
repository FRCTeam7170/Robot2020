package frc.robot.commands.groups;

import frc.robot.commands.FlyWheelSpin;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FlyWheel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class Teleop extends ParallelCommandGroup{
    public Teleop(XboxController xboxController, DriveBase driveBase, FlyWheel flyWheel) {
        addCommands(new TankDrive(driveBase, xboxController),
                    new FlyWheelSpin(flyWheel));
    }
}