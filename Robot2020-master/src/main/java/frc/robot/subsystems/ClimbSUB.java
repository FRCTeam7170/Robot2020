package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSUB extends SubsystemBase {
	private final VictorSPX climb_Winch = new VictorSPX(Constants.Motors.CLIMBWINCH);

	public ClimbSUB(){
		climb_Winch.setNeutralMode(NeutralMode.Brake);
	}
	public void ClimbUp() {
		climb_Winch.set(ControlMode.PercentOutput, 1);
	}
	
	public void ClimbDown() {
		climb_Winch.set(ControlMode.PercentOutput, -1);
	}

	public void stop() {
		climb_Winch.set(ControlMode.PercentOutput, 0);
	}
}
