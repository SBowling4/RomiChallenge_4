// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/** An example command that uses an example subsystem. */
public class RomiCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final RomiDrivetrain subsystem;
  private double time;
  private double speed;
  private double distance;

  /**
   * USE EITHER DISTANCE OR TIME, NOT BOTH. SET ONE OF THEM AS 0.
   * 
   * @param subsystem just type in "romi" here
   * @param speed speed of the bot (-`1, 1)
   * @param time time (in seconds) the bot will run
   * @param distance distance (in inches) the bot will move
   * 
   */
  public RomiCommand(RomiDrivetrain subsystem, double speed, double time, double distance) {
    this.subsystem = subsystem;
    this.time = time;
    this.speed = speed;
    this.distance = distance;

    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    subsystem.setBothSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (time != 0) {
      return new WaitCommand(time).isFinished();
    } else if (distance != 0) {
      if (subsystem.getLeftDistanceInch() > distance) {
        return true;
      }
    }
    return false;
  }
}
