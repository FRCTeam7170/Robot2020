package frc.robot.subsystems;

import frc.robot.Constants;
import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;

public class DriveBaseSUB extends SubsystemBase {
  private final WPI_TalonSRX m_motorLeft1 = new WPI_TalonSRX(Constants.Motors.MOTOR_LEFT_1);
  private final WPI_TalonSRX m_motorLeft2 = new WPI_TalonSRX(Constants.Motors.MOTOR_LEFT_2);
  private final WPI_TalonSRX m_motorRight1 = new WPI_TalonSRX(Constants.Motors.MOTOR_RIGHT_1);
  private final WPI_TalonSRX m_motorRight2 = new WPI_TalonSRX(Constants.Motors.MOTOR_RIGHT_2);
  private double speed = Constants.Motors.SPEED;

  private final SpeedControllerGroup m_leftMotors;

  // The motors on the right side of the drive.
  private final SpeedControllerGroup m_rightMotors;

  // The robot's drive
  private final DifferentialDrive m_drive;

  // The left-side drive encoder
  private static final Encoder m_leftEncoder = new Encoder(4, 5, true, EncodingType.k4X);

  // The right-side drive encoder
  private static final Encoder m_rightEncoder = new Encoder(2, 3, false, EncodingType.k4X);

  // The gyro sensor
  private final Gyro m_gyro = new ADIS16448_IMU();

  // Odometry class for tracking robot pose
  private final DifferentialDriveOdometry m_odometry;

  /**
   * Creates a new DriveSubsystem.
   */
  public DriveBaseSUB() {
    m_motorRight1.configFactoryDefault();
    m_motorRight2.configFactoryDefault();
    m_motorLeft1.configFactoryDefault();
    m_motorLeft2.configFactoryDefault();

    m_motorLeft1.setNeutralMode(NeutralMode.Brake);
    m_motorLeft2.setNeutralMode(NeutralMode.Brake);
    m_motorRight1.setNeutralMode(NeutralMode.Brake);
    m_motorRight2.setNeutralMode(NeutralMode.Brake);

    m_motorLeft1.setInverted(true);
    m_motorLeft2.setInverted(true);
    m_motorRight1.setInverted(true);
    m_motorRight2.setInverted(true);

    m_motorLeft1.configPeakOutputForward(+speed, Constants.Autonomous.TIMEOUT);
    m_motorLeft1.configPeakOutputReverse(-speed, Constants.Autonomous.TIMEOUT);
    m_motorRight1.configPeakOutputForward(+speed, Constants.Autonomous.TIMEOUT);
    m_motorRight1.configPeakOutputReverse(-speed, Constants.Autonomous.TIMEOUT);
    m_motorLeft2.configPeakOutputForward(+speed, Constants.Autonomous.TIMEOUT);
    m_motorLeft2.configPeakOutputReverse(-speed, Constants.Autonomous.TIMEOUT);
    m_motorRight2.configPeakOutputForward(+speed, Constants.Autonomous.TIMEOUT);
    m_motorRight2.configPeakOutputReverse(-speed, Constants.Autonomous.TIMEOUT);

    m_leftMotors = new SpeedControllerGroup(m_motorLeft1, m_motorLeft2);
    m_rightMotors = new SpeedControllerGroup(m_motorRight1, m_motorRight2);
    m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

    m_drive.setSafetyEnabled(false);

    m_leftEncoder.setDistancePerPulse(Constants.Measurements.DISTANCE_PER_PULSE);
    m_rightEncoder.setDistancePerPulse(Constants.Measurements.DISTANCE_PER_PULSE);

    resetEncoders();
    zeroHeading();
    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
  }

  @Override
  public void periodic() {
    // Update the odometry in the periodic block
    m_odometry.update(Rotation2d.fromDegrees(getHeading()), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  /**
   * Controls the left and right sides of the drive directly with voltages.
   *
   * @param leftVolts  the commanded left output
   * @param rightVolts the commanded right output
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    m_leftMotors.setVoltage(leftVolts);
    m_rightMotors.setVoltage(-rightVolts);
    m_drive.feed();

  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    m_drive.tankDrive(leftSpeed, rightSpeed);
  }

  /**
   * Resets the drive encoders to currently read a position of 0.
   */
  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public Encoder getLeftEncoder() {
    return m_leftEncoder;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public Encoder getRightEncoder() {
    return m_rightEncoder;
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more
   * slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  /**
   * Zeroes the heading of the robot.
   */
  public void zeroHeading() {
    m_gyro.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  public double getHeading() {
    return Math.IEEEremainder(m_gyro.getAngle(), 360) * (Constants.Autonomous.INVERTED_GYRO ? -1.0 : 1.0);
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return m_gyro.getRate() * (Constants.Autonomous.INVERTED_GYRO ? -1.0 : 1.0);
  }

  public static double getAvgSpeed() {
    return (m_leftEncoder.getRate() + m_rightEncoder.getRate()) / 2;
  }

  public void stop() {
    m_drive.stopMotor();
  }
}
