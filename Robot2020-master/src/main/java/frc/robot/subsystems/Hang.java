package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Hang extends SubsystemBase {

    private WPI_TalonSRX telescoping_Winch;
    private WPI_TalonSRX climb_Winch;

    public Hang(final WPI_TalonSRX motor1, final WPI_TalonSRX motor2){
        telescoping_Winch = motor1;
        climb_Winch = motor2;
        telescoping_Winch.setNeutralMode(NeutralMode.Brake);
        climb_Winch.setNeutralMode(NeutralMode.Brake);
    }

    public void RaiseLiftArm(){
        telescoping_Winch.set(ControlMode.PercentOutput, 100);
        while (telescoping_Winch.getSelectedSensorPosition() < 1000){
        }
        telescoping_Winch.stopMotor();
    }

    public void LowerLiftArm(){
        telescoping_Winch.set(ControlMode.PercentOutput, -100);
        while (telescoping_Winch.getSelectedSensorPosition() > 0){
        }
        telescoping_Winch.stopMotor();
    }

    public void Climb(){
        climb_Winch.set(100);
        while (climb_Winch.getSelectedSensorPosition() < 1000){
        }
        climb_Winch.stopMotor();
    }
}
