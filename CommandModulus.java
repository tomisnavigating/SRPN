import java.math.BigInteger;

import Exceptions.ExceptionStackOverflow;
import Exceptions.ExceptionStackUnderflow;

public class CommandModulus extends Command implements ICommand {
    
    public void execute(SRPN srpn) throws ExceptionStackUnderflow, ExceptionStackOverflow {

        super.getOperands(srpn);

        BigInteger result = operandA.mod(operandB);

        srpn.pushToStack(result);

    }
}
