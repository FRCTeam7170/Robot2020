/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
	public final static class Motors {
		public static final double SPEED = 1;
		public static final int INDEXER = 18; // Talon
		public static final int FLYWHEEL_1 = 16; // Talon
		public static final int FLYWHEEL_2 = 17; // Talon
		public static final int INTAKEWHEEL = 12; // Talon
		public static final int MOTOR_LEFT_1 = 14; // Talon
		public static final int MOTOR_LEFT_2 = 15; // Talon
		public static final int MOTOR_RIGHT_1 = 10; // Talon
		public static final int MOTOR_RIGHT_2 = 11; // Talon
		public static final int TELEWINCH = 21; // SparkMax
		public static final int CLIMBWINCH = 20; // SparkMax
	}

	public final static class Pneumatics {
		public static final int SOLENOID_1_ON = 0;
		public static final int SOLENOID_1_OFF = 0;
	}

	public final static class Controller {
		public static final int CONTROLLER_PORT = 0;
		public static final int BUTTON_A = 1;
		public static final int BUTTON_B = 2;
		public static final int BUTTON_X = 3;
		public static final int BUTTON_Y = 4;
		public static final int LEFT_STICK_X = 0;
		public static final int LEFT_STICK_Y = 1;
		public static final int RIGHT_STICK_X = 4;
		public static final int RIGHT_STICK_Y = 5;
		public static final int LEFT_TRIGGER = 2;
		public static final int RIGHT_TRIGGER = 3;
		public static final int LEFT_BUMPER = 5;
		public static final int RIGHT_BUMPER = 6;
		public static final int BUTTON_START = 7;
		public static final int BUTTON_MENU = 8;
	}

	public final static class Measurements {
		public static final int ENCODER_PULSE = 1024;
		public static final double WHEEL_CIRCUMFERENCE = 6 * Math.PI; // inch
		public static final double AUTO_DRIVE_DISTANCE = 24; // inch
		public static final double SVOLTS = 0.00;
		public static final double SVOLT_SECOND_PER_METER = 0.00;
		public static final double SVOLT_SECOND_PER_METER_SQUARED = 0.00;
		public static final double KP_DRIVE_VELOCITY = 0.00;
		public static final double TRACKWIDTH = 0.44; // meters
		public static final double WHEELDIAMETER = 0.152; // meters
		public static final double MAX_SPEED = 3; // m/s
		public static final double MAX_ACCELERATION = 3; // m/s²
		public static final double RAMSETE_B = 2;
		public static final double RAMSETE_ZETA = 0.7;
		public static final double WHEEL_MOVE_TICK = WHEEL_CIRCUMFERENCE / 4096; // inch, ~0.0046
		public static final double DISTANCE_PER_PULSE = (WHEELDIAMETER * Math.PI) / (double) ENCODER_PULSE;
		public static final DifferentialDriveKinematics DRIVE_KINEMATICS = new DifferentialDriveKinematics(TRACKWIDTH);
	}

	public final static class Autonomous {
		public final static int TIMEOUT = 30;
		public final static int REMOTE_0 = 0;
		public final static int REMOTE_1 = 1;
		public final static int PID_PRIMARY = 0;
		public final static int PID_TURN = 1;
		public final static int SLOT_0 = 0;
		public final static int SLOT_1 = 1;
		public final static int SLOT_2 = 2;
		public final static int SLOT_3 = 3;
		public final static int kSlot_Distanc = 0;
		public final static int kSlot_Turning = 1;
		public final static int kSlot_Velocit = 2;
		public final static int kSlot_MotProf = 3;
		public final static boolean INVERTED_GYRO = false;
	}
}