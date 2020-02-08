package frc.robot.subsystems;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeWheel extends SubsystemBase{
    private final WPI_TalonSRX m_motor = new WPI_TalonSRX(Constants.Motors.INTAKEWHEEL);
    
    public void off(){
        m_motor.set(ControlMode.PercentOutput, 0);
    }
    public void on(){
        m_motor.set(ControlMode.PercentOutput, 0.75);
    }
}