package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase{
    private final CANSparkMax m_motor = new CANSparkMax(Constants.Motors.INDEXER, MotorType.kBrushed);
    public Indexer(){
    }
    public void stop(){
        m_motor.stopMotor();
    }
    public void index(){
        m_motor.set(0.1);
    }
}