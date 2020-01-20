package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeLift;
import frc.robot.subsystems.IntakeWheel;

public class LiftIntake extends CommandBase{
    private final IntakeLift m_intakeLift;
    private final IntakeWheel m_intakeWheel;
    private boolean up = true;

    public LiftIntake(final IntakeLift intakeLift, final IntakeWheel intakeWheel) {
        m_intakeLift = intakeLift;
        m_intakeWheel = intakeWheel;
        addRequirements(m_intakeLift, m_intakeWheel);
    }
    public void initialize(){
        up = !up;
        if (up){
            m_intakeLift.up();
            m_intakeWheel.off();
        } else {
            m_intakeLift.down();
            m_intakeWheel.on();
        }
    }
    public boolean isFinished(){
        return false;
    }
}
