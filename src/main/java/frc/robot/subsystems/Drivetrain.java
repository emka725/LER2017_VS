package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TeleopDriveCommand;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	//XXX Why are these class-wide variables?
	private double left_speed;
	private double right_speed;
	
	private double straight_gyro_output_array[];
	
	public void initDefaultCommand() {
		setDefaultCommand(new TeleopDriveCommand());
		
	}
	
	public void setSpeed(double left_speed, double right_speed) {
		this.left_speed = left_speed;
		this.right_speed = right_speed;
		
		//|TODO: change control mode back to Velocity???? Need feedback
		RobotMap.left_motor_1.set(ControlMode.PercentOutput, this.left_speed);
		RobotMap.right_motor_1.set(ControlMode.PercentOutput, -this.right_speed);
	}
	
	public void setSpeedWithGyro(double left_speed, double right_speed) {
		straight_gyro_output_array = Robot.gyro.getStraightGyroOutput(left_speed, right_speed);
		this.setSpeed(straight_gyro_output_array[0], straight_gyro_output_array[1]);
	}
	
	public void stop() {
		setSpeed(0D, 0D);
	}
	
	public double getLeftSpeed() {
		return left_speed;
	}
	
	public double getRightSpeed() {
		return right_speed;
	}
	
}
