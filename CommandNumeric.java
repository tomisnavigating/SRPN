
import java.math.BigInteger;

import Exceptions.ExceptionStackOverflow;

public class CommandNumeric implements ICommand {
    
    private BigInteger input;

    public CommandNumeric(String s) {
        input = new BigInteger(s);
    }

    
    /** 
     * @param srpn The SRPN on which to execute the command
     * @throws ExceptionStackOverflow
     */
    @Override
    public void execute(SRPN srpn) throws ExceptionStackOverflow {
        srpn.pushToStack(input);
    }

}
