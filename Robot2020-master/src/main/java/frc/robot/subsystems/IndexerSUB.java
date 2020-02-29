package frc.robot.subsystems;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexerSUB extends SubsystemBase {
	private final TalonSRX m_motor = new TalonSRX(Constants.Motors.INDEXER);
	private final double kP = 0.0025;
	private final double kI = 0.001;
	private final double kD = 2;
	private final double kF = 1023 / 7200;
	private int m_targetRPM;

	public IndexerSUB() {
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

	public void setRPM(final int rpm) {
		m_targetRPM = rpm;
	}

	public void stop() {
		m_motor.set(ControlMode.PercentOutput, 0);
		m_targetRPM = 0;
	}

	public void setIndexer() {
		final double rpmout = m_targetRPM * 4096 / 600;
		m_motor.set(ControlMode.Velocity, rpmout);
	}
	public void spinTest(double speed){
		m_motor.set(ControlMode.PercentOutput, speed);
	}
}