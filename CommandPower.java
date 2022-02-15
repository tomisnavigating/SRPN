import java.math.BigInteger;

import Exceptions.ExceptionNegativePower;
import Exceptions.ExceptionStackOverflow;
import Exceptions.ExceptionStackUnderflow;

public class CommandPower extends Command implements ICommand {

    /**
     * @param srpn The SRPN on which to execute the command
     * @throws ExceptionStackUnderflow
     * @throws ExceptionStackOverflow
     * @throws ExceptionNegativePower
     */
    public void execute(SRPN srpn) throws ExceptionStackUnderflow, ExceptionStackOverflow, ExceptionNegativePower {

        super.getOperands(srpn);

        try {
            BigInteger result = operandA.pow(operandB.intValue());
            srpn.pushToStack(result);
        } catch (ArithmeticException e) {
            super.replaceOperands(srpn);
            throw new ExceptionNegativePower();
        }

    }
}
