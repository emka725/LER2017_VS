package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollIntake extends Command {

    public RollIntake() {
    	requires(Robot.roller);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.roller.intake();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.roller.stop();
    }

    protected void interrupted() {
    	end();
    }
}
