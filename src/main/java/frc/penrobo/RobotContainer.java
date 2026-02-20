// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.penrobo;

import java.io.File;

import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.penrobo.Constants.OperatorConstants;
import frc.penrobo.subsystem.SwerveSubsystem;
import swervelib.SwerveInputStream;

public class RobotContainer {
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  private AnalogEncoder encoder1 = new AnalogEncoder(0);
  private AnalogEncoder encoder2 = new AnalogEncoder(1);
  private AnalogEncoder encoder3 = new AnalogEncoder(2);
  private AnalogEncoder encoder4 = new AnalogEncoder(3);

  private final CJoystick joystick = new CJoystick(OperatorConstants.driverControllerPort);

  private SwerveSubsystem swerveSubsystem = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));

  SwerveInputStream driveAngularVelocity = SwerveInputStream.of(swerveSubsystem.getSwerveDrive(),
      () -> joystick.getY() * -1,
      () -> joystick.getX() * -1)
      .withControllerRotationAxis(joystick::getZ)
      .deadband(OperatorConstants.deadband)
      .scaleTranslation(0.8)
      .allianceRelativeControl(true);

  public RobotContainer() {
    // Point the parser at your deploy directory
    // YAGSL reads the JSONs and builds the entire kinematics engine for you

    configureBindings();
    DriverStation.silenceJoystickConnectionWarning(true);

    SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  private void configureBindings() {
    Command swerveDriveCommand = swerveSubsystem.driveFieldOriented(driveAngularVelocity);
    swerveSubsystem.setDefaultCommand(swerveDriveCommand);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  long lastLogTime = 0;

  /**
   * Encoder 1 angle: 0.718789, Encoder 2 angle: 0.832463, Encoder 3 angle:
   * 0.249788, Encoder 4 angle: 0.602895
   */

  public void periodic() {
    if (System.currentTimeMillis() - lastLogTime > 300) {
      String output = String.format(
          "Encoder 1 angle: %f, Encoder 2 angle: %f, Encoder 3 angle: %f, Encoder 4 angle: %f",
          encoder1.get() - 0.718789, encoder2.get() - 0.832463, encoder3.get() - 0.249788, encoder4.get() - 0.602895);

      System.out.println(output);
      lastLogTime = System.currentTimeMillis();
    }
  }
}