import java.math.BigInteger;

import Exceptions.ExceptionStackOverflow;
import Exceptions.ExceptionStackUnderflow;

public class CommandAdd extends Command implements ICommand {

    public void execute(SRPN srpn) throws ExceptionStackUnderflow, ExceptionStackOverflow {

        super.getOperands(srpn);

        BigInteger result = operandA.add(operandB);

        srpn.pushToStack(result);

    }
}
