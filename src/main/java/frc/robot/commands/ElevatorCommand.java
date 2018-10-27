package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Glow;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorCommand extends Command {

    public ElevatorCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.elevate_button.get() && Robot.oi.release_elevator_button.get()) {
    		Robot.elevator.slowlyElevate();
    		Robot.glow.setMode(Glow.Mode.DISCO);
    	}
    	else if (Robot.oi.elevate_button.get()) {
    		Robot.elevator.elevate();
    		Robot.glow.setMode(Glow.Mode.DISCO);
    	}
    	else if (Robot.oi.release_elevator_button.get()) {
    		Robot.elevator.release();
    	}
    	else {
    		Robot.elevator.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
}
