package frc.penrobo.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.penrobo.subsystem.Shooter;

public class Shoot extends Command {
    private final Shooter shooter;

    public Shoot(Shooter shooter) {
        this.shooter = shooter;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.shoot();
    }

    @Override
    public void execute() {
        shooter.shoot();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stop();
    }

}
