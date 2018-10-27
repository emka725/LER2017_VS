package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Pivot;

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
		if (Robot.pivot.getTargetPosition() == Pivot.ANGLE_UP) {
			Robot.roller.pullGears();
		}
		Robot.gearIntake.intake();
	}
	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.gearIntake.stop();
		Robot.roller.stop();
	}

	protected void interrupted() {
		end();
	}
}
