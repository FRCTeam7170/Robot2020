package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

//import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class FlyWheel extends SubsystemBase {

	private final ShuffleboardTab FlyWheelTab = Shuffleboard.getTab("FlyWheelTab");
	private final WPI_TalonSRX flywheelMotor1 = new WPI_TalonSRX(Constants.Motors.FLYWHEEL_1);
	private final WPI_TalonSRX flywheelMotor2 = new WPI_TalonSRX(Constants.Motors.FLYWHEEL_2);
	private double m_targetRPM;
	private double rpmout;
	private final double kP = 0.001;
	private final double kI = 0.0001;
	//private final double kD = 10;
	private final double kF = 1023 / 7200;

	public FlyWheel() {
		flywheelMotor2.configFactoryDefault();
		flywheelMotor1.configFactoryDefault();
		
		flywheelMotor1.setInverted(false);
		flywheelMotor2.setInverted(false);

		flywheelMotor1.setNeutralMode(NeutralMode.Coast);
		flywheelMotor2.setNeutralMode(NeutralMode.Coast);

		/* Config sensor used for Primary PID [Velocity]*/
		flywheelMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
		 0,
		  Constants.Autonomous.TIMEOUT);
		/**
		 * Phase sensor accordingly. Positive Sensor Reading should match Green
		 * (blinking) Leds on Talon
		*/ 
		flywheelMotor1.setSensorPhase(true);

		/* Config the peak and nominal outputs*/
		//flywheelMotor1.configNominalOutputForward(0, Constants.Autonomous.TIMEOUT);
		//flywheelMotor1.configNominalOutputReverse(0, Constants.Autonomous.TIMEOUT);
		flywheelMotor1.configPeakOutputForward(1, Constants.Autonomous.TIMEOUT);
		flywheelMotor1.configPeakOutputReverse(-1, Constants.Autonomous.TIMEOUT);
		flywheelMotor2.configPeakOutputForward(1, Constants.Autonomous.TIMEOUT);
		flywheelMotor2.configPeakOutputReverse(-1, Constants.Autonomous.TIMEOUT);

		flywheelMotor1.config_kF(0, kF, Constants.Autonomous.TIMEOUT);
		flywheelMotor1.config_kP(0, kP, Constants.Autonomous.TIMEOUT);
		flywheelMotor1.config_kI(0, kI, Constants.Autonomous.TIMEOUT);
		//flywheelMotor1.config_kD(0, kD, Constants.Autonomous.TIMEOUT);	
	}

	public void setRPM(final double rpm) {
		m_targetRPM	= rpm;
	}

	public void setFlyWheel() {
		/*
		FlyWheelTab.add("kP", kP).getEntry().addListener(notification -> {
			flywheelMotor1.config_kP(0, notification.value.getDouble(), Constants.Autonomous.TIMEOUT);
		}, EntryListenerFlags.kUpdate);
		FlyWheelTab.add("kI", kI).getEntry().addListener(notification -> {
			flywheelMotor1.config_kI(0, notification.value.getDouble(), Constants.Autonomous.TIMEOUT);
		}, EntryListenerFlags.kUpdate);
		FlyWheelTab.add("kD", kD).getEntry().addListener(notification -> {
			flywheelMotor1.config_kD(0, notification.value.getDouble(), Constants.Autonomous.TIMEOUT);
		}, EntryListenerFlags.kUpdate);
		FlyWheelTab.add("kF", kF).getEntry().addListener(notification -> {
			flywheelMotor1.config_kF(0, notification.value.getDouble(), Constants.Autonomous.TIMEOUT);
		}, EntryListenerFlags.kUpdate);
		FlyWheelTab.addNumber("RPM", new DoubleSupplier() {
			public double getAsDouble() {
				return flywheelMotor2.getSelectedSensorVelocity() * 0.15;
			}
		});
		*/
		rpmout = 100 * 4096 / 600;
		flywheelMotor1.set(ControlMode.Velocity, rpmout);
		System.out.println(flywheelMotor1.getSelectedSensorVelocity());
		flywheelMotor2.follow(flywheelMotor1);
	}

	public void stop() {
		flywheelMotor2.set(ControlMode.PercentOutput, 0);
		flywheelMotor1.set(ControlMode.PercentOutput, 0);
	}
	public void spinManual(double speed){
		flywheelMotor1.set(ControlMode.PercentOutput, speed);
		flywheelMotor2.follow(flywheelMotor1);
	}
	public void spinTest(){
		flywheelMotor1.set(ControlMode.PercentOutput, 0.1);
		System.out.println(flywheelMotor1.getSelectedSensorVelocity());
		flywheelMotor2.follow(flywheelMotor1);
	}
	public void returnRPM(){
		System.out.println(flywheelMotor1.getSelectedSensorVelocity());
	}
}
