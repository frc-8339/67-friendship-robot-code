package frc.penrobo.subsystem;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexing extends SubsystemBase {
    private final SparkMax indexerMotor;

    public Indexing(SparkMax indexerMotor) {
        this.indexerMotor = indexerMotor;
        indexerMotor.setInverted(true);
    }

    public void feedShooter() {
        indexerMotor.set(0.5);
    }

    public void stop() {
        indexerMotor.stopMotor();
    }
}
