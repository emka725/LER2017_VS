package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Ramp;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ResetRobot extends InstantCommand {

    public ResetRobot() {
        super();
        requires(Robot.gyro);
        requires(Robot.drivetrain);
        requires(Robot.pivot);
    }

    protected void initialize() {
    	Robot.drivetrain.stop();
    	Robot.gyro.resetAngle();
    	Robot.pivot.setPosition(Pivot.DISABLED);
    	Robot.ramp.setBothPosition(Ramp.RampPosition.DISABLED);
    }

}
