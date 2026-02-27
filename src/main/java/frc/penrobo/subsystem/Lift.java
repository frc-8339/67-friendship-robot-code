package frc.penrobo.subsystem;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkFlex;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Lift extends SubsystemBase {
    private final SparkFlex extrusionMotor;

    private final VictorSPX liftMotor;

    public Lift(SparkFlex extrusionMotor, VictorSPX liftMotor) {
        this.extrusionMotor = extrusionMotor;
        this.liftMotor = liftMotor;
    }

    public void extrude() {
        extrusionMotor.set(0.1);
    }

    public void preciseExtrude() {
        var controller = extrusionMotor.getClosedLoopController();

        controller.setSetpoint(0.3, ControlType.kPosition);
    }

    public void preciseRetract() {
        var controller = extrusionMotor.getClosedLoopController();

        controller.setSetpoint(0, ControlType.kPosition);

    }

    public void stopExtrude() {
        extrusionMotor.set(0);
    }

    public void winchIn() {
        liftMotor.set(VictorSPXControlMode.PercentOutput, 0.5);
    }

    public void winchOut() {
        liftMotor.set(VictorSPXControlMode.PercentOutput, -0.5);
    }

    public void stopWinch() {
        liftMotor.set(VictorSPXControlMode.PercentOutput, 0);
    }
}
