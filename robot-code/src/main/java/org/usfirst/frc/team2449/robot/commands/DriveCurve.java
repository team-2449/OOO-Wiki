package org.usfirst.frc.team2449.robot.commands;

import org.usfirst.frc.team2449.robot.Robot;
import org.usfirst.frc.team2449.robot.RobotMap;
import org.usfirst.frc.team2449.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	private double timeRemaining;  //All warnings for the commandTime variable apply to this too.
	
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
    
	//TODO: Explain how this function works a little better
    
    public DriveCurve(double endFacing, double radius, double cruiseSpeed, double rampTime) {  //This Constructor is sort of difficult for humans to use, but is the easiest to program.  You may want to use one of the overloaded constructors instead
    	//Set class variables equal to constructors
    	this.endFacing = endFacing;
    	this.radius = radius;
    	this.cruiseSpeed = cruiseSpeed;
    	this.rampTime = rampTime;
    	
    	//Calculate Helper Variables Based on Arguments
    	this.arcLength = 2*radius*Math.PI*(Math.abs(endFacing)/360);  //Set Arc Length equal to the full circle circumference over the amount of the circle we will actually be traversing
    	this.commandTime = (arcLength/cruiseSpeed) + rampTime;  //Whew.  Visualize the distance as a rectangle, speed over time.  Slant both edges to account for the ramp times.  The area you removed from each end can be added to form a new rectangle of height cruiseSpeed and length rampTime, which accounts for the extra time.  You can also verify this through algebra.
    	
    	//Set local variables to reference statics
    	this.drivetrain = Robot.drivetrain;
    	requires(Robot.drivetrain);
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
    	timeRemaining = commandTime - this.timeSinceInitialized();
    	
    	//Update Setpoints
    	if(this.timeSinceInitialized()<rampTime) {  //During Ramp Up
    		setVelocity = cruiseSpeed*(this.timeSinceInitialized()/rampTime);  //Sets velocity as linear, from 0 at beginning to cruise speed once ramp is finished
    		setDistance = (setVelocity*this.timeSinceInitialized())/2;  //Sets distance as the area of the function set by velocity, which is a triangle
    	} else if (timeRemaining>rampTime){  //During Cruise
    		setVelocity = cruiseSpeed;  //During cruise, we run at cruising velocity
    		setDistance = cruiseSpeed*(this.timeSinceInitialized()+(this.timeSinceInitialized()-rampTime))/2;  //Distance is area of trapezoid made by velocity function.  TODO: Simplify this math?  
    	} else {
    		setVelocity = cruiseSpeed*(timeRemaining/rampTime);
    		setDistance = arcLength - (setVelocity*this.timeRemaining)/2;
    	}
    	setFacing = endFacing*(setDistance / arcLength);
    	
    	//Set Setpoints for each side
    	leftSetDistance = setDistance*((2*radius+RobotMap.trackWidth)/(2*radius));
    	rightSetDistance = setDistance*((2*radius-RobotMap.trackWidth)/(2*radius));
    	
    	leftSetVelocity = setVelocity*((2*radius+RobotMap.trackWidth)/(2*radius));
    	rightSetVelocity = setVelocity*((2*radius-RobotMap.trackWidth)/(2*radius));
    	
    	SmartDashboard.putNumber("Facing", setFacing);
    	SmartDashboard.putNumber("Distance", setDistance);
    	SmartDashboard.putNumber("Velocity", setVelocity);
    	SmartDashboard.putNumber("leftDistance", leftSetDistance);
    	SmartDashboard.putNumber("rightDistance", rightSetDistance);
    	SmartDashboard.putNumber("leftVelocity", leftSetDistance);
    	SmartDashboard.putNumber("rightVelocity", rightSetVelocity);
    	SmartDashboard.putNumber("Time",this.timeSinceInitialized());
    	SmartDashboard.putNumber("Time Remaining", timeRemaining);
    	
    	leftPower = leftSetVelocity*RobotMap.velocitykF+(leftSetDistance-drivetrain.getLeftDistance())-(setFacing-drivetrain.getEncoderFacing());
    	rightPower = rightSetVelocity*RobotMap.velocitykF+(rightSetDistance-drivetrain.getRightDistance())+(setFacing-drivetrain.getEncoderFacing());
    	
    	drivetrain.driveDutyCycle(leftPower, rightPower);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.setVelocity<0;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
