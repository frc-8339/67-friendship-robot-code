package frc.penrobo.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.penrobo.subsystem.Lift;

public class ExtrudeLift extends Command {
    private final Lift lift;

    public ExtrudeLift(Lift lift) {
        this.lift = lift;
        addRequirements(lift);
    }

    @Override
    public void initialize() {
        lift.extrude();
    }

    @Override
    public void execute() {
        lift.extrude();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        lift.stopExtrude();
    }

}
