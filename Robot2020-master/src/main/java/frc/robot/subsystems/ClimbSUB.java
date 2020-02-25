package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSUB extends SubsystemBase {
	private final CANSparkMax climb_Winch = new CANSparkMax(Constants.Motors.CLIMBWINCH, MotorType.kBrushed);

	public void ClimbUp() {
		climb_Winch.set(1);
	}

	public void ClimbDown() {
		climb_Winch.set(-1);
	}
}
