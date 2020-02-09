package frc.robot.subsystems;

import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {

	private CANSparkMax telescoping_Winch = new CANSparkMax(Constants.Motors.TELEWINCH, MotorType.kBrushed);
	private CANSparkMax climb_Winch = new CANSparkMax(Constants.Motors.CLIMBWINCH, MotorType.kBrushed);

	public void TeleUp(double speed) {
		telescoping_Winch.set(speed);
	}

	public void TeleDown(double speed) {
		telescoping_Winch.set(-speed);
	}

	public void ClimbUp() {
		climb_Winch.set(1);
	}

	public void ClimbDown() {
		climb_Winch.set(-1);
	}
}
