//The code that the 2449 drivetrain deserves

package org.usfirst.frc.team2449.robot.subsystems;

import org.usfirst.frc.team2449.robot.OI;
import org.usfirst.frc.team2449.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	/*
	 * The DriveTrain has 3 constructors, which allow for 2, 4, or 6 motor drivetrains.
	 * Two of these must be Talon SRX motor controllers, in order to use CTRE mag encoders.
	 * The other 2 or 4 controllers (if you use them) can be any CAN motor controller provided by CTRE.
	 * There is a small chance that PWM Motor controllers would work without any extra effot, but I wouldn't bet on it.
	 * Sensors connected to these auxiliary controllers will be ignored (with the exception of the pigeon IMU,
	 * which uses the gadgeteer port as a direct line to the CAN bus.
	 */
	
	private TalonSRX leftTalon;
	private TalonSRX rightTalon;
	
	
    public DriveTrain(TalonSRX leftTalon,TalonSRX rightTalon) {  //Constructor for 2 motor Drivetrain
    	this.leftTalon = leftTalon;
    	this.rightTalon = rightTalon;
    }
    
    public DriveTrain(TalonSRX leftTalon, IMotorController left1Follower, TalonSRX rightTalon, IMotorController right1Follower) {  //Constructor for 4 motor Drivetrain
    	this(leftTalon,rightTalon);
    	left1Follower.follow(this.leftTalon);  //Set the follower Talons to copy the duty cycle of the main talons, which we will be commanding.
    	right1Follower.follow(this.rightTalon);
    }

    public DriveTrain(TalonSRX leftTalon, IMotorController left1Follower, IMotorController left2Follower, TalonSRX rightTalon, IMotorController right1Follower, IMotorController right2Follower) {  //Constructor for 6 motor Drivetrain
    	this(leftTalon,left1Follower,rightTalon,right1Follower);
    	left2Follower.follow(this.leftTalon);  ////Set the follower Talons to copy the duty cycle of the main talons, which we will be commanding.
    	right2Follower.follow(this.rightTalon);
    }
    
    public void driveDutyCycle(double left, double right) {  //Drive motors using duty cycle control
    	leftTalon.set(ControlMode.PercentOutput, left);
    	rightTalon.set(ControlMode.PercentOutput, right);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

