package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveBaseSUB;
import frc.robot.subsystems.IntakeLiftSUB;
import frc.robot.subsystems.IntakeWheelSUB;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeWheelCMD extends CommandBase{
    private final IntakeWheelSUB m_intakeWheel;
    private int driveBaseSpeed = Constants.Measurements.MIN_DRIVEBASE_SPEED;
    private int speed;

    public IntakeWheelCMD(final IntakeWheelSUB intakeWheel){
        m_intakeWheel = intakeWheel;
        addRequirements(m_intakeWheel);
    }
    public void initialize() {
    }
        
    public void execute(){
        /*
        if (IntakeLiftSUB.getState()){
            driveBaseSpeed = (int)DriveBaseSUB.getAvgSpeed();
            if (driveBaseSpeed <= Constants.Measurements.MIN_DRIVEBASE_SPEED){
                speed = Constants.Measurements.MIN_DRIVEBASE_SPEED * 12;
            } else {
                speed = driveBaseSpeed * 12;
            }
            m_intakeWheel.spin(speed);
        } else {
            m_intakeWheel.stop();
        } */
        //m_intakeWheel.spin(500 * 4096);
        if (IntakeLiftSUB.getState()){
            m_intakeWheel.bangbang();
        } else {
            m_intakeWheel.stop();
        }
    }
}