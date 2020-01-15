package frc.robot.commands;
import frc.robot.subsystems.FlyWheel;

public class FlyWheelSpin {
    public final FlyWheel Fly;

    public FlyWheelSpin(FlyWheel fly) {
        Fly = fly;
    }

    public void initialize() {
        Fly.setRPM(1000);
    }

    public void execute() {
        Fly.PID_FlyWheel();
    }

    public boolean isFinished() {
        return false;
    }

}
