import java.math.BigInteger;

import Exceptions.ExceptionStackUnderflow;
import Exceptions.ExceptionZeroDivision;

public class CommandPower extends Command implements ICommand {
    
    public void execute(SRPN srpn) throws ExceptionStackUnderflow, ExceptionZeroDivision {

        super.getOperands(srpn);

        try {
            BigInteger result = operandA.pow(operandB.intValue());
            srpn.pushToStack(result);
        } catch (ArithmeticException e) {
            throw new ExceptionZeroDivision();
        }


    }
}
