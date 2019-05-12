/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.pnooomatics;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Timer;

/**
 * Add your docs here.
 */
public class PnooomaticsCompressor extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Compressor compressor = new Compressor();

  public enum ControlMode{
    max, minmax
  }

  private ControlMode controlMode = ControlMode.max;
  private AnalogInput pressureSensor;
  private boolean enabled = true;  //Used for manual enable/disable

  private double min = 0;
  private double max = 120;
  private double timeConstant = 0;

  public void setControlMode(ControlMode controlMode, double timeConstant){
    this.controlMode = controlMode;
    this.timeConstant = timeConstant;
  }

  public PnooomaticsCompressor(int sensorPort){
    pressureSensor = new AnalogInput(sensorPort);
  }

  public void setEnabled(boolean enabled){
    this.enabled = enabled;
  }

  public double getPressure(){
    return 250*(pressureSensor.getAverageVoltage()/5)- 25;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Handler());
    compressor.start();
  }

  
  private class Handler extends Command {

    @Override
    protected void execute() {
      double currentMax = max - timeConstant*this.timeSinceInitialized();
      currentMax = currentMax<min ? min : currentMax;

      if(enabled){
        if(controlMode == ControlMode.max){
          if(getPressure()<currentMax){
            compressor.start();
          } else {
            compressor.stop();
          }
        }else if(controlMode == ControlMode.minmax){
          if(getPressure()<=min){
            compressor.start();
          } else if(getPressure()>=max) {
            compressor.stop();
          }
        }
      } else {
        compressor.stop();
      }
    }

    @Override
    protected boolean isFinished() {
      return false;
    }
  }
}
