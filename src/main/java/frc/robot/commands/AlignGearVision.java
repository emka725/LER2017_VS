package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AlignGearVision extends Command {
	
	private double x_mean;
	protected double y_mean;
	private double area_mean;
	
	private double x_grip_return[];
	private double y_grip_return[];
	//private double area_grip_return[];
	
	private double contours_found;
	
	private final double X_1_TARGET = 150;
	//private final double Y_1_TARGET = 155;
	private final double AREA_1_TARGET = 0;
	
	private final double X_2_TARGET = 170;
	//private final double Y_2_TARGET = 155;
	private final double AREA_2_TARGET = 0;
	
	private final double X_TARGET_MEAN = (X_1_TARGET + X_2_TARGET) / 2;
	//private final double Y_TARGET_MEAN = (Y_1_TARGET + Y_2_TARGET) / 2;
	private final double AREA_TARGET_MEAN = (AREA_1_TARGET + AREA_2_TARGET) / 2;
	
	private final double MAX_SPEED = 0.23;
	//private final double X_MULTIPLIER = 0.01;
	//private final double Y_MULTIPLIER = 0.01;
	private final double AREA_MULTIPLIER = 0.01;
	
	private final double X_DEADBAND = 73;
	//private final double Y_DEADBAND = 10;
	private final double AREA_DEADBAND = 10;
	
	private final double Y_THRESHOLD = 150;
	
	//NetworkTable contours_report;
	
	private double l_output;
	private double r_output;
	
	private enum Stage {ALIGN, DISTANCE, FINISHED};
	
	private Stage current_stage = Stage.ALIGN;
	
	private boolean grip_successful = false;

    public AlignGearVision() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	setTimeout(4);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	current_stage = Stage.ALIGN;

		// Set up Network Table listener to retrieve changes in centerx and centery:
		NetworkTableInstance NTInstance = NetworkTableInstance.getDefault();
		NetworkTable networkTable = NTInstance.getTable("GRIP");
		NTInstance.startClientTeam(2708);
		networkTable.addEntryListener("centerX", (table, key, entry, value, flags) -> {x_grip_return=value.getDoubleArray();}, EntryListenerFlags.kNew|EntryListenerFlags.kUpdate);
		networkTable.addEntryListener("centerY", (table, key, entry, value, flags) -> {y_grip_return=value.getDoubleArray();}, EntryListenerFlags.kNew|EntryListenerFlags.kUpdate);
    }
    
    protected void getGripData() {
		//double[] defaultValue=new double[0];
		int counter = 0;
    	try {
			//These are not needed because the Network Table listeners above automatically update the variables:
    		//contours_report = NetworkTable.getTable("GRIP");
        	//x_grip_return = contours_report.getNumberArray("centerX", defaultValue);
        	//y_grip_return = contours_report.getNumberArray("centerY", defaultValue);
        	
        	contours_found = x_grip_return.length;
        	grip_successful = contours_found > 0;
        	if (grip_successful) {
        		x_mean = 0;
        		y_mean = 0;
        		for (int i = 0; i < contours_found; i++) {
        			if (y_grip_return[i] > Y_THRESHOLD) {
        				x_mean += x_grip_return[i];
        				y_mean += y_grip_return[i];
        				counter += 1;
        			}
        		}
        		x_mean /= counter;    
        		y_mean /= counter;
        	}   
        	grip_successful = counter > 0;
    	}
    	catch (Exception e) {
    		grip_successful = false;
    	}
    }
    
    protected void align() {
    	System.out.println(current_stage.toString());
    	if (x_mean < X_TARGET_MEAN - X_DEADBAND / 2) {
    		l_output = -MAX_SPEED;//(X_TARGET_MEAN - x_mean) * X_MULTIPLIER;
    		r_output = -l_output;
    	}
    	else if (x_mean > X_TARGET_MEAN + X_DEADBAND / 2) {
    		l_output = MAX_SPEED;//(X_TARGET_MEAN - x_mean) * X_MULTIPLIER;
    		r_output = -l_output;//(X_TARGET_MEAN - x_mean) * X_MULTIPLIER;
    	}
    	else {
    		System.out.println("else");
    		l_output = 0;
    		r_output = 0;
    		current_stage = Stage.FINISHED;
    		Robot.gyro.resetAngle();
    		System.out.println(current_stage.toString());
    	}
    }
    
    protected void distance() {
    	System.out.println("distance");
    	if (area_mean < AREA_TARGET_MEAN - AREA_DEADBAND / 2) {
			l_output = (AREA_TARGET_MEAN - area_mean) * AREA_MULTIPLIER;
			r_output = l_output;
		}
		else if (area_mean > AREA_TARGET_MEAN - AREA_DEADBAND / 2) {
			l_output = -(AREA_TARGET_MEAN - area_mean) * AREA_MULTIPLIER;
			r_output = l_output;
		}
		else {
			l_output = 0;
			r_output = 0;
			current_stage = Stage.FINISHED;
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
				default:
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
    		Robot.drivetrain.setSpeedWithGyro(l_output, r_output);   
    	}
    	else {
    		Robot.drivetrain.setSpeed(l_output, r_output);   
    	}
    		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(current_stage.toString());
    	getGripData();
    	System.out.println(current_stage.toString());
    	createOutput();
    	System.out.println(current_stage.toString());
    	sendToDrivetrain();
    	System.out.println(current_stage.toString());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return current_stage == Stage.FINISHED || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
