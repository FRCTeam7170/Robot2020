package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import edu.wpi.first.networktables.EntryListenerFlags;

public class AutoDriveBase extends SubsystemBase{
    private final WPI_TalonSRX m_motorLeft1, m_motorLeft2, m_motorRight1, m_motorRight2;
    private final double kP1 = 0.2;
    private final double kI1 = 0;
    private final double kD1 = 0.2;
    private final double kF1 = 0;
    private final double kP2 = 2.0;
    private final double kI2 = 0;
    private final double kD2 = 4.0;
    private final double kF2 = 0;
    private final int smoothing = 4;
    private double targetPos;
    private double turningValue;
    private double lockedDistance;


    public AutoDriveBase(final WPI_TalonSRX motorLeft1, final WPI_TalonSRX motorLeft2, final WPI_TalonSRX motorRight1,
                        final WPI_TalonSRX motorRight2) {
                        
        m_motorLeft1 = motorLeft1;
        m_motorLeft2 = motorLeft2;
        m_motorRight1 = motorRight1;
        m_motorRight2 = motorRight2;

        m_motorLeft1.setSafetyEnabled(false);
        m_motorLeft2.setSafetyEnabled(false);
        m_motorRight1.setSafetyEnabled(false);
        m_motorRight2.setSafetyEnabled(false);

        m_motorLeft2.follow(m_motorRight1);
        m_motorRight2.follow(m_motorRight1);

		/* Disable all motor controllers */
		m_motorRight1.set(ControlMode.PercentOutput, 0);
        m_motorLeft1.set(ControlMode.PercentOutput, 0);

		/* Factory Default all hardware to prevent unexpected behaviour */
		m_motorRight1.configFactoryDefault();
		m_motorLeft1.configFactoryDefault();
		
		/* Set Neutral Mode */
		m_motorLeft1.setNeutralMode(NeutralMode.Brake);
		m_motorRight1.setNeutralMode(NeutralMode.Brake);
		
		/** Feedback Sensor Configuration */
		
		/* Configure the left Talon's selected sensor as local QuadEncoder */
		m_motorLeft1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,		// Local Feedback Source
												    0,					            // PID Slot for Source [0, 1]
													30);					        // Configuration Timeout

		/* Configure the Remote Talon's selected sensor as a remote sensor for the right Talon */
		m_motorRight1.configRemoteFeedbackFilter(m_motorLeft1.getDeviceID(),					// Device ID of Source
												RemoteSensorSource.TalonSRX_SelectedSensor,	    // Remote Feedback Source
												0,							                    // Source number [0, 1]
												30);						                    // Configuration Timeout
		
