public class CommandEquals implements ICommand {
    
    /** 
     * @param srpn The SRPN on which to execute the command
     * @throws ExceptionStackEmpty
     */
    public void execute(SRPN srpn) throws ExceptionStackEmpty {
        srpn.printStackHead();
    }

}
