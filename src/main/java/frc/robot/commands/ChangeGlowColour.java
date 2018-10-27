package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Glow;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ChangeGlowColour extends InstantCommand {
	
	private Glow.Colour colour;
	
    public ChangeGlowColour(Glow.Colour new_colour) {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        colour = new_colour;
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.glow.setColour(colour);
    }

}
