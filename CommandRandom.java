public class CommandRandom implements ICommand {
    
    
    /** 
     * @param srpn The SRPN on which to execute the command
     * @throws ExceptionStackOverflow
     */
    public void execute(SRPN srpn) throws ExceptionStackOverflow {

        srpn.addRandomNumberToStack();
        
    }

}
