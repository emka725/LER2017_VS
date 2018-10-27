package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.XboxController;
import frc.robot.commands.ShooterCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem{

	public final double L_TARGET_SPEED =775;
	public final double R_TARGET_SPEED = 700;
	public final double INTAKE_SPEED = -500;
	
	//public final double SPEED = 0.75;	
	//public final double L_MULTIPLIER = 0.02;
	//public final double R_MULTIPLIER = 0.02;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ShooterCommand());
	}
	public void setSpeed(double speed){
		setLeftSpeed(speed);
		setRightSpeed(speed);
	}
	
	public void setLeftSpeed(double speed) {
		//RobotMap.shooter_left_motor.set(ControlMode.Velocity, -speed);
	}
	
	public void setRightSpeed(double speed) {
		//RobotMap.shooter_right_motor.set(ControlMode.Velocity, speed);
	}
	
	public void shoot() {
		setLeftSpeed(L_TARGET_SPEED);
		setRightSpeed(R_TARGET_SPEED);
		
	}

	public void intake() {
		Robot.shooter.setSpeed(INTAKE_SPEED);
	}

	public void stop() {
		Robot.shooter.setSpeed(0);
		//RobotMap.shooter_left_motor.clearIAccum();
		//RobotMap.shooter_right_motor.clearIAccum();
		//Don't change agitator speed - agitator default command will take over and close both
	}
	
	private boolean isLeftShooterUpToSpeed() {
		return true;//RobotMap.shooter_left_motor.getSpeed()>RobotMap.shooter_left_motor.getSetpoint()*0.99;
	}
	private boolean isRightShooterUpToSpeed() {
		return true;//RobotMap.shooter_right_motor.getSpeed()>RobotMap.shooter_right_motor.getSetpoint()*0.99;
	}
	
	public double getLeftVelocity() {
		return 0.0;//RobotMap.shooter_left_motor.getSpeed(); //getEncVelocity();
	}
	public double getRightVelocity() {
		return 0.0;//-RobotMap.shooter_right_motor.getSpeed(); //getEncVelocity();
	}

}
