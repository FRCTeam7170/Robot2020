package frc.robot.subsystems;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexerSUB extends SubsystemBase {
	private final TalonSRX m_motor = new TalonSRX(Constants.Motors.INDEXER);

	public IndexerSUB() {
		m_motor.configFactoryDefault();
		m_motor.setInverted(false);
		m_motor.setNeutralMode(NeutralMode.Brake);
	}

	public void bangbang() {
		m_motor.set(ControlMode.PercentOutput, 0.35);
	}

	public void stop() {
		m_motor.set(ControlMode.PercentOutput, 0);
	}
}