/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
//import com.ctre.TalonSRX;
//import com.ctre.TalonSRX.FeedbackDevice;
//import com.ctre.TalonSRX.TalonControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Flipped left_talon_1 and gear_intake_talon inputs due to physical limits (1 and 5)
	private static final int LEFT_TALON_1_INT = 5;
	private static final int LEFT_TALON_2_INT = 2;
	private static final int RIGHT_TALON_1_INT = 3;
	private static final int RIGHT_TALON_2_INT = 4;
	private static final int GEAR_INTAKE_TALON_INT = 1;
	
	private static final int PIVOT_MOTOR_INT = 6;
	private static final int INTAKE_ROLLER_INT = 7;
	private static final int ELEVATOR_FORWARD_INT = 10;
	private static final int ELEVATOR_BACKWARDS_INT = 9;
	//private static final int SHOOTER_LEFT_INT = 8;
	//private static final int SHOOTER_RIGHT_INT = 11;
	//private static final int RAMP_MOTOR_LEFT_INT = 12;
	//private static final int RAMP_MOTOR_RIGHT_INT = 13;
	//private static final int AGITATOR_MOTOR_1_INT = 14;
	//private static final int AGITATOR_MOTOR_2_INT = 15;
	
	private static final int PIVOT_POTENTIOMETER_INT = 3;
