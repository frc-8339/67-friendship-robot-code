package frc.penrobo.subsystem;

import com.revrobotics.spark.SparkBase.ControlType;
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

    public void drop() {
        var controller = liftMotor.getClosedLoopController();

        controller.setSetpoint(0, ControlType.kPosition);
    }

    public void retract() {
        var controller = liftMotor.getClosedLoopController();

        controller.setSetpoint(0, ControlType.kPosition); // save starting position after technician put on field, start
                                                          // robot at 0, then drop it down. code save the position of
                                                          // the drop, and then retract back to it at end game.
    }

    public void manualLift() {
        liftMotor.set(0.5);
    }

}
