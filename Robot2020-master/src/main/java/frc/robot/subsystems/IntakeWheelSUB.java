package frc.robot.subsystems;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeWheelSUB extends SubsystemBase {
	private final WPI_TalonSRX m_motor = new WPI_TalonSRX(Constants.Motors.INTAKEWHEEL);
	private final double kP = 0.25;
	private final double kI = 0.00005;
	private final double kD = 2;
	private final double kF = 1023 / 7200;
	private double m_speed;

	public IntakeWheelSUB(){
		m_motor.configFactoryDefault();
		
		m_motor.setInverted(true);

		m_motor.setNeutralMode(NeutralMode.Coast);

		/* Config sensor used for Primary PID [Velocity]*/
		m_motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
		 0,
		  Constants.Autonomous.TIMEOUT);

		m_motor.setSensorPhase(false);

		m_motor.configPeakOutputForward(1, Constants.Autonomous.TIMEOUT);
		m_motor.configPeakOutputReverse(-1, Constants.Autonomous.TIMEOUT);

		m_motor.config_kF(0, kF, Constants.Autonomous.TIMEOUT);
		m_motor.config_kP(0, kP, Constants.Autonomous.TIMEOUT);
		m_motor.config_kI(0, kI, Constants.Autonomous.TIMEOUT);
		m_motor.config_kD(0, kD, Constants.Autonomous.TIMEOUT);
	}

	public void stop() {
		m_motor.set(ControlMode.PercentOutput, 0);
		m_speed = 0;
	}

	public void spin(double speed) {
		m_speed = speed / 600;
		m_motor.set(ControlMode.Velocity, m_speed);
	}
	public void test(double speed){
		m_motor.set(ControlMode.PercentOutput, speed);
		System.out.println(m_motor.getSelectedSensorVelocity());
	}
}