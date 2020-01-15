package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class FlyWheel extends SubsystemBase{

    private TalonSRX flywheel_Motor1;
    private TalonSRX flywheel_Motor2;
    public final PIDController flyWheelPid;

    private final double kP = 0;
    private final double kI = 0;
    private final double kD = 0;
    public  double error = 0;

    public FlyWheel(final WPI_TalonSRX motor1, final WPI_TalonSRX motor2) {

        flywheel_Motor1 = motor1;
        flywheel_Motor2 = motor2;

        error = flywheel_Motor1.getSelectedSensorVelocity();

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
        flywheel_Motor1.set(ControlMode.PercentOutput, value);
        flywheel_Motor2.set(ControlMode.PercentOutput, value);
    }

    public void periodic() {
    }
}
