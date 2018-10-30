package org.usfirst.frc.team2449.robot.commands;

import org.usfirst.frc.team2449.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCurve extends Command {

	private double endFacing;
	private double arcLength;
	private double commandTime;  //Total time the command SHOULD take.  DO NOT USE AS A TIMEOUT!
	private double radius;  //Radius of draw circle
	
    private double setDistance;  //Current setpoint distance
    private double setFacing;  //Current setpoint angle
    private double setVelocity;  //Current set velocity
    
    private double theta;  //Angle from IMU
    private double phi;  //Angle from wheel differential
    private double leftDistance;  //Distance of left side of drivetrain
    private double rightDistance;  //Distance of right side of drivetrain
    
    private double leftSetDistance;
    private double rightSetDistance;
    private double leftSetVelocity;
    private double rightSetVelocity;
    
	
    public DriveCurve(double endFacing, double arcLength) {
    	this.endFacing = endFacing;
    	this.arcLength = arcLength;
    	commandTime = RobotMap.rampUpTime + RobotMap.rampDownTime + (arcLength - (RobotMap.rampUpTime*RobotMap.cruiseSpeed)/2 - (RobotMap.rampDownTime*RobotMap.cruiseSpeed)/2)/RobotMap.cruiseSpeed;  //Figure out the expected time, Both ramp times plus (TotalDistance-DistanceCoveredDuringRamps)/CruiseSpeed
    	radius = arcLength/((endFacing/180)*Math.PI);
    }
    
    public DriveCurve(double x, double y, double endFacing) {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (this.timeSinceInitialized()<RobotMap.rampUpTime) {  //Set variables during ramp up
    		setVelocity = (this.timeSinceInitialized()/RobotMap.rampUpTime)*RobotMap.cruiseSpeed;  //Set velocity to current point in linear ramp
    		setDistance = (this.timeSinceInitialized()*setVelocity)/2;  //Set distance equal to elapsed area in the velocity ramp; D=VT/2
    	} else if (this.timeSinceInitialized()<commandTime-RobotMap.rampDownTime) {
    		setVelocity = RobotMap.cruiseSpeed;
    		setDistance = (this.timeSinceInitialized()*RobotMap.cruiseSpeed)-(RobotMap.rampUpTime*RobotMap.cruiseSpeed)/2;  //Set Distance, CruiseSpeed*time-Amount of area covered during ramp. Seems weird, but math checks out, look at trapezoidal drives 
    	} else {
    		setVelocity = ((commandTime - this.timeSinceInitialized()/RobotMap.rampUpTime))*RobotMap.cruiseSpeed;
    		setDistance = 2*(((commandTime-RobotMap.rampUpTime)*RobotMap.cruiseSpeed)-(RobotMap.rampUpTime*RobotMap.cruiseSpeed)/2) + (commandTime-this.timeSinceInitialized())*setVelocity/2;
    	}
    	setFacing = (setDistance/arcLength)*endFacing;  //Facing/FinalFacing = Distance/FinalDistance
    	
    	leftSetDistance = setDistance*((radius+0.5*RobotMap.trackWidth)/radius);
    	rightSetDistance = setDistance*((radius-0.5*RobotMap.trackWidth)/radius);
    	leftSetDistance = setVelocity*((radius+0.5*RobotMap.trackWidth)/radius);
    	rightSetDistance = setVelocity*((radius-0.5*RobotMap.trackWidth)/radius);
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
