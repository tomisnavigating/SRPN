import java.math.BigInteger;

import Exceptions.ExceptionStackUnderflow;

public class CommandMultiply extends Command implements ICommand {
    
    public void execute(SRPN srpn) throws ExceptionStackUnderflow {

        super.getOperands(srpn);

        BigInteger result = operandA.multiply(operandB);

        srpn.pushToStack(result);

    }
}
