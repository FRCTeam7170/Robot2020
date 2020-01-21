package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AutoDriveBase extends SubsystemBase{
    private final WPI_TalonSRX m_motorLeft1, m_motorLeft2, m_motorRight1, m_motorRight2;

    public AutoDriveBase(final WPI_TalonSRX motorLeft1, final WPI_TalonSRX motorLeft2, final WPI_TalonSRX motorRight1,
                        final WPI_TalonSRX motorRight2) {
                        
        m_motorLeft1 = motorLeft1;
        m_motorLeft2 = motorLeft2;
        m_motorRight1 = motorRight1;
        m_motorRight2 = motorRight2;
    }
    public void drive(){
        m_motorLeft1.set(ControlMode.Position, 0);
        m_motorLeft2.follow(m_motorLeft1);
        m_motorRight1.set(ControlMode.Position, 0);
        m_motorRight2.follow(m_motorRight1);
    }
}