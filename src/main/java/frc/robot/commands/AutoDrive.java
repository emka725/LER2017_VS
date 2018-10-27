package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {
	
	private double left_speed = 1, right_speed = 1;
	private final double COOLDOWN_DIFFERENCE = 0.5;
	private double seconds;
	private boolean backwards = false;

    public AutoDrive(double seconds_in, double speed_in) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
//    	requires(Robot.drivetrain);
//    	setTimeout(Math.abs(seconds_in));
//    	seconds = Math.abs(seconds_in);
//    	backwards = seconds_in < 0;
//    	left_speed = speed_in;
//    	right_speed = speed_in;
    	this(seconds_in, speed_in, speed_in);
    }

	public AutoDrive(double seconds_in, double l_speed_in, double r_speed_in) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	setTimeout(Math.abs(seconds_in));
    	seconds = Math.abs(seconds_in);
    	backwards = seconds_in < 0;
    	left_speed = l_speed_in;
    	right_speed = r_speed_in;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyro.resetAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (timeSinceInitialized() > seconds) {
    		Robot.drivetrain.setSpeedWithGyro(0, 0);
    	}
    	else if (!backwards) {
    		if (left_speed == right_speed) {
	    		if (timeSinceInitialized() <= seconds - COOLDOWN_DIFFERENCE) {
	    			Robot.drivetrain.setSpeedWithGyro(left_speed, right_speed);	
	    		}
	    		else {
	    			Robot.drivetrain.setSpeedWithGyro(((seconds - timeSinceInitialized()) / COOLDOWN_DIFFERENCE) * left_speed, ((seconds - timeSinceInitialized()) / COOLDOWN_DIFFERENCE) * right_speed);
	    		}
    		}
    		else {
    			if (timeSinceInitialized() <= seconds - COOLDOWN_DIFFERENCE) {
	    			Robot.drivetrain.setSpeed(left_speed, right_speed);	
	    		}
	    		else {
	    			Robot.drivetrain.setSpeed(((seconds - timeSinceInitialized()) / COOLDOWN_DIFFERENCE) * left_speed, ((seconds - timeSinceInitialized()) / COOLDOWN_DIFFERENCE) * right_speed);
	    		}
    		}
    	}
    	else {
    		if (left_speed == right_speed) {
	    		if (timeSinceInitialized() <= seconds - COOLDOWN_DIFFERENCE) {
	        		Robot.drivetrain.setSpeedWithGyro(-left_speed, -right_speed);	
	        	}
	        	else {
	        		Robot.drivetrain.setSpeedWithGyro(((seconds - timeSinceInitialized()) / COOLDOWN_DIFFERENCE) * left_speed, ((seconds - timeSinceInitialized()) / COOLDOWN_DIFFERENCE) * right_speed);
	        	}
    		}
    		else {
    			if (timeSinceInitialized() <= seconds - COOLDOWN_DIFFERENCE) {
	        		Robot.drivetrain.setSpeed(-left_speed, -right_speed);	
	        	}
	        	else {
	        		Robot.drivetrain.setSpeed(((seconds - timeSinceInitialized()) / COOLDOWN_DIFFERENCE) * left_speed, ((seconds - timeSinceInitialized()) / COOLDOWN_DIFFERENCE) * right_speed);
	        	}
    		}
    	}
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeSinceInitialized() > seconds;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
