package frc.penrobo.subsystem;

import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private final SparkMax intakeMotor;
    private final SparkMax liftMotor;

    public double liftSetpoint = 0;

    public Intake(SparkMax intakeMotor, SparkMax liftMotor) {
        this.intakeMotor = intakeMotor;
        intakeMotor.setInverted(true);

        this.liftMotor = liftMotor;
    }

    public double getLiftPosition() {
        return liftMotor.getEncoder().getPosition();
    }

    public void setIntakeSpeed(double speed) {
        intakeMotor.set(speed);
    }

    public void drop() {
        var controller = liftMotor.getClosedLoopController();

        controller.setSetpoint(-8, ControlType.kPosition);
    }

    public void retract() {
        var controller = liftMotor.getClosedLoopController();

        controller.setSetpoint(0, ControlType.kPosition);
    }

    public void manualDrop() {
        liftMotor.set(0.4);
    }

    public void manualRetract() {
        liftMotor.set(-0.4);
    }

    public void stopLift() {
        liftMotor.stopMotor();
    }

    public void setLiftMotorPosition(double position) {
        var controller = liftMotor.getClosedLoopController();

        controller.setSetpoint(position, ControlType.kPosition);

        liftSetpoint = position;
    }

}
