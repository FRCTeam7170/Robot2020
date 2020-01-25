/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final static class Motors {
        public static final double SPEED = 1;
        public static final int MOTOR_LEFT_1 = 14;
        public static final int MOTOR_LEFT_2 = 15;
        public static final int MOTOR_RIGHT_1 = 11;
        public static final int MOTOR_RIGHT_2 = 12;
        public static final int FLYWHEEL_1 = 12;
        public static final int FLYWHEEL_2 = 13;
        public static final int INTAKEWHEEL = 16;
        public static final int SOLNAOID_1 = 16;
    }

    public final static class Controller {
        public static final int CONTROLLER_PORT = 0; 
        public static final int RIGHT_STICK_X = 4; 
        public static final int RIGHT_STICK_Y = 5; 
        public static final int LEFT_STICK_X = 0; 
        public static final int LEFT_STICK_Y = 1; 
        public static final int RIGHT_TRIGGER = 3; 
        public static final int LEFT_TRIGGER = 2; 
        public static final int RIGHT_BUMPER = 6; 
        public static final int LEFT_BUMPER = 5;
        public static final int BUTTON_A = 1; 
        public static final int BUTTON_B = 2; 
        public static final int BUTTON_X = 3; 
        public static final int BUTTON_Y = 4; 
        public static final int BUTTON_MENU = 8; 
        public static final int BUTTON_START = 7; 
    }
    public final static class Measurements {

        public static final double WHEEL_CIRCUMFERENCE = 6 * Math.PI; //inch
        public static final double WHEEL_MOVE_TICK = WHEEL_CIRCUMFERENCE / 4096; //inch, ~0.0046
        public static final double AUTO_DRIVE_DISTANCE = 24; //inch
    }
    public final static class Autonomous {
        public final static int REMOTE_0 = 0;
        public final static int REMOTE_1 = 1;
        /* We allow either a 0 or 1 when selecting a PID Index, where 0 is primary and 1 is auxiliary */
        public final static int PID_PRIMARY = 0;
        public final static int PID_TURN = 1;
        /* Firmware currently supports slots [0, 3] and can be used for either PID Set */
        public final static int SLOT_0 = 0;
        public final static int SLOT_1 = 1;
        public final static int SLOT_2 = 2;
        public final static int SLOT_3 = 3;
        /* ---- Named slots, used to clarify code ---- */
        public final static int kSlot_Distanc = SLOT_0;
        public final static int kSlot_Turning = SLOT_1;
        public final static int kSlot_Velocit = SLOT_2;
        public final static int kSlot_MotProf = SLOT_3;
    }
}