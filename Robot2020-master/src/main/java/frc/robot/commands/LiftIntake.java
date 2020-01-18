package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeLift;

public class LiftIntake extends CommandBase{
    private final IntakeLift m_intakeLift;

    public LiftIntake(final IntakeLift intakeLift) {
        m_intakeLift = intakeLift;
        addRequirements(m_intakeLift);
    }
    public void initialize(){
        m_intakeLift.toggle();
    }
}
