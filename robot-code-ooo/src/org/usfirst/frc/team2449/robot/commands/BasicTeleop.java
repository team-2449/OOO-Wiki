package org.usfirst.frc.team2449.robot.commands;

import org.usfirst.frc.team2449.robot.OI;
import org.usfirst.frc.team2449.robot.Robot;
import org.usfirst.frc.team2449.robot.RobotMap;
import org.usfirst.frc.team2449.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BasicTeleop extends Command {

	private DriveTrain drivetrain;  //Robot Drivetrain
	private Joystick joystick;  //Driver Joystick
	
    public BasicTeleop() {
    	drivetrain = Robot.drivetrain;
    	joystick = OI.driverJoystick;
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Set robot to do a basic tank drive
    	drivetrain.driveDutyCycle(joystick.getRawAxis(RobotMap.leftAxis), joystick.getRawAxis(RobotMap.rightAxis));  //TODO: Write a better way to interact with the different types of Joysticks
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
