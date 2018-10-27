package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Agitator extends Subsystem {
	public final double SPEED = 0.3;
	
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void spin() {
		//RobotMap.agitator_motor_1.set(SPEED);
		//RobotMap.agitator_motor_2.set(-SPEED);
	}
	
	public void spinBackwards() {
		//RobotMap.agitator_motor_1.set(-SPEED);
		//RobotMap.agitator_motor_2.set(SPEED);
	}
	
	public void stop() {
		//RobotMap.agitator_motor_1.set(0);
		//RobotMap.agitator_motor_2.set(0);
	}
}
