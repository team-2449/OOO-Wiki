/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2449.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Controller
	public static int driverPort = 0;  //Port for Joystick to be on.  Settable in driverstation.
	public static int leftAxis = 1;  //Number of left Joystick Axis
	public static int rightAxis = 3;  //Number of right Joystick Axis
	
	//Drivetrain
	public static double distancekP = 0;  //Drivetrain encoder PID values.  Check the wiki for how to tune these.
	public static double distancekI = 0;
	public static double distancekD = 0;
	public static double velocitykF = 0;
	public static double ticksPerUnit = 1;  //Sets amount of encoder ticks per arbitrary (linear) unit.  Figure this out experimentally, with current designs, this changes based on gearing, wheel diameter, what stage encoder is placed on, slop, etc.
	public static double trackWidth = 3;  //Distance between center of the 2 'tank tracks'.  Make sure that this is the same unit as above.
	public static double pigeonkP = 0;  //Pigeon IMU pid values used for making point turns.
	public static double pigeonkI = 0;
	public static double pigeonkD = 0;
	public static double kPhi = 0;  //Proportional value for Phi  //PHI IS CURRENTLY NOT ENABLED  TODO: Add Phi Values
	public static double kTheta = 0;  //Proportional value for Theta
	
	/*
	 * A couple quick notes about the autonomous driving functions:
	 * For making a 'point turn' (where the robot turns, ideally without moving linearly in any direction), the encoders are entirely ignored.  The drivetrain has wayyyyyyyy to much wheel slippage to accurately use encoders for ANY turn tight enough to require the sides moving in opposite directions, so these types of turns should be avoided (more on this later).  Once again, this can be avoided with good sensor fusion.
	 * When making an arc turn (or straight line), the robot can be thought of as basically a segway, where the two wheels are the center of the 2 dropped drivetrain wheels.  Do your planning accordingly.  The arc turn functions are designed to be "smooth".  The current turn algorithm works by tracking the dead center of the segway along the arc, as well the segway's facing (using both the distance between the encoders and the IMU reading).
	 * The system runs on a sort of motion profile, however, because we know that we are traveling in a segment of a circular arc, we can run code that is as continuous as the processing speed of the included devices, that is, we don't need preset waypoints, they will be dynamically calculated.
	 * The motors are set by a combination of the desired velocity at any given moment, the desired end position, and the desired angle.
	 * There are two angles used, theta (the angle read by the IMU) and phi (the angle given by the offset of the two encoders).
	 * The algorithm for making an arc is a sort of continuous drive profile.  Instead of making discrete set points that are time driven, we continuously update the desired pose based on time, and velocity based on pose.
	 * The final algoritm looks like:
	 * Output = PID on moving setpoint + velocity feed-forward on moving setpoint + kPhi * desired Angle + kTheta * desired Angle
	 */
}
