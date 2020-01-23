package frc.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import edu.wpi.first.networktables.EntryListenerFlags;

public class AutoDriveBase extends SubsystemBase{
    private final WPI_TalonSRX m_motorLeft1, m_motorLeft2, m_motorRight1, m_motorRight2;
    private final double kP1 = 0.1;
    private final double kI1 = 0;
    private final double kD1 = 0;
    private final double kF1 = 0;
    private final double kP2 = 2.0;
    private final double kI2 = 0;
    private final double kD2 = 4.0;
    private final double kF2 = 0;
    private final int smoothing = 4;
    private double targetPos;
    private final ADIS16448_IMU m_imu;

    public AutoDriveBase(final WPI_TalonSRX motorLeft1, final WPI_TalonSRX motorLeft2, final WPI_TalonSRX motorRight1,
                        final WPI_TalonSRX motorRight2, ADIS16448_IMU imu) {
                        
        m_motorLeft1 = motorLeft1;
        m_motorLeft2 = motorLeft2;
        m_motorRight1 = motorRight1;
        m_motorRight2 = motorRight2;
        m_imu = imu;

        ShuffleboardTab AutoDriveBaseTab = Shuffleboard.getTab("AutoDriveBase_PID_Values");

        AutoDriveBaseTab.add("kP1", kP1).getEntry().addListener(
                notification -> {m_motorLeft1.config_kP(0, notification.value.getDouble());},
                EntryListenerFlags.kUpdate
        );
        AutoDriveBaseTab.add("kI1", kI1).getEntry().addListener(
                notification -> {m_motorLeft1.config_kI(0, notification.value.getDouble());},
                EntryListenerFlags.kUpdate
        );
        AutoDriveBaseTab.add("kD1", kD1).getEntry().addListener(
                notification -> {m_motorLeft1.config_kD(0, notification.value.getDouble());},
                EntryListenerFlags.kUpdate
        );
        AutoDriveBaseTab.add("kF1", kF1).getEntry().addListener(
                notification -> {m_motorLeft1.config_kF(0, notification.value.getDouble());},
                EntryListenerFlags.kUpdate
        );

        // AutoDriveBaseTab.add("kP2", kP2).getEntry().addListener(
        //     notification -> {m_motorLeft1.config_kP(0, notification.value.getDouble());},
        //     EntryListenerFlags.kUpdate
        // );
        // AutoDriveBaseTab.add("kI2", kI2).getEntry().addListener(
        //         notification -> {m_motorLeft1.config_kI(0, notification.value.getDouble());},
        //         EntryListenerFlags.kUpdate
        // );
        // AutoDriveBaseTab.add("kD2", kD2).getEntry().addListener(
        //         notification -> {m_motorLeft1.config_kD(0, notification.value.getDouble());},
        //         EntryListenerFlags.kUpdate
        // );
        // AutoDriveBaseTab.add("kF2", kF2).getEntry().addListener(
        //         notification -> {m_motorLeft1.config_kF(0, notification.value.getDouble());},
        //         EntryListenerFlags.kUpdate
        // );

        m_motorLeft1.configFactoryDefault();

        m_motorLeft1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
        
        m_motorLeft1.configNeutralDeadband(0.001, 30);
        
		m_motorLeft1.setSensorPhase(false);
        m_motorLeft1.setInverted(false);
        
		m_motorLeft1.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
        m_motorLeft1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 30);
        
		m_motorLeft1.configNominalOutputForward(0, 30);
		m_motorLeft1.configNominalOutputReverse(0, 30);
		m_motorLeft1.configPeakOutputForward(1, 30);
        m_motorLeft1.configPeakOutputReverse(-1, 30);
        
		m_motorLeft1.selectProfileSlot(0, 0);
        
		m_motorLeft1.configMotionCruiseVelocity(15000, 30);
        m_motorLeft1.configMotionAcceleration(6000, 30);
        
        m_motorLeft1.setSelectedSensorPosition(0, 0, 30);
        m_motorLeft1.configMotionSCurveStrength(smoothing);
    }
    public void setDistance(double distance){
        targetPos = Constants.Measurements.WHEEL_MOVE_TICK * 217.391 * distance;
    }

    public void move(){
        m_motorLeft1.set(ControlMode.MotionMagic, targetPos);
    }

    public void turnDegrees (double degrees){
        m_motorLeft1.set(ControlMode.Position, degrees - m_imu.getAngle());
        m_motorRight1.set(ControlMode.Position, -(degrees - m_imu.getAngle()));
    }
}