import Exceptions.ExceptionPrintEmptyStack;

public class CommandPrintStack implements ICommand {
    
    
    /** 
     * @param srpn The SRPN on which to execute the command
     * @throws ExceptionPrintEmptyStack
     */
    public void execute(SRPN srpn) throws ExceptionPrintEmptyStack {

        srpn.printStack();
        
    }

}
