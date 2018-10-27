/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.ResetRobot;
import frc.robot.subsystems.Agitator;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.GearIntake;
import frc.robot.subsystems.Glow;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Ramp;
import frc.robot.subsystems.Roller;
import frc.robot.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Gyro gyro = new Gyro();
	public static final Pivot pivot = new Pivot();
	public static final GearIntake gearIntake = new GearIntake();
	public static final Roller roller = new Roller();
	public static final Elevator elevator = new Elevator();
	public static final Shooter shooter = new Shooter();
	public static final Ramp ramp = new Ramp();
	public static final Agitator agitator = new Agitator();
	public static final Glow glow = new Glow();
	public static OI oi;
	
	public static boolean autonomous_running = false;

  ResetRobot reset_robot = new ResetRobot();
	public Command autonomousCommand = new AutonomousCommand();


  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    oi = new OI();
    RobotMap.init();
		reset_robot.start();
    initSmartdashboard();
    m_chooser.addDefault("Default Auto", new AutonomousCommand());
    // chooser.addObject("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }
	
	public void initSmartdashboard() {
  	//TODO: Mr Wood: change this to new network table implementation as in Shooterless branch
    NetworkTable.globalDeleteAll();
		SmartDashboard.putBoolean("Use Xbox Joystick for Pivot Control", false);
		SmartDashboard.putBoolean("Use Xbox Joysticks for Ramp Control", false);
		SmartDashboard.putBoolean("Baseline Autonomous", false);
		SmartDashboard.putBoolean("Gear Autonomous", false);
		SmartDashboard.putBoolean("Left Gear Autonomous", false);
		SmartDashboard.putBoolean("Right Gear Autonomous", false);
		SmartDashboard.putBoolean("sendVideo", false);
	}
	
  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
		updateSmartDashboard();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    reset_robot.start();
		Logging.close();
		autonomous_running = false;
}

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    //autonomousCommand = m_chooser.getSelected();
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command:
		autonomousCommand = new AutonomousCommand();
		autonomous_running = true;
    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
		Logging.open("Autonomous_Log.csv");
  }

	private void updateSmartDashboard() {
		SmartDashboard.putNumber("Pivot Voltage", RobotMap.pivot_potentiometer.getVoltage());
		SmartDashboard.putNumber("Left Encoder", shooter.getLeftVelocity());
		SmartDashboard.putNumber("Right Encoder", shooter.getRightVelocity());
		SmartDashboard.putNumber("Left Drivetrain", drivetrain.getLeftSpeed());
		SmartDashboard.putNumber("Right Drivetrain", drivetrain.getRightSpeed());
		SmartDashboard.putBoolean("Gear Switch", RobotMap.gear_switch.get());
		SmartDashboard.putBoolean("Gear Pocket Switch", RobotMap.gear_pocket_switch.get());
		SmartDashboard.putBoolean("Left Ramp Switch 1", RobotMap.left_ramp_switch_1.get());
		SmartDashboard.putBoolean("Left Ramp Switch 2", RobotMap.left_ramp_switch_2.get());
		SmartDashboard.putBoolean("Right Ramp Switch 1", RobotMap.right_ramp_switch_1.get());
		SmartDashboard.putBoolean("Right Ramp Switch 2", RobotMap.right_ramp_switch_2.get());
		
  }
  
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
		autonomousLogging();
  }

	public void autonomousLogging() {
	}
	
	public void teleopLogging() {
	}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
		autonomous_running = false;
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		Logging.close();
		Logging.open("Teleop_Logging.csv");
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    teleopLogging();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
		//LiveWindow.run(); // No longer required
  }
}
