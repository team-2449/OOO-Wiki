//The code that the 2449 drivetrain deserves

package org.usfirst.frc.team2449.robot.subsystems;

import org.usfirst.frc.team2449.robot.OI;
import org.usfirst.frc.team2449.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	/*
	 * The DriveTrain has 3 constructors, which allow for 2, 4, or 6 motor
	 * drivetrains. Two of these must be Talon SRX motor controllers, in order to
	 * use CTRE mag encoders. The other 2 or 4 controllers (if you use them) can be
	 * any CAN motor controller provided by CTRE. There is a small chance that PWM
	 * Motor controllers would work without any extra effot, but I wouldn't bet on
	 * it. Sensors connected to these auxiliary controllers will be ignored (with
	 * the exception of the pigeon IMU, which uses the gadgeteer port as a direct
	 * line to the CAN bus.
	 */

	private TalonSRX leftTalon;
	private TalonSRX rightTalon;

	private DriveTrain(TalonSRX leftTalon, TalonSRX rightTalon) { // Constructor for 2 motor Drivetrain
		this.leftTalon = leftTalon;
		this.rightTalon = rightTalon;
		rightTalon.setInverted(true);
		// Set up feedback sensors
		leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
		rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
	}

	private DriveTrain(TalonSRX leftTalon, IMotorController left1Follower, TalonSRX rightTalon,
			IMotorController right1Follower) { // Constructor for 4 motor Drivetrain
		this(leftTalon, rightTalon);
		left1Follower.follow(this.leftTalon); // Set the follower Talons to copy the duty cycle of the main talons,
												// which we will be commanding.
		right1Follower.follow(this.rightTalon);
		right1Follower.setInverted(false); // TODO: Set this back to true, this is to correct a wiring error on the 2018
											// bot.
	}

	private DriveTrain(TalonSRX leftTalon, IMotorController left1Follower, IMotorController left2Follower,
			TalonSRX rightTalon, IMotorController right1Follower, IMotorController right2Follower) { // Constructor for
																										// 6 motor
																										// Drivetrain
		this(leftTalon, left1Follower, rightTalon, right1Follower);
		left2Follower.follow(this.leftTalon); //// Set the follower Talons to copy the duty cycle of the main talons,
												//// which we will be commanding.
		right2Follower.follow(this.rightTalon);
		right2Follower.setInverted(true);
	}

	public void driveDutyCycle(double left, double right) { // Drive motors using duty cycle control
		leftTalon.set(ControlMode.PercentOutput, left);
		rightTalon.set(ControlMode.PercentOutput, right);
	}

	public double getLeftDistance() {
		return leftTalon.getSelectedSensorPosition() / RobotMap.ticksPerUnit;
	}

	public double getRightDistance() {
		return rightTalon.getSelectedSensorPosition() / RobotMap.ticksPerUnit;
	}

	public double getLeftVelocity() {
		return leftTalon.getSelectedSensorVelocity() / RobotMap.ticksPerUnit;
	}

	public double getRightVelocity() {
		return rightTalon.getSelectedSensorVelocity() / RobotMap.ticksPerUnit;
	}

	public double getIMUFacing() {
		return 0;
	}

	public double getEncoderFacing() {
		return 0;
	}

	public void tareSensors() { // Set sensors to zero
		leftTalon.setSelectedSensorPosition(0);
		rightTalon.setSelectedSensorPosition(0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public static DriveTrain createDriveTrain(String controllerType, int motorCount) { // Factory for creating
																						// drivetrain objects. TODO: Add
																						// functionality for using
																						// Victor SPX's
		DriveTrain drivetrain = null;
		if (controllerType.toLower().Compare("talonsrx") == 0) {
			switch (motorCount) {
			case 2:
				drivetrain = new DriveTrain(new TalonSRX(0), new TalonSRX(1));
				break;
			case 4:
				drivetrain = new DriveTrain(new TalonSRX(0), new TalonSRX(1), new TalonSRX(2), new TalonSRX(3));
				break;
			case 6:
				drivetrain = new DriveTrain(new TalonSRX(0), new TalonSRX(1), new TalonSRX(2), new TalonSRX(3),
						new TalonSRX(4), new TalonSRX(5));
				break;
			default:
				try {
					throw new Exception("Please Set Drivetrain Motor Count to 2, 4, or 6");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		} else if (controllerType.toLower().Compare("sparkmax") == 0) {
			switch (motorCount) {
			case 2:
				drivetrain = new DriveTrain(new , new TalonSRX(1));
				break;
			case 4:
				drivetrain = new DriveTrain(new TalonSRX(0), new TalonSRX(1), new TalonSRX(2), new TalonSRX(3));
				break;
			case 6:
				drivetrain = new DriveTrain(new TalonSRX(0), new TalonSRX(1), new TalonSRX(2), new TalonSRX(3),
						new TalonSRX(4), new TalonSRX(5));
				break;
			default:
				try {
					throw new Exception("Please Set Drivetrain Motor Count to 2, 4, or 6");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		return drivetrain;
	}
}
