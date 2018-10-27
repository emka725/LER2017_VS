package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SwitchPivotPositionCommand extends InstantCommand {

	double angle;
	
    public SwitchPivotPositionCommand(double angle){
    	this.angle = angle;
    }

    protected void initialize() {
    	Robot.pivot.setPosition(angle);
    }

}
