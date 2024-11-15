// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RomiDrivetrain extends SubsystemBase {
  

  // The Romi has the left and right motors set to
  // PWM channels 0 and 1 respectively
  public final Spark leftMotor = new Spark(0);
  public final Spark rightMotor = new Spark(1);

  // The Romi has onboard encoders that are hardcoded
  // to use DIO pins 4/5 and 6/7 for the left and right
  public final Encoder leftEncoder = new Encoder(4, 5);
  public final Encoder rightEncoder = new Encoder(6, 7);

  // Set up the differential drive controller
  public final DifferentialDrive diffDrive =
      new DifferentialDrive(leftMotor::set, rightMotor::set);

  /** Creates a new RomiDrivetrain. */
  public RomiDrivetrain() {
    // Use inches as unit for encoder distances
    leftEncoder.setDistancePerPulse((Math.PI * Constants.kWheelDiameterInch) / Constants.kCountsPerRevolution);
    rightEncoder.setDistancePerPulse((Math.PI * Constants.kWheelDiameterInch) / Constants.kCountsPerRevolution);
    resetEncoders();

    // Invert right side since motor is flipped
    rightMotor.setInverted(true);
  }

  public void arcadeDrive(double xAxisSpeed, double zAxisRotate) {
    diffDrive.arcadeDrive(xAxisSpeed, zAxisRotate);
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getLeftDistanceInch() {
    return leftEncoder.getDistance();
  }

  public double getRightDistanceInch() {
    return rightEncoder.getDistance();
  }



  /**
   * Sets the speed of the right motor
   * @param speed speed of the motor  (-1, 1)
   */
  public void setRightSpeed(double speed) {
    rightMotor.set(speed);
  }

  /**
   * Sets the speed of the left motor
   * @param speed speed of the motor (-1, 1)
   */
  public void setLeftSpeed(double speed) {
    leftMotor.set(speed);
  }

  public void stopMotor() {
    leftMotor.stopMotor();
    rightMotor.stopMotor();
  }




  public void setBothSpeed(double speed) {
    leftMotor.set(speed);
    rightMotor.set(speed);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
