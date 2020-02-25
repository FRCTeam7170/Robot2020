package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TelescopeSUB extends SubsystemBase {

	private final WPI_TalonSRX telescoping_Winch = new WPI_TalonSRX(Constants.Motors.TELEWINCH);
	private final Counter m_counter = new Counter(6);
	private final DigitalInput m_button = new DigitalInput(7);

	public TelescopeSUB() {
		m_counter.reset();
	}

	public void TeleUp(final double speed) {
		System.out.println(m_counter.get());
		telescoping_Winch.set(ControlMode.PercentOutput, speed);
	}

	public void TeleDown(final double speed) {
		telescoping_Winch.set(ControlMode.PercentOutput, -speed);
    }
    
	public int getCounterValue(){
		return m_counter.get();
	}
	public boolean getButtonPressed(){
		return m_button.get();
	}
	public void zeroSensor(){
		m_counter.reset();
	}
}