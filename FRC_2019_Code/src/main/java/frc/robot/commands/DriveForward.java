package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
import frc.robot.Robot;
import edu.wpi.first.wpilibj.AnalogGyro;
import com.revrobotics.CANEncoder;
import frc.robot.RobotMap;
import frc.robot.Settings;


public class DriveForward extends Command {

    double driveSpeed;
    double driveDistance;
    double angle;
    double turnSpeed;
    double Kp = .04;  //turn intensity
    double moveSpeed = 0;
    double loop = 0;
    double turnAngle = 0;
    private CANEncoder driveEncoder = Robot.m_drivetrain.getLeftEncoder();
    private AnalogGyro gyro = new AnalogGyro(RobotMap.ROBOT_GYRO);
    

    public DriveForward(double inches, double drivespeed, double turnangle, double turnspeed)
    {
        requires(Robot.m_drivetrain);
        driveDistance = inches*(Settings.Encoder_RotationsPerInch);
        driveSpeed = drivespeed;
        angle = turnangle;
        turnSpeed = turnspeed;
    }


    @Override 
    protected void initialize()
    {
        Robot.m_drivetrain.ResetEncoder();
        gyro.reset();
    }

    @Override 
    protected void execute()
    {
        turnAngle = 0;
        moveSpeed = 0;
		if(Math.abs(driveEncoder.getPosition()) < Math.abs(driveDistance)){ //negative is forward
        
            moveSpeed = driveSpeed; //positive is forward
            turnAngle = -angle + gyro.getAngle();
            turnAngle = turnAngle*Kp;

            if (++loop >= 10) {
                loop = 0;
                System.out.println("turnAngle: " + turnAngle +" |Gyro: "+ gyro.getAngle());
                System.out.println(driveEncoder.getPosition());
            }
           
            
            
            }

            if (moveSpeed > 1){moveSpeed = 1;}
	        if (Math.abs(moveSpeed) < 0.10) {
			    moveSpeed = 0;
            }
           // if (turnSpeed > 1){turnSpeed = 1;}
	        //if (Math.abs(turnAngle) < 0.10) {
			//    turnSpeed = 0;
            //}
            if (turnAngle > turnSpeed){turnAngle = turnSpeed;}
            if (turnAngle < -turnSpeed){turnAngle = -turnSpeed;}
        
        Robot.m_drivetrain.arcadeDrive(moveSpeed, turnAngle);

        //System.out.println(Math.abs(driveDistance));
        //System.out.println(Math.abs(leftSlave1Talon.getSelectedSensorPosition()));
    }
    

    @Override 
    protected boolean isFinished()
    {
        return false;
    }



    @Override 
    protected void end(){       
        Robot.m_drivetrain.arcadeDrive(0,0);
    }

    @Override 
    protected void interrupted()
    {
        end();
    }
}