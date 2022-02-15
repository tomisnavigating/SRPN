import Exceptions.ExceptionStackOverflow;

public class CommandRandom implements ICommand {
    
    @Override
    public void execute(SRPN srpn) throws ExceptionStackOverflow {
        srpn.addRandomNumberToStack();
    }

}
