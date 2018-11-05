package frc.robot.subsystems;

import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearIntake extends Subsystem {
	
	private final double SPEED = 1;

    public void initDefaultCommand() {
    }
    
    public void intake(){
    	RobotMap.gear_intake_motor.set(ControlMode.PercentOutput, -SPEED);
    }
    
    public void stop() {
    	RobotMap.gear_intake_motor.set(ControlMode.PercentOutput, 0);
    }
    
    public void outtake() {
    	RobotMap.gear_intake_motor.set(ControlMode.PercentOutput, SPEED);
    }
}

