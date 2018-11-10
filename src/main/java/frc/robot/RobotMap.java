/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
	private static final int LEFT_TALON_1_INT = 3;
	private static final int LEFT_TALON_2_INT = 4;
	private static final int LEFT_TALON_3_INT = 11;
	private static final int RIGHT_TALON_1_INT = 5;
	private static final int RIGHT_TALON_2_INT = 2;
	private static final int RIGHT_TALON_3_INT = 8;
	private static final int GEAR_INTAKE_TALON_INT = 1;
	
	private static final int PIVOT_MOTOR_INT = 6;
	//private static final int INTAKE_ROLLER_INT = 7;
	private static final int ELEVATOR_FORWARD_INT = 10;
	private static final int ELEVATOR_BACKWARDS_INT = 9;
	
	private static final int PIVOT_POTENTIOMETER_INT = 0;
	
	private static final int GEAR_SWITCH_INT = 1;
	private static final int GEAR_POCKET_SWITCH_INT = 0;
	
	private static final int R_CHANNEL_RELAY_INT = 2;
	private static final int G_CHANNEL_RELAY_INT = 0;
	private static final int B_CHANNEL_RELAY_INT = 1;

	public static AnalogInput pivot_potentiometer;
	public static AnalogInput dist_sensor_potentiometer;
	
	public static Relay r_channel_relay;
	public static Relay g_channel_relay;
	public static Relay b_channel_relay;
		
	public static DigitalInput gear_switch;
	public static DigitalInput gear_pocket_switch;

	public static TalonSRX pivot_motor;
	public static TalonSRX left_motor_1;
	public static TalonSRX left_motor_2;
	public static TalonSRX left_motor_3;
	public static TalonSRX right_motor_1;
	public static TalonSRX right_motor_2;
	public static TalonSRX right_motor_3;
	public static TalonSRX gear_intake_motor;
	public static TalonSRX elevator_forward_motor;
	public static TalonSRX elevator_backwards_motor;

	public static ADXRS450_Gyro gyro;

	public static void init() {
		
		r_channel_relay = new Relay(R_CHANNEL_RELAY_INT, Relay.Direction.kBoth);
		g_channel_relay = new Relay(G_CHANNEL_RELAY_INT, Relay.Direction.kBoth);
		b_channel_relay = new Relay(B_CHANNEL_RELAY_INT, Relay.Direction.kBoth);
				
		pivot_potentiometer = new AnalogInput(PIVOT_POTENTIOMETER_INT);
		
		gear_switch = new DigitalInput(GEAR_SWITCH_INT);
		gear_pocket_switch = new DigitalInput(GEAR_POCKET_SWITCH_INT);

    	pivot_motor = new TalonSRX(PIVOT_MOTOR_INT);
    	left_motor_1 = new TalonSRX(LEFT_TALON_1_INT);
    	left_motor_2 = new TalonSRX(LEFT_TALON_2_INT);
    	left_motor_3 = new TalonSRX(LEFT_TALON_3_INT);
    	right_motor_1 = new TalonSRX(RIGHT_TALON_1_INT);
    	right_motor_2 = new TalonSRX(RIGHT_TALON_2_INT);
    	right_motor_3 = new TalonSRX(RIGHT_TALON_3_INT);
    	gear_intake_motor = new TalonSRX(GEAR_INTAKE_TALON_INT);
    	elevator_forward_motor = new TalonSRX(ELEVATOR_FORWARD_INT);
    	elevator_backwards_motor = new TalonSRX(ELEVATOR_BACKWARDS_INT);
    	
    	gyro = new ADXRS450_Gyro();
    	
		left_motor_2.set(ControlMode.Follower,LEFT_TALON_1_INT);
		left_motor_3.set(ControlMode.Follower,LEFT_TALON_1_INT);
		right_motor_2.set(ControlMode.Follower,RIGHT_TALON_1_INT);
		right_motor_3.set(ControlMode.Follower,RIGHT_TALON_1_INT);
	
		try {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(320, 340);
			camera.setFPS(15);
		} 
			catch(Exception e) {	
		}
	}
}
