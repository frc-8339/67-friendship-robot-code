package frc.penrobo.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.penrobo.subsystem.Lift;

public class RetractLift extends Command {
    private final Lift lift;

    public RetractLift(Lift lift) {
        this.lift = lift;
        addRequirements(lift);
    }

    @Override
    public void initialize() {
        lift.preciseRetract();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
