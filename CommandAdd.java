import java.math.BigInteger;

import Exceptions.ExceptionStackUnderflow;

public class CommandAdd extends Command implements ICommand {

    public void execute(SRPN srpn) throws ExceptionStackUnderflow {

        super.getOperands(srpn);

        BigInteger result = operandA.add(operandB);

        srpn.pushToStack(result);

    }
}
