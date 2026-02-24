package frc.penrobo.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.penrobo.subsystem.Shooter;

public class RampUpShooter extends Command {
    private final Shooter shooter;

    public RampUpShooter(Shooter shooter) {
        this.shooter = shooter;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.rampUp();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
