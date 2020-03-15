package frc.robot.subsystems;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeWheelSUB extends SubsystemBase {
	private final WPI_TalonSRX m_motor = new WPI_TalonSRX(Constants.Motors.INTAKEWHEEL);

	public IntakeWheelSUB() {
		m_motor.configFactoryDefault();

		m_motor.setInverted(false);
		
		m_motor.setNeutralMode(NeutralMode.Coast);

		m_motor.configPeakOutputForward(1, Constants.Autonomous.TIMEOUT);
		m_motor.configPeakOutputReverse(-1, Constants.Autonomous.TIMEOUT);

		m_motor.configPeakCurrentLimit(20, Constants.Autonomous.TIMEOUT);
		m_motor.configContinuousCurrentLimit(15, Constants.Autonomous.TIMEOUT);
		m_motor.enableCurrentLimit(true);
	}

	public void bangbang() {
		m_motor.set(ControlMode.PercentOutput, 1);
	}

	public void reverse() {
		m_motor.set(ControlMode.PercentOutput, -1);
	}

	public void stop() {
		m_motor.set(ControlMode.PercentOutput, 0);
	}
}