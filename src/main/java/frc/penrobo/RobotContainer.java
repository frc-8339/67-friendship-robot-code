// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.penrobo;

import java.io.File;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.pathplanner.lib.auto.AutoBuilder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.penrobo.Constants.OperatorConstants;
import frc.penrobo.commands.ExtrudeLift;
import frc.penrobo.commands.IntakeCommand;
import frc.penrobo.commands.OuttakeCommand;
import frc.penrobo.commands.RampUpShooter;
import frc.penrobo.commands.RetractLift;
import frc.penrobo.commands.Shoot;
import frc.penrobo.commands.SpinIndex;
import frc.penrobo.subsystem.Indexing;
import frc.penrobo.subsystem.Intake;
import frc.penrobo.subsystem.Lift;
import frc.penrobo.subsystem.Shooter;
import frc.penrobo.subsystem.SwerveSubsystem;
import swervelib.SwerveInputStream;

public class RobotContainer {
    public RobotContainer() {
        // Point the parser at your deploy directory
        // YAGSL reads the JSONs and builds the entire kinematics engine for you

        configureBindings();
        DriverStation.silenceJoystickConnectionWarning(true);

        autoChooser = AutoBuilder.buildAutoChooser();

        ShuffleboardTab tab = Shuffleboard.getTab("Toby");

        tab.add("Auto Chooser", autoChooser);

        tab.addNumber("Driving Speed", () -> (-joystick.getRawAxis(3) + 1) / 2);
        tab.addNumber("Intake Lift Position", () -> (-secondJoystick.getRawAxis(3) + 1) / 2 * -8);
    }

    private final SendableChooser<Command> autoChooser;

    private final CommandJoystick joystick = new CommandJoystick(OperatorConstants.driverControllerPort);
    private final CommandJoystick secondJoystick = new CommandJoystick(OperatorConstants.secondaryControllerPort);

    private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem(
            new File(Filesystem.getDeployDirectory(), "swerve"));

    private final SwerveInputStream driveAngularVelocity = SwerveInputStream.of(swerveSubsystem.getSwerveDrive(),
            () -> -joystick.getY() * (-joystick.getRawAxis(3) + 1) / 2,
            () -> -joystick.getX() * (-joystick.getRawAxis(3) + 1) / 2)
            .withControllerRotationAxis(() -> -joystick.getZ() * (-joystick.getRawAxis(3) + 1) / 2)
            .deadband(OperatorConstants.deadband)
            .scaleTranslation(0.8)
            .allianceRelativeControl(true);

    private final Shooter shooter = new Shooter(
            new SparkFlex(10, SparkFlex.MotorType.kBrushless),
            new SparkMax(11, SparkMax.MotorType.kBrushless));

    private final Intake intake = new Intake(
            new SparkMax(12, SparkMax.MotorType.kBrushless),
            new SparkMax(14, SparkMax.MotorType.kBrushless));

    private final Indexing indexing = new Indexing(
            new SparkMax(13, SparkMax.MotorType.kBrushless));

    private final Lift lift = new Lift(
            new SparkFlex(15, SparkFlex.MotorType.kBrushless),
            new VictorSPX(16));

    private void configureBindings() {
        Command swerveDriveCommand = swerveSubsystem.driveFieldOriented(driveAngularVelocity);
        swerveSubsystem.setDefaultCommand(swerveDriveCommand);

        secondJoystick.button(1).whileTrue(new RampUpShooter(shooter));
        secondJoystick.button(2).whileTrue(new Shoot(shooter));

        secondJoystick.pov(180).whileTrue(new IntakeCommand(intake));
        secondJoystick.pov(0).whileTrue(new OuttakeCommand(intake));
        secondJoystick.button(3).whileTrue(new SpinIndex(indexing));

        joystick.pov(0).whileTrue(new ExtrudeLift(lift));
        joystick.pov(180).whileTrue(new RetractLift(lift));

        // secondJoystick.button(7).onTrue(new DropIntake(intake));
        // secondJoystick.button(8).onTrue(new RetractIntake(intake));
    }

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }

    public void periodic() {
        var liftSetpoint = (-secondJoystick.getRawAxis(3) + 1) / 2 * -8;
        if (liftSetpoint != intake.liftSetpoint) {
            intake.setLiftMotorPosition(liftSetpoint);
        }
    }
}