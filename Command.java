import java.math.BigInteger;

public class Command {
    /*
     * This is the base Command class.
     * It contains methods which are required by multiple Subclasses to reduce
     * repetition.
     */

    protected BigInteger operandA;
    protected BigInteger operandB;

    /**
     * Method to be used from subclasses of Command to retreive the operands
     * required for mathematical operations
     * 
     * @param srpn the SRPN to operate upon.
     * @throws ExceptionStackUnderflow if there are not 2 numbers on stack
     */
    protected void getOperands(SRPN srpn) throws ExceptionStackUnderflow {

        BigInteger[] operands = srpn.getOperands();
        operandA = operands[1];
        operandB = operands[0];
    }

    /**
     * Method to be used from subclasses of Command to replace the operands if the
     * mathematical operation fails
     * 
     * @param srpn the SRPN to operate upon.
     */
    public void replaceOperands(SRPN srpn) throws ExceptionStackOverflow {
        srpn.pushToStack(operandA);
        srpn.pushToStack(operandB);
    }

}
