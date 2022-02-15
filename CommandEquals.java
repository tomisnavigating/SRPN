import Exceptions.ExceptionStackEmpty;

public class CommandEquals implements ICommand {
    
    @Override
    public void execute(SRPN srpn) throws ExceptionStackEmpty {
        srpn.printStackHead();
    }

}
