// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.penrobo;

import java.io.File;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.pathplanner.lib.auto.AutoBuilder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.penrobo.Constants.OperatorConstants;
import frc.penrobo.commands.ExtrudeLift;
import frc.penrobo.commands.IntakeCommand;
import frc.penrobo.commands.OuttakeCommand;
import frc.penrobo.commands.RampUpShooter;
import frc.penrobo.commands.Shoot;
import frc.penrobo.commands.SpinIndex;
import frc.penrobo.subsystem.Indexing;
import frc.penrobo.subsystem.Intake;
import frc.penrobo.subsystem.Lift;
import frc.penrobo.subsystem.Shooter;
import frc.penrobo.subsystem.SwerveSubsystem;
import swervelib.SwerveInputStream;

public class RobotContainer {
  private final SendableChooser<Command> autoChooser;

  private final CJoystick joystick = new CJoystick(OperatorConstants.driverControllerPort);
  private final CommandJoystick secondJoystick = new CommandJoystick(OperatorConstants.secondaryControllerPort);

  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem(
      new File(Filesystem.getDeployDirectory(), "swerve"));

  private final SwerveInputStream driveAngularVelocity = SwerveInputStream.of(swerveSubsystem.getSwerveDrive(),
      () -> -joystick.getY(),
      () -> -joystick.getX())
      .withControllerRotationAxis(() -> -joystick.getZ())
      .deadband(OperatorConstants.deadband)
      .scaleTranslation(0.8)
      .allianceRelativeControl(true);

  private final Shooter shooter = new Shooter(
      new SparkFlex(10, SparkFlex.MotorType.kBrushless),
      new SparkMax(11, SparkMax.MotorType.kBrushless));

  private final Intake intake = new Intake(
      new SparkMax(12, SparkMax.MotorType.kBrushless),
      new SparkMax(13, SparkMax.MotorType.kBrushless));

  private final Indexing indexing = new Indexing(
      new SparkMax(14, SparkMax.MotorType.kBrushless));

  private final Lift lift = new Lift(
      new SparkFlex(15, SparkFlex.MotorType.kBrushless),
      new VictorSPX(16));

  public RobotContainer() {
    // Point the parser at your deploy directory
    // YAGSL reads the JSONs and builds the entire kinematics engine for you

    configureBindings();
    DriverStation.silenceJoystickConnectionWarning(true);

    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Chooser", autoChooser);

    // Rotation3d gyroOffset = new Rotation3d(0, 0, Math.toRadians(180));

    // swerveSubsystem.getSwerveDrive().setGyroOffset(gyroOffset);
  }

  private void configureBindings() {
    Command swerveDriveCommand = swerveSubsystem.driveFieldOriented(driveAngularVelocity);
    swerveSubsystem.setDefaultCommand(swerveDriveCommand);

    new Trigger(() -> joystick.getRawButton(3)).whileTrue(swerveSubsystem.driveForward());
    new Trigger(() -> joystick.getRawButton(4))
        .whileTrue(new RunCommand(() -> swerveSubsystem.drive(new Translation2d(-1, 0), 0, false), swerveSubsystem));
    new Trigger(() -> joystick.getRawButton(5))
        .whileTrue(new RunCommand(() -> swerveSubsystem.drive(new Translation2d(0, 1), 0, false), swerveSubsystem));
    new Trigger(() -> joystick.getRawButton(6))
        .whileTrue(new RunCommand(() -> swerveSubsystem.drive(new Translation2d(0, -1), 0, false), swerveSubsystem));

    new Trigger(() -> joystick.getRawButton(2)).whileTrue(new Shoot(shooter));
    new Trigger(() -> joystick.getRawButton(1)).onTrue(new RampUpShooter(shooter));

    new Trigger(() -> joystick.getPOV() == 0).whileTrue(new IntakeCommand(intake));
    new Trigger(() -> joystick.getPOV() == 180).whileTrue(new OuttakeCommand(intake));

    secondJoystick.button(3).whileTrue(new ExtrudeLift(lift));
    secondJoystick.button(4).whileTrue(new SpinIndex(indexing));
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

  public void periodic() {
  }
}