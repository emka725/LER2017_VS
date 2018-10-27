package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Glow;
import static frc.robot.subsystems.Glow.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GlowCommand extends Command {

    public GlowCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.glow);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.glow.setMode(Glow.Mode.GEAR_ALERT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.glow.setColour(Glow.Colour.WHITE);
    	switch (Robot.glow.getMode()) {
    	case NORMAL:
			Robot.glow.setColour(Robot.glow.getColour());
    		break;
    	case DISCO:
    		switch (((int) (timeSinceInitialized() * 24)) % 6) { 
    		case 0:
	    		Robot.glow.setColour(Glow.Colour.RED);
	    		break;
    		case 1:
    			Robot.glow.setColour(Glow.Colour.YELLOW);
    			break;
    		case 2:
	    		Robot.glow.setColour(Glow.Colour.GREEN);
	    		break;
    		case 3:
	    		Robot.glow.setColour(Glow.Colour.TURQUOISE);
	    		break;
    		case 4:
	    		Robot.glow.setColour(Glow.Colour.BLUE);
	    		break;
    		case 5:
	    		Robot.glow.setColour(Glow.Colour.PURPLE);
	    		break;
    		}
    		break;
    	case BLINK:
    		if ((int)(timeSinceInitialized())%2==0)
    			Robot.glow.setColour(Glow.Colour.OFF);
    		else
    			Robot.glow.setColour(Robot.glow.getColour());
    		break;
    	case CHASE:
    		switch ((int)(timeSinceInitialized())%3){
    		case 0:
	    		Robot.glow.setColour(Glow.Colour.RED);
	    		break;
    		case 1:
    			Robot.glow.setColour(Glow.Colour.BLUE);
    			break;
    		case 2:
	    		Robot.glow.setColour(Glow.Colour.GREEN);
	    		break;    		
    		}
    		break;
    	case SAFETY:
			if (((int)(timeSinceInitialized()*2.5)%2==0))
				Robot.glow.setColour(Glow.Colour.OFF);
			else
				Robot.glow.setColour(Glow.Colour.YELLOW);
			break;
    	case GEAR_ALERT:
    		if (RobotMap.gear_switch.get()) {
    			Robot.glow.setColour(Glow.Colour.RED);
    		}
    		else {
    			Robot.glow.setColour(Glow.Colour.GREEN);
    		}
	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.glow.setColour(Colour.OFF);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
