package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveBaseSUB;
import frc.robot.subsystems.IntakeLiftSUB;
import frc.robot.subsystems.IntakeWheelSUB;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeWheelCMD extends CommandBase{
    private final IntakeWheelSUB m_intakeWheel;
    private final IntakeLiftSUB m_intakeLift;
    private int driveBaseSpeed = Constants.Measurements.MIN_DRIVEBASE_SPEED;
    private int speed;

    public IntakeWheelCMD(final IntakeWheelSUB intakeWheel, final IntakeLiftSUB intakeLift){
        m_intakeWheel = intakeWheel;
        m_intakeLift = intakeLift;
        addRequirements(m_intakeWheel, intakeLift);
    }
    public void initialize() {
    }
        
    public void execute(){
        if (m_intakeLift.getState()){
            driveBaseSpeed = (int)DriveBaseSUB.getAvgSpeed();
            if (driveBaseSpeed <= Constants.Measurements.MIN_DRIVEBASE_SPEED){
                speed = Constants.Measurements.MIN_DRIVEBASE_SPEED * 12;
            } else {
                speed = driveBaseSpeed * 12;
            }
            m_intakeWheel.spin(speed);
        } else {
            m_intakeWheel.stop();
        }
    }
}