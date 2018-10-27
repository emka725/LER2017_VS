package frc.robot.subsystems;

import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Roller extends Subsystem{
	private final double SPEED = 0.8;

	@Override
	protected void initDefaultCommand() {
	}
	public void setSpeed(double d){
		RobotMap.intake_roller_motor.set(ControlMode.Velocity, -d);
	}
	public void intake() {
		setSpeed(SPEED);
	}
	public void stop() {
		setSpeed(0);
	}
	public void pullGears() {
		setSpeed(-SPEED);
	}
}
