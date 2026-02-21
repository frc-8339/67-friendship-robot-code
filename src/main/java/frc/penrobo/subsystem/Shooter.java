package frc.penrobo.subsystem;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private final SparkFlex shooterMotor;
    private final SparkMax feedMotor;

    public Shooter(SparkFlex shooterMotor, SparkMax feedMotor) {
        this.shooterMotor = shooterMotor;
        this.feedMotor = feedMotor;
    }

    public void shoot() {
        shooterMotor.set(1);
        feedMotor.set(-0.6);
    }

    public void stop() {
        shooterMotor.set(0);
        feedMotor.set(0);
    }

}
