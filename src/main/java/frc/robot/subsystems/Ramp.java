package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.RampCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Ramp extends Subsystem{

	public static final double MAX_SPEED = 0.3;

	public static enum RampPosition {SHOOT, INTAKE, DISABLED}
	
	private RampPosition left_ramp_position = RampPosition.DISABLED;
	private RampPosition right_ramp_position = RampPosition.DISABLED;
	
	@Override
	protected void initDefaultCommand() {
        setDefaultCommand(new RampCommand());
	}
	
    public void setBothPosition(RampPosition new_position){
    	 setLeftPosition(new_position);
    	 setRightPosition(new_position);
    }
    
    public void setLeftPosition(RampPosition new_position) {
    	left_ramp_position = new_position;
    }
    
    public void setRightPosition(RampPosition new_position) {
    	right_ramp_position = new_position;
    }
    
    public void setLeftSpeed(double d){
    	if (d > MAX_SPEED) {
    		d = MAX_SPEED;
    	}
    	else if (d < -MAX_SPEED) {
    		d = -MAX_SPEED;
    	}
    	
//    	if ((!(!getLeftSwitch1() && d > 0)) && (!(!getLeftSwitch2() && d < 0))) {
//    		RobotMap.ramp_motor_left.set(ControlMode.Velocity, d);
//    	}
//    	else {
//    		RobotMap.ramp_motor_left.set(ControlMode.Velocity, 0);
//    	}
    	//RobotMap.ramp_motor_left.set(ControlMode.Velocity, d);
    }
    public void setRightSpeed(double d){
    	if (d > MAX_SPEED) {
    		d = MAX_SPEED;
    	}
    	else if (d < -MAX_SPEED) {
    		d = -MAX_SPEED;
    	}
//    	if ((!(!getRightSwitch1() && d > 0)) && (!(!getRightSwitch2() && d < 0))) {
//    		RobotMap.ramp_motor_right.set(ControlMode.Velocity, d);
//    	}
//    	else {
//    		RobotMap.ramp_motor_right.set(ControlMode.Velocity, 0);
//    	}
    	//RobotMap.ramp_motor_right.set(ControlMode.Velocity, d);
    }
    
    public boolean getLeftSwitch1() {
    	return RobotMap.left_ramp_switch_1.get();
    }
    
    public boolean getLeftSwitch2() {
    	return RobotMap.left_ramp_switch_2.get();
    }
    
    public boolean getRightSwitch1() {
    	return RobotMap.right_ramp_switch_1.get();
    }
    
    public boolean getRightSwitch2() {
    	return RobotMap.right_ramp_switch_2.get();
    }
    
    public RampPosition getLeftPosition() {
    	return left_ramp_position;
    }
    
    public RampPosition getRightPosition() {
    	return right_ramp_position;
    }
    
    public void setBothSpeed(double speed) {
    	setLeftSpeed(speed);
    	setRightSpeed(speed);
    }
}
