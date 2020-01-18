package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlyWheel;

public class FlyWheelSpin extends CommandBase{
    private final FlyWheel m_fly;

    public FlyWheelSpin(FlyWheel fly) {
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
