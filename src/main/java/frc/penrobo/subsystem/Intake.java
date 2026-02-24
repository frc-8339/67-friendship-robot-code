package frc.penrobo.subsystem;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private final SparkMax intakeMotor;
    private final SparkMax liftMotor;

    public Intake(SparkMax intakeMotor, SparkMax liftMotor) {
        this.intakeMotor = intakeMotor;
        intakeMotor.setInverted(true);

        this.liftMotor = liftMotor;
    }

    public void setIntakeSpeed(double speed) {
        intakeMotor.set(speed);
    }

    // public void lift() {
    // liftMotor.set(1);
    // }

}
