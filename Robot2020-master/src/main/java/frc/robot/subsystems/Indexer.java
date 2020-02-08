package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase{
    private final TalonSRX m_motor = new TalonSRX(Constants.Motors.INDEXER);
    private final double kP = 0.0025;
    private final double kI = 0.001;
    private final double kD = 2;
    private final double kF = 1023 / 7200;
    private int m_rpm;

    public Indexer(){
        m_motor.configFactoryDefault();
        m_motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
        /**
         * Phase sensor accordingly. Positive Sensor Reading should match Green
         * (blinking) Leds on Talon
         */
        m_motor.setSensorPhase(true);

        /* Config the peak and nominal outputs */
        m_motor.configNominalOutputForward(0, 30);
        m_motor.configNominalOutputReverse(0, 30);
        m_motor.configPeakOutputForward(1, 30);
        m_motor.configPeakOutputReverse(-1, 30);

        m_motor.config_kP(0, kP);
        m_motor.config_kI(0, kI);
        m_motor.config_kD(0, kD);
        m_motor.config_kF(0, kF);
    }
    public void setRPM(final int rpm){
        m_rpm = rpm;
    }
    public void stop(){
        m_motor.set(ControlMode.PercentOutput, 0);
    }
    public void setIndexer(){
        final double rpmout = m_rpm * 4096 / 600;
        m_motor.set(ControlMode.Velocity, rpmout);
    }
}