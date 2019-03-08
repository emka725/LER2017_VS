package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopDriveCommand extends Command {
	//XXX Why are these class-wide variables?
	private double l_joystick_input;
	private double r_joystick_x_input;
	private double r_joystick_y_input;
	
	private boolean straight_gyro_on;
	private boolean straight_gyro_previously_on = false;
	
	private double[] straight_gyro_array;
	
	private boolean arcade_gyro_on;
	private boolean arcade_gyro_previously_on;
	
	private boolean turn_override_on;

	private double l_output;
	private double r_output;
	
	private static double JOYSTICK_DEADBAND = 0.03;
	
	public TeleopDriveCommand() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
	}

	protected void getInput() {
		l_joystick_input = -Robot.oi.l_joystick.getY();
		r_joystick_y_input = -Robot.oi.r_joystick.getY();
		r_joystick_x_input = Robot.oi.r_joystick.getX();
		
		if (Math.abs(l_joystick_input) > JOYSTICK_DEADBAND)
			l_output = l_joystick_input;
		else {
			l_output = 0D;
		}
		if (Math.abs(r_joystick_y_input) > JOYSTICK_DEADBAND)
			r_output = r_joystick_y_input;
		else {
			r_output = 0D;
		}
		
		straight_gyro_on = Robot.oi.r_joystick.getTrigger();
		arcade_gyro_on = Robot.oi.arcade_gyro_button.get();
		turn_override_on = Robot.oi.l_joystick.getTrigger();
	}
	
	protected void createOutput() {
		
		if (straight_gyro_on) {
			if (!straight_gyro_previously_on) {
				Robot.gyro.resetAngle();
			}
			l_output = (l_output + r_output) / 2;
			r_output = l_output;
			straight_gyro_array = Robot.gyro.getStraightGyroOutput(l_output, r_output);
			l_output = straight_gyro_array[0];
			r_output = straight_gyro_array[1];
			
		}
		else if (turn_override_on) {
			l_output = -(Robot.oi.l_joystick.getY()-Robot.oi.r_joystick.getY());
			r_output = -l_output;

		}
		else if (arcade_gyro_on) {
			if (!arcade_gyro_previously_on) {
				Robot.gyro.resetAngle();
			}
			
			double[] gyro_output = Robot.gyro.getArcadeGyroOutput(r_joystick_x_input, r_joystick_y_input);
			l_output = gyro_output[0];
			r_output = gyro_output[1];
		}
		
	}
	
	protected void sendToDrivetrain() {
		if (!Robot.autonomous_running) {
			double left = l_output * Robot.speedLimitFactor;
			double right = r_output * Robot.speedLimitFactor;
			Robot.drivetrain.setSpeed(left, right);
		}
	}
	
	protected void lastTasks() {
		straight_gyro_previously_on = straight_gyro_on;
		arcade_gyro_previously_on = arcade_gyro_on;
	}
	
	@Override
	protected void execute() {
		getInput();
		createOutput();
		sendToDrivetrain();		
		lastTasks();
	}

	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drivetrain.stop();;
	}

	@Override
	protected void interrupted() {
		end();
	}
}
