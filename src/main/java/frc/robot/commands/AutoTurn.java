package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command {
	
	private final double MAX_SPEED = 0.5;
	private final double DEADBAND = 15;
	private final double MULTIPLIER = 0.013;//0.013;//0.011;
	private double angle;
	private boolean finished;
	private double left_output;
	private double right_output;
	

    public AutoTurn(double angle_in) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	angle = angle_in;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyro.resetAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(Math.abs(Robot.gyro.getAngle()) - Math.abs(angle)) < DEADBAND / 2) {
    		finished = true;
    	}
    	else {
    		right_output = MULTIPLIER * (Robot.gyro.getAngle() + angle);
    	}
    	
    	if (right_output > MAX_SPEED){
    		right_output = MAX_SPEED;
    	}
    	else if (right_output < -MAX_SPEED) {
    		right_output = -MAX_SPEED;
    	}
    	
    	
    	left_output = -right_output;
    	
    	Robot.drivetrain.setSpeed(left_output, right_output);    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
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
