package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Glow;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ChangeGlowMode extends InstantCommand {
	
	private Glow.Mode mode;
	
    public ChangeGlowMode(Glow.Mode new_mode) {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        mode = new_mode;
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.glow.setMode(mode);
    }

}
