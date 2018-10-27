package frc.robot.commands;

import frc.robot.subsystems.Pivot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

//	public enum AutonomousMode {DO_NOTHING, BASELINE, CENTER_GEAR}
	
    public AutonomousCommand() {
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	if (SmartDashboard.getBoolean("Baseline Autonomous", false)) {
    		addParallel(new ConstantlyIntakeGear());
    		addSequential(new SwitchPivotPositionCommand(Pivot.ANGLE_DOWN));
    		addSequential(new AutoDrive(1.5, 1));
    		addSequential(new AutoTurn(180));
    		addSequential(new SwitchPivotPositionCommand(Pivot.ANGLE_UP));
    		addSequential(new StopGearIntake());
    	}
    	else if (SmartDashboard.getBoolean("Gear Autonomous", false)) {
    		addParallel(new ConstantlyIntakeGear());
    		addSequential(new SwitchPivotPositionCommand(Pivot.ANGLE_UP));
    		addSequential(new StopGearIntake());
 //   		addSequential(new AutoDrive(2.6, 0.3));
    		addSequential(new AutoDrive(2, 0.5));  
    		addSequential(new SwitchPivotPositionCommand(Pivot.ANGLE_DOWN));       //eject gear
    		
    		addSequential(new AutoDrive(1.4, -0.4));                               //back up
    		addSequential(new AutoTurn(-70));
    		
    		
    	}
    	else if (SmartDashboard.getBoolean("Left Gear Autonomous",false)) {
    		addParallel(new ConstantlyIntakeGear());
    		addSequential(new SwitchPivotPositionCommand(Pivot.ANGLE_DOWN));
    		addSequential(new AutoDrive(1.7, 0.6));                               //move forwards
//    		addSequential(new AutoDrive(0.5, 0.1, 0.3));	
    		addSequential(new AutoTurn(-75));                                       //turn to gear peg
//    		addSequential(new AutoDrive(0.7, 0.4));
//    		addSequential(new AutoTurn(30));
    		addSequential(new StopGearIntake());
    		addSequential(new TimerCommand(0.3)); //wait for vision to catch up
    		addSequential(new AlignGearVision());                                  //vision
    		addSequential(new SwitchPivotPositionCommand(Pivot.ANGLE_UP));         //move gear up
    		addSequential(new TimerCommand(0.8));                                  //wait for pivot to move
    		addSequential(new AutoDrive(2.2, 0.3));                                //insert gear
    		addSequential(new SwitchPivotPositionCommand(Pivot.ANGLE_DOWN));       //eject gear
    		addSequential(new AutoDrive(1.5, -0.4));                               //back up
    		addSequential(new AutoTurn(60));
 //   		addSequential(new AutoDrive(3, 1));
    	}
    	else if (SmartDashboard.getBoolean("Right Gear Autonomous", false)) {
    		addParallel(new ConstantlyIntakeGear());
    		addSequential(new SwitchPivotPositionCommand(Pivot.ANGLE_DOWN));
    		addSequential(new AutoDrive(1.7, 0.6));                               //move forwards
//    		addSequential(new AutoDrive(0.5, 0.1, 0.3));	
    		addSequential(new AutoTurn(75));                                       //turn to gear peg
//    		addSequential(new AutoDrive(0.7, 0.4));
//    		addSequential(new AutoTurn(30));
    		addSequential(new StopGearIntake());
    		addSequential(new TimerCommand(0.3)); //wait for vision to catch up
    		addSequential(new AlignGearVision());                                  //vision
    		addSequential(new SwitchPivotPositionCommand(Pivot.ANGLE_UP));         //move gear up
    		addSequential(new TimerCommand(0.8));                                  //wait for pivot to move
    		addSequential(new AutoDrive(2.2, 0.3));                                //insert gear
    		addSequential(new SwitchPivotPositionCommand(Pivot.ANGLE_DOWN));       //eject gear
    		addSequential(new AutoDrive(1.5, -0.4));                               //back up
    		addSequential(new AutoTurn(-60));
 //   		addSequential(new AutoDrive(3, 1));
    		
    	}
    }
}
