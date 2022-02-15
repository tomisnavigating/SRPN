import java.math.BigInteger;

import Exceptions.ExceptionStackUnderflow;

public class CommandSubtract extends Command implements ICommand {
    
    public void execute(SRPN srpn) throws ExceptionStackUnderflow {

        super.getOperands(srpn);
        
        BigInteger result = operandA.subtract(operandB);

        srpn.pushToStack(result);

    }
}
