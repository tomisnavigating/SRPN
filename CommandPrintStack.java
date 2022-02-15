import Exceptions.ExceptionPrintEmptyStack;

public class CommandPrintStack implements ICommand {
    
    @Override
    public void execute(SRPN srpn) throws ExceptionPrintEmptyStack {
        srpn.printStack();
    }

}
