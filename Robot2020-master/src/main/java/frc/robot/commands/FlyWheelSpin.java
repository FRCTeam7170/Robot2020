package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlyWheel;

public class FlyWheelSpin extends CommandBase{
    private final FlyWheel m_flyWheel;

    public FlyWheelSpin(final FlyWheel flyWheel) {
        m_flyWheel = flyWheel;
        addRequirements(m_flyWheel);
    }
    public void initialize() {
        m_flyWheel.setRPM(3000);
    }
    public void execute() {
        m_flyWheel.setFlyWheel();
    }
    public boolean isFinished() {
        return false;
    }
}
