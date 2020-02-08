package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

//import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class FlyWheel extends SubsystemBase{

    private final WPI_TalonSRX flywheelMotor1 = new WPI_TalonSRX(Constants.Motors.FLYWHEEL_1);
    private final WPI_TalonSRX flywheelMotor2 = new WPI_TalonSRX(Constants.Motors.FLYWHEEL_2);
    private double m_targetRPM;
    private double rpmout;
    private final double kP = 0.0025;
    private final double kI = 0.001;
    private final double kD = 2;
    private final double kF = 1023 / 7200;

    public FlyWheel() {
        flywheelMotor2.setInverted(true);
        flywheelMotor2.follow(flywheelMotor1);

        flywheelMotor1.configFactoryDefault();

        flywheelMotor1.setNeutralMode(NeutralMode.Coast);
        flywheelMotor2.setNeutralMode(NeutralMode.Coast);

        /* Config sensor used for Primary PID [Velocity] */
        flywheelMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
        /**
         * Phase sensor accordingly. Positive Sensor Reading should match Green
         * (blinking) Leds on Talon
         */
        flywheelMotor1.setSensorPhase(true);

        /* Config the peak and nominal outputs */
        flywheelMotor1.configNominalOutputForward(0, 30);
        flywheelMotor1.configNominalOutputReverse(0, 30);
        flywheelMotor1.configPeakOutputForward(1, 30);
        flywheelMotor1.configPeakOutputReverse(-1, 30);
        

        /* Config the Velocity closed loop gains in slot0 */
        ShuffleboardTab FlyWheelTab = Shuffleboard.getTab("FlyWheelTab");

        FlyWheelTab.add("kP", kP).getEntry().addListener(
                notification -> {flywheelMotor1.config_kP(0, notification.value.getDouble());},
                EntryListenerFlags.kUpdate
        );
        FlyWheelTab.add("kI", kI).getEntry().addListener(
                notification -> {flywheelMotor1.config_kI(0, notification.value.getDouble());},
                EntryListenerFlags.kUpdate
        );
        FlyWheelTab.add("kD", kD).getEntry().addListener(
                notification -> {flywheelMotor1.config_kD(0, notification.value.getDouble());},
                EntryListenerFlags.kUpdate
        );
        FlyWheelTab.add("kF", kF).getEntry().addListener(
                notification -> {flywheelMotor1.config_kF(0, notification.value.getDouble());},
                EntryListenerFlags.kUpdate
        );
        FlyWheelTab.addNumber("RPM", new DoubleSupplier(){
            public double getAsDouble() {return flywheelMotor2.getSelectedSensorVelocity() * 0.15;}
        });
    }

    public void setRPM(final double rpm) {
        m_targetRPM = rpm;
    }

    public void setFlyWheel() {
        rpmout = m_targetRPM * 4096 / 600;
        flywheelMotor1.set(ControlMode.Velocity, rpmout);
        flywheelMotor2.follow(flywheelMotor1);
    }
    public void stop(){
        flywheelMotor2.set(ControlMode.PercentOutput, 0);
        flywheelMotor1.set(ControlMode.PercentOutput, 0);
    }
}
