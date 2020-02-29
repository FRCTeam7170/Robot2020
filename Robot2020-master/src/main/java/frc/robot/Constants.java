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
		public static final int PNEUMATIC_CONTROL_PANEL = 6; //PCM
		public static final int MOTOR_RIGHT_1 = 10; // Talon
		public static final int MOTOR_RIGHT_2 = 11; // Talon
		public static final int MOTOR_LEFT_1 = 12; // Talon
		public static final int MOTOR_LEFT_2 = 13; // Talon
		public static final int INTAKEWHEEL = 14; // Talon
		public static final int FLYWHEEL_1 = 15; // Talon
		public static final int FLYWHEEL_2 = 16; // Talon
		public static final int INDEXER = 17; // Talon
		public static final int TELEWINCH = 18; // Talon
		public static final int CLIMBWINCH = 19; // Vicotr
	}

	public final static class Pneumatics {
		public static final int SOLENOID_1_ON = 0;
		public static final int SOLENOID_1_OFF = 1;
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
		public static final int ENCODER_PULSE = 4096;
		public static final double SVOLTS = 1.07;
		public static final double SVOLT_SECOND_PER_METER = 2.95;
		public static final double SVOLT_SECOND_PER_METER_SQUARED = 0.0124;
		public static final double KP_DRIVE_VELOCITY = 0.0254;
		public static final double TRACKWIDTH = 0.44; // meters
		public static final double WHEELDIAMETER = 0.152; // meters
		public static final double MAX_SPEED = 1.5; // m/s
		public static final double MAX_ACCELERATION = 1.5; // m/s²
		public static final double RAMSETE_B = 2;
		public static final double RAMSETE_ZETA = 0.7;
		public static final double DISTANCE_PER_PULSE = (WHEELDIAMETER * Math.PI) / (double) ENCODER_PULSE;
		public static final DifferentialDriveKinematics DRIVE_KINEMATICS = new DifferentialDriveKinematics(TRACKWIDTH);

		public static final double LIMELIGHT_HEIGHT = 0.2; //m
		public static final double LIMELIGHT_ANGLE = 35; //degrees
		public static final double TARGET_HEIGHT = 2.; //m

		public static final double AIR_DENSITY = 1.2; //kg/m³
		public static final double BALL_AREA = Math.sqrt(0.89) * Math.PI; //m²
		public static final double BALL_DRAG = 0.47;

		public static final double MIN_DRIVEBASE_SPEED = 500;

		public static final double SHOOTING_RANGE = 6.7; //m
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
		public final static boolean INVERTED_GYRO = true;
	}
}