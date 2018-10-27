package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.XboxController;
import frc.robot.subsystems.Ramp;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RampCommand extends Command {
	private static final double MOVEMENT_SPEED = 0.3;
	
    public RampCommand() {
    	requires(Robot.ramp);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (Robot.oi.xbox.getPOV() == XboxController.XBOX_DPAD_UP_ANGLE) {
    		Robot.ramp.setBothPosition(Ramp.RampPosition.SHOOT);
    	}
    	else if (Robot.oi.xbox.getPOV() == XboxController.XBOX_DPAD_DOWN_ANGLE) {
    		Robot.ramp.setBothPosition(Ramp.RampPosition.INTAKE);
    	}
    	else if (Robot.oi.xbox.getPOV() == XboxController.XBOX_DPAD_RIGHT_ANGLE) {
    		Robot.ramp.setBothPosition((Ramp.RampPosition.DISABLED));
    	}
    	
    	if (SmartDashboard.getBoolean("Use Xbox Joysticks for Ramp Control",false) && !SmartDashboard.getBoolean("Use Xbox Joystick for Pivot Control",true)) {
    		Robot.ramp.setLeftSpeed(Robot.oi.xbox.getRawAxis(XboxController.XBOX_LEFT_Y));
    		Robot.ramp.setRightSpeed(Robot.oi.xbox.getRawAxis(XboxController.XBOX_RIGHT_Y));
    	}
    	else {
	    	if (Robot.ramp.getLeftPosition() == Ramp.RampPosition.DISABLED) {
	    		Robot.ramp.setLeftSpeed(0);
	    	}
	    	else if (Robot.ramp.getLeftPosition() == Ramp.RampPosition.INTAKE) {
	    		Robot.ramp.setLeftSpeed(MOVEMENT_SPEED);
//	    		if (Robot.ramp.getLeftSwitch1()) {
//	    			Robot.ramp.setLeftSpeed(MOVEMENT_SPEED);
//	    		}
//	    		else {
//	    			Robot.ramp.setLeftSpeed(0);
//	    		}
	    	}
	    	else if (Robot.ramp.getLeftPosition() == Ramp.RampPosition.SHOOT) {
	    		Robot.ramp.setLeftSpeed(-MOVEMENT_SPEED);
//	    		if (Robot.ramp.getLeftSwitch2()) {
//	    			Robot.ramp.setLeftSpeed(-MOVEMENT_SPEED);
//	    		}
//	    		else {
//	    			Robot.ramp.setLeftSpeed(0);
//	    		}
	    	}
	    	
	    	if (Robot.ramp.getRightPosition() == Ramp.RampPosition.DISABLED) {
	    		Robot.ramp.setRightSpeed(0);
	    	}
	    	else if (Robot.ramp.getRightPosition() == Ramp.RampPosition.INTAKE) {
//	    		if (Robot.ramp.getRightSwitch1()) {
//	    			Robot.ramp.setRightSpeed(MOVEMENT_SPEED);
//	    		}
//	    		else {
//	    			Robot.ramp.setRightSpeed(0);
//	    		}
	    		Robot.ramp.setRightSpeed(MOVEMENT_SPEED);
	    	}
	    	else if (Robot.ramp.getRightPosition() == Ramp.RampPosition.SHOOT) {
//	    		if (Robot.ramp.getRightSwitch2()) {
//	    			Robot.ramp.setRightSpeed(-MOVEMENT_SPEED);
//	    		}
//	    		else {
//	    			Robot.ramp.setRightSpeed(0);
//	    		}
	    		Robot.ramp.setRightSpeed(-MOVEMENT_SPEED);
	    	}
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.ramp.setBothSpeed(0);
    }

    protected void interrupted() {
    	end();
    }
}
