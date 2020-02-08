package frc.robot.commands;

import frc.robot.subsystems.FlyWheel;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FlyWheelSpin extends CommandBase{
    private final FlyWheel m_fly;

    public FlyWheelSpin(final FlyWheel fly) {
        m_fly = fly;
        addRequirements(m_fly);
    }

    public void initialize() {
        m_fly.setRPM(200);
    }

    public void execute() {
        m_fly.set_FlyWheel();
    }

    public boolean isFinished() {
        return false;
    }
}
