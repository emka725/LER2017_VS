package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.XboxController;
import frc.robot.subsystems.Pivot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PivotCommand extends Command{

	
	public PivotCommand() {
		requires(Robot.pivot);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	protected void initialize() {
	}

	protected void execute() {
    	double pivot_potentiometer_position = Robot.pivot.getPosition();
    	double target = Robot.pivot.getTargetPosition();
    	double diff = target - pivot_potentiometer_position;
    	double output;

    	if (target == 0D){
    		output = 0;
    	}
    	else {
    		output = - diff *Pivot.kP;
    	}
    	if (SmartDashboard.getBoolean("Use Xbox Joystick for Pivot Control",true)) {
    		Robot.pivot.setSpeed(Robot.oi.xbox.getRawAxis(XboxController.XBOX_LEFT_Y));
    	}
    	else {
    		Robot.pivot.setSpeed(output);
    	}
    	
	}

}
