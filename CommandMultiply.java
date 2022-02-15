import java.math.BigInteger;

import Exceptions.ExceptionStackOverflow;
import Exceptions.ExceptionStackUnderflow;

public class CommandMultiply extends Command implements ICommand {
    
    
    /** 
     * @param srpn The SRPN on which to execute the command
     * @throws ExceptionStackUnderflow
     * @throws ExceptionStackOverflow
     */
    public void execute(SRPN srpn) throws ExceptionStackUnderflow, ExceptionStackOverflow {

        super.getOperands(srpn);

        BigInteger result = operandA.multiply(operandB);

        srpn.pushToStack(result);

    }
}
