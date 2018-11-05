/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import frc.robot.commands.AlignGearVision;
import frc.robot.commands.ChangeGlowColour;
import frc.robot.commands.ChangeGlowMode;
import frc.robot.commands.IntakeGears;
import frc.robot.commands.OuttakeGears;
import frc.robot.commands.SwitchPivotPositionCommand;
import frc.robot.subsystems.Glow;
import frc.robot.subsystems.Pivot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private final int L_JOYSTICK_INT = 0;
  private final int R_JOYSTICK_INT = 1;
  private final int XBOX_INT = 2;

  private final int ARCADE_GYRO_BUTTON_INT = 2;

  private final int FLASH_BLUE_BUTTON_INT = 2;

  private final int ALIGN_GEAR_BUTTON_INT = 7;

  private final int GEAR_INTAKE_PULL_BUTTON_INT = XboxController.XBOX_B;
  private final int GEAR_INTAKE_PUSH_BUTTON_INT = XboxController.XBOX_RB;

  private final int PIVOT_DOWN_BUTTON_INT = XboxController.XBOX_A;
  private final int PIVOT_UP_BUTTON_INT = XboxController.XBOX_X;
  private final int PIVOT_BACK_BUTTON_INT = XboxController.XBOX_Y;
  private final int ELEVATOR_BUTTON_INT = XboxController.XBOX_START;
  private final int RELEASE_ELEVATOR_BUTTON_INT = XboxController.XBOX_BACK;

  //private final int ROLLER_INTAKE_BUTTON_INT = XboxController.XBOX_LB;

  public Joystick l_joystick = new Joystick(L_JOYSTICK_INT);
  public Joystick r_joystick = new Joystick(R_JOYSTICK_INT);

  public Joystick xbox = new Joystick(XBOX_INT);

  public JoystickButton arcade_gyro_button = new JoystickButton(r_joystick, ARCADE_GYRO_BUTTON_INT);

  public JoystickButton align_gear_button = new JoystickButton(l_joystick, ALIGN_GEAR_BUTTON_INT);

  public JoystickButton flash_blue_button = new JoystickButton(l_joystick, FLASH_BLUE_BUTTON_INT);

  public JoystickButton gear_intake_pull_button = new JoystickButton(xbox, GEAR_INTAKE_PULL_BUTTON_INT);
  public JoystickButton gear_intake_push_button = new JoystickButton(xbox, GEAR_INTAKE_PUSH_BUTTON_INT);
  //public JoystickButton roller_intake_button = new JoystickButton(xbox, ROLLER_INTAKE_BUTTON_INT);

  public JoystickButton pivot_down_button = new JoystickButton(xbox, PIVOT_DOWN_BUTTON_INT);
  public JoystickButton pivot_up_button = new JoystickButton(xbox, PIVOT_UP_BUTTON_INT);
  public JoystickButton pivot_back_button = new JoystickButton(xbox, PIVOT_BACK_BUTTON_INT);

  public JoystickButton elevate_button = new JoystickButton(xbox, ELEVATOR_BUTTON_INT);
  public JoystickButton release_elevator_button = new JoystickButton(xbox, RELEASE_ELEVATOR_BUTTON_INT);

  public OI() {
    pivot_down_button.whenPressed(new SwitchPivotPositionCommand(Pivot.ANGLE_DOWN));
    pivot_up_button.whenPressed(new SwitchPivotPositionCommand(Pivot.ANGLE_UP));
    pivot_back_button.whenPressed(new SwitchPivotPositionCommand(Pivot.ANGLE_BACK));

    align_gear_button.whileHeld(new AlignGearVision());

    //flash_blue_button.whenPressed(new ChangeGlowMode(Glow.Mode.NORMAL));
    flash_blue_button.whenPressed(new ChangeGlowColour(Glow.Colour.BLUE));
    flash_blue_button.whenReleased(new ChangeGlowMode(Glow.Mode.GEAR_ALERT));

    elevate_button.whenReleased(new ChangeGlowMode(Glow.Mode.GEAR_ALERT));

    gear_intake_pull_button.whileHeld(new IntakeGears());
    gear_intake_push_button.whileHeld(new OuttakeGears());
  }
}
