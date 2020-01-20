package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeLift extends SubsystemBase{
    private final DoubleSolenoid m_solenoid;
    private boolean mode = true;

    public IntakeLift(final DoubleSolenoid solenoid) {
        m_solenoid = solenoid;
    }
    public void off(){
        m_solenoid.set(Value.kOff);
    }
    public void move(){
        if (mode){
            m_solenoid.set(Value.kForward);
        } else {
            m_solenoid.set(Value.kReverse);
        }
    }
    public void toggle(){
        mode = !mode;
        move();
    }
    @Override
    public void periodic(){
    }
}