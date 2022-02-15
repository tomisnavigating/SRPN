import java.math.BigInteger;

import Exceptions.ExceptionStackUnderflow;
import Exceptions.ExceptionZeroDivision;

public class CommandDivide extends Command implements ICommand {
    
    public void execute(SRPN srpn) throws ExceptionZeroDivision, ExceptionStackUnderflow{

        super.getOperands(srpn);
        
        try {
            BigInteger result = operandA.divide(operandB);
            srpn.pushToStack(result);
        } catch (ArithmeticException e) {
            super.replaceOperands(srpn);
            throw new ExceptionZeroDivision();
        }
    }
}
