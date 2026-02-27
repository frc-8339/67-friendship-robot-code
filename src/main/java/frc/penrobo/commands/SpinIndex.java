package frc.penrobo.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.penrobo.subsystem.Indexing;

public class SpinIndex extends Command {
    private final Indexing indexing;

    public SpinIndex(Indexing indexing) {
        this.indexing = indexing;
        addRequirements(indexing);
    }

    @Override
    public void initialize() {
        indexing.feedShooter();
    }

    @Override
    public void execute() {
        indexing.feedShooter();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        indexing.stop();
    }

}
