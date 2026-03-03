package frc.penrobo.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.penrobo.subsystem.Intake;

public class RetractIntake extends Command {
    private final Intake intake;

    public RetractIntake(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.retract();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
