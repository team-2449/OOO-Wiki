package org.usfirst.frc.team2449.robot.commands;

import org.usfirst.frc.team2449.robot.Robot;
import org.usfirst.frc.team2449.robot.RobotMap;
import org.usfirst.frc.team2449.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCurve extends Command {

	//Parameter Variables for Drive Function
	private double endFacing;
	private double radius;  //Radius of drawn circle
	private double cruiseSpeed;
	private double rampTime;
	
	//Helper Variables for Commonly Used Values
	
	private double arcLength;  //Radius of drawn circle
	private double commandTime;  //Total time it **SHOULD** take command to execute.  Don't use this for anything important, like a command timeout.  It is only used to help in math.
	
	//Path Setpoints
    private double setDistance;  //Current setpoint distance
    private double setFacing;  //Current setpoint angle
    private double setVelocity;  //Current set velocity
    
    //Sensor Readings
    private double theta;  //Angle from IMU
    private double phi;  //Angle from wheel differential
    private double leftDistance;  //Distance of left side of drivetrain
    private double rightDistance;  //Distance of right side of drivetrain
    
    //Motor Setpoints
    private double leftSetDistance;
    private double rightSetDistance;
    private double leftSetVelocity;
    private double rightSetVelocity;
    private double leftPower;
    private double rightPower;
    
    private DriveTrain drivetrain;
    
	
    public DriveCurve(double endFacing, double radius, double cruiseSpeed, double rampTime) {  //This Constructor is sort of difficult for humans to use, but is the easiest to program.  You may want to use one of the overloaded constructors instead
    	//Set class variables equal to constructors
    	this.endFacing = endFacing;
    	this.radius = radius;
    	this.cruiseSpeed = cruiseSpeed;
    	this.rampTime = rampTime;
    	
    	//Calculate Helper Variables Based on Arguments
    	this.arcLength = radius*Math.PI*(endFacing/360);  //Set Arc Length equal to the full circle circumference over the amount of the circle we will actually be traversing
    	this.commandTime = (arcLength/cruiseSpeed) + rampTime;  //Whew.  Visualize the distance as a rectangle, speed over time.  Slant both edges to account for the ramp times.  The area you removed from each end can be added to form a new rectangle of height cruiseSpeed and length rampTime, which accounts for the extra time.  You can also verify this through algebra.
    	
    	//Set local variables to reference statics
    	this.drivetrain = Robot.drivetrain;
    }
    
    public DriveCurve(double x, double y, double endFacing) {  //Overload to make the math a bit easier on the person writing paths
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivetrain.tareSensors();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Update Sensor Readings
    	theta = drivetrain.getIMUFacing();
    	leftDistance = drivetrain.getLeftDistance();
    	rightDistance = drivetrain.getRightDistance();
    	phi = 0;  //TODO: Add Phi Capability
    	
    	//Update Setpoints
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
