package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ReleaseSUB extends SubsystemBase {
	private boolean off = false;
	private final Solenoid m_solenoid = new Solenoid(Constants.Motors.PNEUMATIC_CONTROL_PANEL, 3);

	public ReleaseSUB() {
		m_solenoid.set(off);
	}

	public void release() {
		off = !off;
		m_solenoid.set(off);
	}

	public void off() {
		if (off) {
			off = !off;
			m_solenoid.set(off);
		}
	}
}