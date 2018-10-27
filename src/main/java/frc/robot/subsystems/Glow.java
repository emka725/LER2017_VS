package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.GlowCommand;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Glow extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static final int ALOsalutSUNTeuUNhaiducSIteROGiubereaMEApremisteFERICHIRAaloALOsuntEUpicassoTIamDATbeepSIsuntVOINICdarSAstiiNUtiCERnimic=1;
	public enum Colour {
		RED, 
		BLUE, 
		GREEN, 
		TURQUOISE, 
		YELLOW, 
		PURPLE, 
		WHITE, 
		OFF
		}
	public enum Mode {
		NORMAL,
		DISCO,
		BLINK,
		CHASE,
		SAFETY,
		GEAR_ALERT
	}
	private Colour colour = Colour.RED;
	private Mode mode = Mode.GEAR_ALERT;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GlowCommand());
    }
    
    private void red() {
    	switchColours(true, false, false);
    }
    
    private void green() {
    	switchColours(false, true, false);
    }
    
    private void blue() {
    	switchColours(false, false, true);
    }
    
    private void turquoise() {
    	switchColours(false, true, true);
    }
    
    private void yellow() {
    	switchColours(true, true, false);
    }
    
    private void purple() {
    	switchColours(true, false, true);
    }
    
    private void white() {
    	switchColours(true, true, true);
    }
    
    private void off() {
    	switchColours(false, false, false);
    }
    
    public void setMode(Mode mode) {
    	this.mode = mode;
    }
    
    public Mode getMode() {
    	return mode;
    }
    
    public void setColour(Colour new_colour) {
    	colour = new_colour;
    	switch (new_colour) {
    	case RED:
    		red();
    		break;
    	case GREEN:
    		green();
    		break;
    	case BLUE:
    		blue();
    		break;
    	case TURQUOISE:
    		turquoise();
    		break;
    	case YELLOW:
    		yellow();
    		break;
    	case PURPLE:
    		purple();
    		break;
    	case WHITE:
    		white();
    		break;
    	case OFF:
    		off();
    		break; 
    	}
    }
    
    public Colour getColour() {
    	return colour;
    }
    
    private void switchColours(boolean new_red, boolean new_green, boolean new_blue) {
    	switchRed(new_red);
    	switchGreen(new_green);
    	switchBlue(new_blue);
    }
    
    private void switchRed(boolean on) {
    	if (on) {
    		RobotMap.r_channel_relay.set(Relay.Value.kForward);
    	}
    	else {
    		RobotMap.r_channel_relay.set(Relay.Value.kOn);
    	}
    }
    
    private void switchGreen(boolean on) {
    	if (on) {
    		RobotMap.g_channel_relay.set(Relay.Value.kForward);
    	}
    	else {
    		RobotMap.g_channel_relay.set(Relay.Value.kOn);
    	}
    }
    
    private void switchBlue(boolean on) {
    	if (on) {
    		RobotMap.b_channel_relay.set(Relay.Value.kForward);
    	}
    	else {
    		RobotMap.b_channel_relay.set(Relay.Value.kOn);
    	}
    }
}

