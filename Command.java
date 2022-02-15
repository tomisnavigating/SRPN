import java.math.BigInteger;

import Exceptions.ExceptionStackUnderflow;

public class Command {
    
    protected BigInteger operandA;
    protected BigInteger operandB;

    protected void getOperands(SRPN srpn) throws ExceptionStackUnderflow {

        BigInteger[] operands = srpn.getOperands();
        operandA = operands[1];
        operandB = operands[0];
    }

}
