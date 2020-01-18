package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlyWheel;

public class FlyWheelSpin extends CommandBase{
    public final FlyWheel m_fly;

    public FlyWheelSpin(FlyWheel fly) {
        m_fly = fly;
    }

    public void initialize() {
        m_fly.setRPM(1500);
    }

    public void execute() {
        m_fly.set_FlyWheel();
    }

    public boolean isFinished() {
        return false;
    }

}
