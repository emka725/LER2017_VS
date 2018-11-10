package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gyro extends Subsystem {

	private final double GYRO_MULTIPLIER = 0.015;
	private final double ANGLE_TOLERANCE = 2;
	private final double FORWARD_THRESHOLD = 80;
	private final double MAX_ARCADE_SPEED = 0.6;
	
    public void initDefaultCommand() {
    }
    
    public void resetAngle() {
    	RobotMap.gyro.reset();
    }
    
    public void calibrate() {
    	RobotMap.gyro.calibrate();
    }
    
    public double getAngle() {
    	return RobotMap.gyro.getAngle();
    }
    
    public double[] getStraightGyroOutput(double left, double right) {
		double l_gyro_out;
		double r_gyro_out;
		double current_angle;
		double total_speed;

		l_gyro_out = left;
    	r_gyro_out = right;
    	total_speed = Math.abs(left) + Math.abs(right) + 1;
    	
    	current_angle = getAngle();
    	
    	if (current_angle > ANGLE_TOLERANCE / 2) {
    		l_gyro_out -= current_angle * GYRO_MULTIPLIER * 2 / total_speed;
    		r_gyro_out += current_angle * GYRO_MULTIPLIER * 2 / total_speed;
    	}
    	else if (current_angle < -ANGLE_TOLERANCE / 2) {
    		l_gyro_out += Math.abs(current_angle) * GYRO_MULTIPLIER * 2 / total_speed;
    		r_gyro_out -= Math.abs(current_angle) * GYRO_MULTIPLIER * 2 / total_speed;
    	}
    	
    	double[] output_array = {l_gyro_out, r_gyro_out};
    	
    	return output_array;
    }
    
    public double[] getArcadeGyroOutput(double x, double y) {
		double current_angle;
		double joystick_angle;
		double joystick_speed;
		double l_gyro_out;
		double r_gyro_out;
		boolean cw_faster;
		double closest_difference;
	
    	if (x == 0D && y == 0D) {
    		double[] output_array = {0D, 0D};
        	return output_array;
    	}
    	
    	joystick_speed = Math.abs(Robot.oi.r_joystick.getX()) > Math.abs(Robot.oi.r_joystick.getY())? 
    			Math.abs(Robot.oi.r_joystick.getX()):
    			Math.abs(Robot.oi.r_joystick.getY());
    			
    	joystick_speed = joystick_speed > MAX_ARCADE_SPEED? 
    			0.6: 
    			joystick_speed;
    	joystick_angle = Math.toDegrees(Math.atan2(y, x)) - 90D;
    	current_angle = getAngle();
    	
    	if (joystick_angle > 0) {
    		joystick_angle = 360 - joystick_angle;
    	}
    	if (joystick_angle < 0) {
    		joystick_angle = joystick_angle * -1;
    	}
    	joystick_angle = 360 - joystick_angle;
    	if (joystick_angle == 360) {
    		joystick_angle = 0;
    	}
    	
    	current_angle %= 360;
    	
    	if (current_angle < 0) {
    		current_angle += 360;
    	}
    	
    	current_angle = 360 - current_angle;
    	

    	if (Math.abs(joystick_angle - current_angle) < 180) {
    		closest_difference = Math.abs(joystick_angle - current_angle);
    	}
    	else {
    		closest_difference = 360 - Math.abs(joystick_angle - current_angle);
    	}
    	
    	if (current_angle > joystick_angle) {
    		cw_faster = current_angle - joystick_angle < 180;
    	}
    	else if (current_angle < joystick_angle) {
    		cw_faster = ! (joystick_angle - current_angle < 180);
    	}
    	else {
    		cw_faster = true;
    	}
    	
    	
    	if (closest_difference > FORWARD_THRESHOLD) {
    		if (cw_faster) {
    			l_gyro_out = joystick_speed;
    			r_gyro_out = -joystick_speed;
    		}
    		else {
    			l_gyro_out = -joystick_speed;
    			r_gyro_out = joystick_speed;
    		}
    	}
    	else {
    		if (cw_faster) {
    			l_gyro_out = joystick_speed;
    			r_gyro_out = -joystick_speed + joystick_speed * 2 * Math.pow(1 - closest_difference / FORWARD_THRESHOLD, 2);
    		}
    		else {
    			l_gyro_out = -joystick_speed + joystick_speed * 2 * Math.pow(1 - closest_difference / FORWARD_THRESHOLD, 2);
    			r_gyro_out = joystick_speed;
    		}
    	}
    	
    	double[] output_array = {l_gyro_out, r_gyro_out};
    	return output_array;
    }
}

