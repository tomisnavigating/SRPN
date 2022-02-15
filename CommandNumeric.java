
import java.math.BigInteger;

import Exceptions.ExceptionStackOverflow;

public class CommandNumeric implements ICommand {
    
    private BigInteger input;

    public CommandNumeric(String s) {
        input = new BigInteger(s);
    }

    @Override
    public void execute(SRPN srpn) throws ExceptionStackOverflow {
        srpn.pushToStack(input);
    }

}
