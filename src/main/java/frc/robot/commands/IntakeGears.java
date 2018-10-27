package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeGears extends Command {

	double output;
	
	public IntakeGears() {
		requires(Robot.gearIntake);
	}
	
	protected void initialize() {
	}

	protected void execute() {
		Robot.gearIntake.intake();
	}
	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.gearIntake.stop();
	}

	protected void interrupted() {
		end();
	}
}
