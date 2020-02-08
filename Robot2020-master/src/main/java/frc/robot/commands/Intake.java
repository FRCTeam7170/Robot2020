package frc.robot.commands;

import frc.robot.subsystems.IntakeLift;
import frc.robot.subsystems.IntakeWheel;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Intake extends CommandBase{
    private final IntakeLift m_intakeLift;
    private final IntakeWheel m_intakeWheel;
    private boolean up = true;

    public Intake(final IntakeLift intakeLift, final IntakeWheel intakeWheel) {
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
