package frc.robot.commands;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
import frc.robot.Robot;

public class CheckState extends Command {

List <boolean> stateList = new ArrayList<>();

    public CheckState()
    {
       
    }


    @Override 
    protected void initialize()
    {
      
    }

    @Override 
    protected void execute()
    {
      
    }

    @Override 
    protected boolean isFinished()
    {
        return false;
    }



    @Override 
    protected void end(){       
      
    }

    @Override 
    protected void interrupted()
    {
        end();
    }
}