//	private static final int DIST_SENSOR_POTENTIOMETER_INT = 2;
	
	private static final int LEFT_RAMP_SWITCH_1_INT = 0;
	private static final int LEFT_RAMP_SWITCH_2_INT = 1;
	private static final int RIGHT_RAMP_SWITCH_1_INT = 2;
	private static final int RIGHT_RAMP_SWITCH_2_INT = 3;
	
	private static final int GEAR_SWITCH_INT = 5;
	private static final int GEAR_POCKET_SWITCH_INT = 4;

	private static final int LEFT_AGITATOR_SWITCH_INT = 6;
	private static final int RIGHT_AGITATOR_SWITCH_INT = 7;
	
	private static final int R_CHANNEL_RELAY_INT = 2;
	private static final int G_CHANNEL_RELAY_INT = 0;
	private static final int B_CHANNEL_RELAY_INT = 1;

	public static AnalogInput pivot_potentiometer;
	public static AnalogInput dist_sensor_potentiometer;
	
	public static Relay r_channel_relay;
	public static Relay g_channel_relay;
	public static Relay b_channel_relay;
	
	public static DigitalInput left_ramp_switch_1;
	public static DigitalInput left_ramp_switch_2;
	public static DigitalInput right_ramp_switch_1;
	public static DigitalInput right_ramp_switch_2;
	
	public static DigitalInput gear_switch;
	public static DigitalInput gear_pocket_switch;

	public static DigitalInput agitator_left_switch;
	public static DigitalInput agitator_right_switch;

	public static TalonSRX pivot_motor;
	public static TalonSRX left_motor_1;
	public static TalonSRX left_motor_2;
	public static TalonSRX right_motor_1;
	public static TalonSRX right_motor_2;
	public static TalonSRX gear_intake_motor;
	public static TalonSRX intake_roller_motor;
	public static TalonSRX elevator_forward_motor;
	public static TalonSRX elevator_backwards_motor;
	//public static TalonSRX shooter_left_motor;
	//public static TalonSRX shooter_right_motor;
	//public static TalonSRX ramp_motor_left;
	//public static TalonSRX ramp_motor_right;
	//public static TalonSRX agitator_motor_1;
	//public static TalonSRX agitator_motor_2;
	
	public static ADXRS450_Gyro gyro;

	public static void init() {
		
		r_channel_relay = new Relay(R_CHANNEL_RELAY_INT, Relay.Direction.kBoth);
		g_channel_relay = new Relay(G_CHANNEL_RELAY_INT, Relay.Direction.kBoth);
		b_channel_relay = new Relay(B_CHANNEL_RELAY_INT, Relay.Direction.kBoth);
				
		pivot_potentiometer = new AnalogInput(PIVOT_POTENTIOMETER_INT);
//		dist_sensor_potentiometer = new AnalogInput(DIST_SENSOR_POTENTIOMETER_INT);
		
		left_ramp_switch_1 = new DigitalInput(LEFT_RAMP_SWITCH_1_INT);
		left_ramp_switch_2 = new DigitalInput(LEFT_RAMP_SWITCH_2_INT);
		right_ramp_switch_1 = new DigitalInput(RIGHT_RAMP_SWITCH_1_INT);
		right_ramp_switch_2 = new DigitalInput(RIGHT_RAMP_SWITCH_2_INT);
		
		gear_switch = new DigitalInput(GEAR_SWITCH_INT);
		gear_pocket_switch = new DigitalInput(GEAR_POCKET_SWITCH_INT);

		agitator_left_switch = new DigitalInput(LEFT_AGITATOR_SWITCH_INT);
		agitator_right_switch = new DigitalInput(RIGHT_AGITATOR_SWITCH_INT);

    	pivot_motor = new TalonSRX(PIVOT_MOTOR_INT);
    	left_motor_1 = new TalonSRX(LEFT_TALON_1_INT);
    	left_motor_2 = new TalonSRX(LEFT_TALON_2_INT);
    	right_motor_1 = new TalonSRX(RIGHT_TALON_1_INT);
    	right_motor_2 = new TalonSRX(RIGHT_TALON_2_INT);
    	gear_intake_motor = new TalonSRX(GEAR_INTAKE_TALON_INT);
    	intake_roller_motor = new TalonSRX(INTAKE_ROLLER_INT);
    	elevator_forward_motor = new TalonSRX(ELEVATOR_FORWARD_INT);
    	elevator_backwards_motor = new TalonSRX(ELEVATOR_BACKWARDS_INT);
    	//shooter_left_motor = new TalonSRX(SHOOTER_LEFT_INT);
    	//shooter_right_motor = new TalonSRX(SHOOTER_RIGHT_INT);
    	//ramp_motor_left = new TalonSRX(RAMP_MOTOR_LEFT_INT);
    	//ramp_motor_right = new TalonSRX(RAMP_MOTOR_RIGHT_INT);
    	//agitator_motor_1 = new TalonSRX(AGITATOR_MOTOR_1_INT);
    	//agitator_motor_2 = new TalonSRX(AGITATOR_MOTOR_2_INT);
    	
    	gyro = new ADXRS450_Gyro();
    	
		left_motor_2.follow(left_motor_1);
		right_motor_2.follow(right_motor_1);
		
		//shooter_left_motor.reverseOutput(false);
		//shooter_left_motor.reverseSensor(true);
		//shooter_left_motor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		//shooter_left_motor.configEncoderCodesPerRev(80);		

		//shooter_left_motor.changeControlMode(TalonControlMode.Speed);
		//shooter_left_motor.clearIAccum();
		//shooter_left_motor.configNominalOutputVoltage(0.0f, -0.0f); //could try 1 or 2 volts
		//shooter_left_motor.configPeakOutputVoltage(12.0F, -12.0F);
		//shooter_left_motor.setEncPosition(0);
		//shooter_left_motor.set(0);
		//shooter_left_motor.setProfile(0);
		////shooter_left_motor.setPID(2.25, 0.035, 0.0, 3.46, 20, 0.0, 0); // P, I, D, F, IZONE, 0,0
		//shooter_left_motor.setPID(2.5, 0.012, 0.0, 2.25, 150, 0.0, 0);
		
		//// Start with just F, then add P, then D=10P if its too fast, and/or I=P/100 to correct steady state error
	
		////shooter_left_motor.changeControlMode(TalonSRX.TalonControlMode.PercentVbus);
		////shooter_left_motor.set(0);

		
		//shooter_right_motor.reverseOutput(false);
		//shooter_right_motor.reverseSensor(true);
		//shooter_right_motor.setFeedbackDevice(TalonSRX.FeedbackDevice.QuadEncoder);
		//shooter_right_motor.configEncoderCodesPerRev(80);
		
		//shooter_right_motor.changeControlMode(TalonSRX.TalonControlMode.Speed);
		//shooter_right_motor.clearIAccum();
		//shooter_right_motor.configNominalOutputVoltage(0.0f, -0.0f); //could try 1 or 2 volts
		//shooter_right_motor.configPeakOutputVoltage(12.0F, -12.0F);
		//shooter_right_motor.setEncPosition(0);
		//shooter_right_motor.set(0);
		//shooter_right_motor.setProfile(0);
		//shooter_right_motor.setPID(2.5, 0.012, 0.0, 2.25, 100, 0.0, 0);
		//// Start with just F, then add P, then D=10P if its too fast, and/or I=P/100 to correct steady state error
		
		////shooter_right_motor.changeControlMode(TalonSRX.TalonControlMode.PercentVbus);
		////shooter_right_motor.set(0);

		try {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(320, 340);
			camera.setFPS(15);
		} 
			catch(Exception e) {	
		}
	}
}
