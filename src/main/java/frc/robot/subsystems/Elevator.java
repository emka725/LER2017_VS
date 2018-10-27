package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.ElevatorCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem{

	private static final double SPEED = 1;
	private static final double RELEASE_SPEED = 0.2;
	private static final double SLOW_SPEED = 0.1;

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorCommand());
	}
	public void setSpeed(double speed) {
		RobotMap.elevator_forward_motor.set(ControlMode.Velocity, speed);
		RobotMap.elevator_backwards_motor.set(ControlMode.Velocity, -speed);
	}
	
	public void elevate() {
		setSpeed(SPEED);
	}
	public void stop() {
		setSpeed(0);
	}
	
	public void slowlyElevate() {
		setSpeed(SLOW_SPEED);
	}
	
	public void release() {
		setSpeed(-RELEASE_SPEED);
	}
}
