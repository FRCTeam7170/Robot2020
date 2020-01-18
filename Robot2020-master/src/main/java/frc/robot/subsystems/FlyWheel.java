package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class FlyWheel extends SubsystemBase{

    private WPI_TalonSRX flywheelMotor1;
    private WPI_TalonSRX flywheelMotor2;
    private double m_targetRPM;
    private double fwdTerm = 0;

    public FlyWheel(WPI_TalonSRX motor1, WPI_TalonSRX motor2, double targetRPM){

        flywheelMotor1 = motor1;
        flywheelMotor2 = motor2;
        m_targetRPM = targetRPM;

        flywheelMotor2.follow(flywheelMotor1);

        flywheelMotor1.setNeutralMode(NeutralMode.Brake);
        flywheelMotor2.setNeutralMode(NeutralMode.Brake);

        flywheelMotor2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
        flywheelMotor1.configRemoteFeedbackFilter(flywheelMotor2.getDeviceID(),
                                                  RemoteSensorSource.TalonSRX_SelectedSensor, 0, 30);

        flywheelMotor1.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, 30);
        flywheelMotor1.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.QuadEncoder, 30);

        flywheelMotor1.configSelectedFeedbackSensor(FeedbackDevice.SensorSum, 0, 30);

        flywheelMotor1.configSelectedFeedbackCoefficient(1, 0, 30);

        flywheelMotor1.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20 ,30);
        flywheelMotor1.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, 30);
        flywheelMotor2.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, 30);

        flywheelMotor1.configNeutralDeadband(0.001, 30);
        flywheelMotor2.configNeutralDeadband(0.001, 30);

        flywheelMotor2.configPeakOutputForward(+1.0, 30);
        flywheelMotor2.configPeakOutputReverse(-1.0, 30);
        flywheelMotor1.configPeakOutputForward(+1.0, 30);
        flywheelMotor1.configPeakOutputReverse(-1.0, 30);

        flywheelMotor1.config_kP(2, 0.1, 30);
        flywheelMotor1.config_kI(2, 0.0, 30);
        flywheelMotor1.config_kD(2, 20, 30);
        flywheelMotor1.config_kF(2, 1023.0/6800.0, 30);
        flywheelMotor1.config_IntegralZone(2, 300, 30);
        flywheelMotor1.configClosedLoopPeakOutput(2, 0.75, 30);
        flywheelMotor1.configAllowableClosedloopError(2, 0, 30);

        flywheelMotor1.configClosedLoopPeriod(0, 1, 30);
        flywheelMotor1.configClosedLoopPeriod(1, 1, 30);

    }
    public void setRPM(double rpm){
        m_targetRPM = rpm;
    }
    public void set_FlyWheel(){
        double targetsPer100ms = m_targetRPM * 4096 / 600;
        flywheelMotor1.set(ControlMode.Velocity, targetsPer100ms, DemandType.ArbitraryFeedForward, fwdTerm);
    }

    public void periodic() {
    }
}
