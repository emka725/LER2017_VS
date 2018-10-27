package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.XboxController;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterCommand extends Command {
    public ShooterCommand() {
    	requires(Robot.shooter);
    	requires(Robot.agitator);	// Will cause this command to interrupt the agitator default command
    								// and allow this command to control the agitator. As soon as this
    								// command ends, the agitator default command will resume and close both ports.
    }

    protected void initialize() {}

    protected void execute() {
//    	if(Robot.oi.prime_button.get()){
//			Robot.shooter.setSpeed(0.5);
//    	
//		}
//    	else if (Robot.oi.shoot_button.get()) {
//			Robot.shooter.setSpeed(Robot.oi.xbox.getRawAxis(XboxController.XBOX_RIGHT_Y));
//    	}
//    	else {
//    		Robot.shooter.setSpeed(0);
//    	}
    	
    	if (Robot.oi.xbox.getRawAxis(XboxController.XBOX_RIGHT_TRIGGER) > 0.8) {
    		Robot.shooter.shoot();
    		Robot.agitator.spin();
    	}
    	
    	else if (Robot.oi.xbox.getRawAxis(XboxController.XBOX_LEFT_TRIGGER) > 0.8) {
    		Robot.shooter.intake();
    		Robot.agitator.spinBackwards();
    	}
    	
    	else {
    		Robot.shooter.stop();
    		Robot.agitator.stop();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.shooter.stop();
    }

    protected void interrupted() {
    	end();
    }
}
