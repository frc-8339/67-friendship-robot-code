package frc.penrobo.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.penrobo.subsystem.Intake;

public class DropIntake extends Command {
    private final Intake intake;

    public DropIntake(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.drop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
