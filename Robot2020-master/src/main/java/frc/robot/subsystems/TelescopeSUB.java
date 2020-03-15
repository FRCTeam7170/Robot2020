package frc.robot.subsystems;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TelescopeSUB extends SubsystemBase {
	private final WPI_TalonSRX telescopeMotor = new WPI_TalonSRX(Constants.Motors.TELEWINCH);

	public TelescopeSUB() {
		telescopeMotor.configFactoryDefault();
		telescopeMotor.setInverted(true);
	}

	public void move(final double speed) {
		telescopeMotor.set(ControlMode.PercentOutput, speed);
	}

	public void stop(){
		telescopeMotor.set(ControlMode.PercentOutput, 0);
	}
}