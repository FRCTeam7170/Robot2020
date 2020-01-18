package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class FlyWheel extends SubsystemBase{

    private WPI_TalonSRX flywheelMotor1;
    private WPI_TalonSRX flywheelMotor2;
    public final PIDController flyWheelPid;

    private final double kP = 0;
    private final double kI = 0;
    private final double kD = 0;
    public  double error = 0;

    public FlyWheel(final WPI_TalonSRX motor1, final WPI_TalonSRX motor2) {

        flywheelMotor1 = motor1;
        flywheelMotor2 = motor2;

        flywheelMotor2.follow(flywheelMotor1);
        error = flywheelMotor1.getSelectedSensorVelocity();

        flyWheelPid = new PIDController(kP, kI, kD);
    }

    public void setRPM(double targetRPM){
        flyWheelPid.setSetpoint(targetRPM);
    }

    public void PID_FlyWheel(){
        set_FlyWheel(flyWheelPid.calculate(error));
    }

    public void set_FlyWheel(double value)
    {
        flywheelMotor1.set(ControlMode.PercentOutput, value);
    }

    public void periodic() {
    }
}