		/* Setup Sum signal to be used for Distance */
		m_motorRight1.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, 30);				// Feedback Device of Remote Talon
		m_motorRight1.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.CTRE_MagEncoder_Relative, 30);	// Quadrature Encoder of current Talon
		
		/* Setup Difference signal to be used for Turn */
		m_motorRight1.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor0, 30);
		m_motorRight1.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.CTRE_MagEncoder_Relative, 30);
		
		/* Configure Sum [Sum of both QuadEncoders] to be used for Primary PID Index */
		m_motorRight1.configSelectedFeedbackSensor(	FeedbackDevice.SensorSum, 
													0,
													30);
		
		/* Scale Feedback by 0.5 to half the sum of Distance */
		m_motorRight1.configSelectedFeedbackCoefficient(0.5, 						    // Coefficient
														0,		                        // PID Slot of Source 
														30);		                    // Configuration Timeout
		
		/* Configure Difference [Difference between both QuadEncoders] to be used for Auxiliary PID Index */
		m_motorRight1.configSelectedFeedbackSensor(	FeedbackDevice.SensorDifference, 
													1, 
													30);
		
		/* Scale the Feedback Sensor using a coefficient */
		m_motorRight1.configSelectedFeedbackCoefficient(1,
														1, 
														30);
		/* Configure output and sensor direction */
		m_motorLeft1.setInverted(false);
		m_motorLeft1.setSensorPhase(true);
		m_motorRight1.setInverted(true);
		m_motorRight1.setSensorPhase(true);
		
		/* Set status frame periods to ensure we don't have stale data */
		m_motorRight1.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, 30);
		m_motorRight1.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, 30);
		m_motorRight1.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, 30);
		m_motorRight1.setStatusFramePeriod(StatusFrame.Status_10_Targets, 20, 30);
		m_motorLeft1.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, 30);

		/* Configure neutral deadband */
		m_motorRight1.configNeutralDeadband(0.001, 30);
		m_motorLeft1.configNeutralDeadband(0.001, 30);
		
		/* Motion Magic Configurations */
		m_motorRight1.configMotionAcceleration(2000, 30);
		m_motorRight1.configMotionCruiseVelocity(2000, 30);

		/**
		 * Max out the peak output (for all modes).  
		 * However you can limit the output of a given PID object with configClosedLoopPeakOutput().
		 */
		m_motorLeft1.configPeakOutputForward(+1.0, 30);
		m_motorLeft1.configPeakOutputReverse(-1.0, 30);
		m_motorRight1.configPeakOutputForward(+1.0, 30);
		m_motorRight1.configPeakOutputReverse(-1.0, 30);

		/* FPID Gains for distance servo */
		m_motorRight1.config_kP(0, kP1, 30);
		m_motorRight1.config_kI(0, kI1, 30);
		m_motorRight1.config_kD(0, kD1, 30);
		m_motorRight1.config_kF(0, kF1, 30);
		m_motorRight1.config_IntegralZone(0, 100, 30);
		m_motorRight1.configClosedLoopPeakOutput(0, 0.5, 30);
		m_motorRight1.configAllowableClosedloopError(0, 0, 30);

		/* FPID Gains for turn servo */
		m_motorRight1.config_kP(1, kP2, 30);
		m_motorRight1.config_kI(1, kI2, 30);
		m_motorRight1.config_kD(1, kD2, 30);
		m_motorRight1.config_kF(1, kF2, 30);
		m_motorRight1.config_IntegralZone(1, 200, 30);
		m_motorRight1.configClosedLoopPeakOutput(1, 1, 30);
		m_motorRight1.configAllowableClosedloopError(1, 0, 30);

		/**
		 * 1ms per loop.  PID loop can be slowed down if need be.
		 * For example,
		 * - if sensor updates are too slow
		 * - sensor deltas are very small per update, so derivative error never gets large enough to be useful.
		 * - sensor movement is very slow causing the derivative error to be near zero.
		 */
		int closedLoopTimeMs = 1;
		m_motorRight1.configClosedLoopPeriod(0, closedLoopTimeMs, 30);
		m_motorRight1.configClosedLoopPeriod(1, closedLoopTimeMs, 30);

		/**
		 * configAuxPIDPolarity(boolean invert, int timeoutMs)
		 * false means talon's local output is PID0 + PID1, and other side Talon is PID0 - PID1
		 * true means talon's local output is PID0 - PID1, and other side Talon is PID0 + PID1
		 */
		m_motorRight1.configAuxPIDPolarity(false, 30);

		/* Initialize */

		m_motorRight1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_Targets, 10);
        m_motorLeft1.getSensorCollection().setQuadraturePosition(0, 30);
        m_motorRight1.getSensorCollection().setQuadraturePosition(0, 30);
        m_motorRight1.configMotionSCurveStrength(smoothing);
    }
    public void setDistance(double distance){
        targetPos =  distance * 4096 / 18.85 / 2; //inches
        SmartDashboard.putNumber("target", targetPos);

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
    public void drive(){
        SmartDashboard.putNumber("targetPos", targetPos);
        turningValue = m_motorRight1.getSelectedSensorPosition(1);
        lockedDistance = m_motorRight1.getSelectedSensorPosition(0);
        m_motorRight1.selectProfileSlot(0, 0);
        m_motorRight1.selectProfileSlot(1, 1);
        m_motorRight1.set(ControlMode.MotionMagic, targetPos, DemandType.AuxPID, turningValue);
        m_motorLeft1.follow(m_motorRight1, FollowerType.AuxOutput1);
    }
    public void readSensors(){
        SmartDashboard.putNumber("Sensor left", m_motorLeft1.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Sensor right", m_motorRight1.getSelectedSensorVelocity());
    }

    public void move(){
        m_motorLeft1.set(ControlMode.MotionMagic, targetPos);
    }
} 
