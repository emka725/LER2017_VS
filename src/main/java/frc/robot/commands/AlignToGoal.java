package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AlignToGoal extends Command {
	
	private double x_mean;
	private double y_mean;
	
	private double x_grip_return[];
	private double y_grip_return[];
	
	private double contours_found;
	
	private final double X_1_TARGET = 320;
	private final double Y_1_TARGET = 240;
	
	private final double X_2_TARGET = 320;
	private final double Y_2_TARGET = 220;
	
	private final double X_TARGET_MEAN = (X_1_TARGET + X_2_TARGET) / 2;
	private final double Y_TARGET_MEAN = (Y_1_TARGET + Y_2_TARGET) / 2;
	
	private final double MAX_SPEED = 0.3;
	private final double X_MULTIPLIER = 0.01;
	private final double Y_MULTIPLIER = 0.01;
	private final double X_DEADBAND = 100;
	private final double Y_DEADBAND = 50;
	
	NetworkTable contours_report;
	
	private double l_output;
	private double r_output;
	
	private enum Stage {ALIGN, DISTANCE, RE_ALIGN, FINISHED};
	
	private Stage current_stage = Stage.ALIGN;
	
	private boolean grip_successful = false;

    public AlignToGoal() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }
    
    protected void getGripData() {
    	try {
			//TODO: Mr Wood: change this to new network table implementation as in Shooterless branch
    		double[] defaultValue=new double[0];
    		contours_report = NetworkTable.getTable("GRIP");
    		
    		x_grip_return = contours_report.getNumberArray("centerX", defaultValue);
        	y_grip_return = contours_report.getNumberArray("centerY", defaultValue);
        	
        	contours_found = x_grip_return.length;
        	
        	grip_successful = contours_found > 0;
        	
        	x_mean = 0;
        	y_mean = 0;
        	
        	if (grip_successful) {
        		for (int i = 0; i < contours_found; i++) {
        			x_mean += x_grip_return[i];
        			y_mean += y_grip_return[i];
        		}
        		x_mean /= contours_found;    
        		y_mean /= contours_found;
        	}
    	}
    	catch (Exception e) {
    		grip_successful = false;
    	}
    }
    
    protected void align() {
    	if (x_mean < X_TARGET_MEAN - X_DEADBAND / 2) {
    		l_output = -(X_TARGET_MEAN - x_mean) * X_MULTIPLIER;
    		r_output = -l_output;
    	}
    	else if (x_mean > X_TARGET_MEAN + X_DEADBAND / 2) {
    		l_output = (X_TARGET_MEAN - x_mean) * X_MULTIPLIER;
    		r_output = -(X_TARGET_MEAN - x_mean) * X_MULTIPLIER;
    	}
    	else {
    		l_output = 0;
    		r_output = 0;
    		if (current_stage == Stage.ALIGN) {
    			current_stage = Stage.DISTANCE;
    			Robot.gyro.resetAngle();
    		}
    		else if (current_stage == Stage.RE_ALIGN) {
    			current_stage = Stage.FINISHED;
    		}
    	}
    }
    
    protected void distance() {
    	if (y_mean < Y_TARGET_MEAN - Y_DEADBAND / 2) {
			l_output = -(Y_TARGET_MEAN - y_mean) * Y_MULTIPLIER;
			r_output = l_output;
		}
		else if (y_mean > Y_TARGET_MEAN + Y_DEADBAND / 2) {
			l_output = (Y_TARGET_MEAN - y_mean) * Y_MULTIPLIER;;
			r_output = l_output;
		}
		else {
			l_output = 0;
			r_output = 0;
			current_stage = Stage.RE_ALIGN;
		}
    }
    
    protected void createOutput() {
    	if (grip_successful) {
	    	switch (current_stage) {
	    	case ALIGN:
	    		align();
	    		break;
	    	case DISTANCE:
	    		distance();
	    		break;
	    	case RE_ALIGN:
	    		align();
	    		break;
	    	}
	    	
	    	if (Math.abs(l_output) > MAX_SPEED) {
	    		if (l_output < 0) {
	    			l_output = -MAX_SPEED;
	    		}
	    		else {
	    			l_output = MAX_SPEED;
	    		}
	    	}
	    	if (Math.abs(r_output) > MAX_SPEED) {
	    		if (r_output < 0) {
	    			r_output = -MAX_SPEED;
	    		}
	    		else {
	    			r_output = MAX_SPEED;
	    		}
	    	}
    	}
    	else {
    		l_output = 0;
    		r_output = 0;
    	}
    }
    
    protected void sendToDrivetrain() {	
    	if (current_stage == Stage.DISTANCE) {
//    		Robot.drivetrain.setSpeedWithGyro(l_output, r_output); 
    	}
    	else {
    		SmartDashboard.putNumber("left out", l_output);
    		SmartDashboard.putNumber("right out", r_output);

//    		Robot.drivetrain.setSpeed(l_output, r_output); 
    	}
    		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	getGripData();
    	createOutput();
    	sendToDrivetrain();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return current_stage == Stage.FINISHED;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
