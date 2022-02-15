import Exceptions.ExceptionStackEmpty;

public class CommandEquals implements ICommand {
    
    /** 
     * @param srpn The SRPN on which to execute the command
     * @throws ExceptionStackEmpty
     */
    @Override
    public void execute(SRPN srpn) throws ExceptionStackEmpty {
        srpn.printStackHead();
    }

}
