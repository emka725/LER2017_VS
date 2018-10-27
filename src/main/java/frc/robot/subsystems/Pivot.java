package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.PivotCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Pivot extends Subsystem{	
	private double targetPosition = 0;

	public static double DISABLED = 0;
	public static double ANGLE_DOWN = 5;//0.64;//4.38;//0.866;//2376;
	public static double ANGLE_UP = 3;//2.26;//1.9;//ANGLE_DOWN + 1.45;//394;
	public static double ANGLE_BACK = 1.1;//3.53;//0.5;//ANGLE_DOWN + 3.014;//4;
	public static final double PIVOT_DEADBAND = 0.00;
	public static final double kP = 2;
	private static final double MAX_SPEED = 0.4;
	static final double constant = 5D / 512D;

    public void initDefaultCommand() {
    	setDefaultCommand(new PivotCommand());
    }
    
    public void setPosition(double d){
    	targetPosition = d;
    	
    }
    
    public
    double getTargetPosition() {
    	return targetPosition;
    }
    
    public double getPosition() {
    	return RobotMap.pivot_potentiometer.getVoltage();
    }
    
    public boolean isIntaking(){
        return (Math.abs(targetPosition - RobotMap.pivot_potentiometer.getVoltage()) < PIVOT_DEADBAND) && 
        	(targetPosition == ANGLE_BACK);

    }
    
    public void setSpeed(double d){
    	if (d > MAX_SPEED) {
    		d = MAX_SPEED;
    	}
    	else if (d < - MAX_SPEED) {
    		d = - MAX_SPEED;
    	}
    	if (!RobotMap.gear_pocket_switch.get() && d < 0) {
    		RobotMap.pivot_motor.set(ControlMode.Velocity, 0);
    	}
    	else {
    		RobotMap.pivot_motor.set(ControlMode.Velocity, d);
    	}
    }
	
}
