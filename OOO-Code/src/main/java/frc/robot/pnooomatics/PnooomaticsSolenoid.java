/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.pnooomatics;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Add your docs here.
 */
public class PnooomaticsSolenoid extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private DoubleSolenoid solenoid;
  private double value = -1;

  public PnooomaticsSolenoid(int forwardChannel, int reverseChannel){
    solenoid = new DoubleSolenoid(forwardChannel, reverseChannel);
    solenoid.set(Value.kReverse);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Handle());
  }

  private class Handle extends Command{
    
    @Override
    protected void execute() {
      
    }

    @Override
    protected boolean isFinished() {
      return false;
    }
  }
}
