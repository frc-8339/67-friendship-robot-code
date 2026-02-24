package frc.penrobo.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.penrobo.subsystem.Intake;

public class OuttakeCommand extends Command {
    private final Intake intake;

    public OuttakeCommand(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.setIntakeSpeed(-0.35);
    }

    @Override
    public void execute() {
        intake.setIntakeSpeed(-0.35);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        intake.setIntakeSpeed(0);
    }

}
