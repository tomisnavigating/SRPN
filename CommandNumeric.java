
import java.math.BigInteger;

public class CommandNumeric implements ICommand {
    
    private BigInteger input;

    public CommandNumeric(String s) {
        input = new BigInteger(s);
    }

    /** 
     * @param srpn The SRPN on which to execute the command
     * @throws ExceptionStackOverflow
     */
    public void execute(SRPN srpn) throws ExceptionStackOverflow {
        srpn.pushToStack(input);
    }

}
