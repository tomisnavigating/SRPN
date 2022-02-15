import java.math.BigInteger;

public class CommandDivide extends Command implements ICommand {
    
    
    /** 
     * @param srpn The SRPN on which to execute the command
     * @throws ExceptionZeroDivision
     * @throws ExceptionStackUnderflow
     * @throws ExceptionStackOverflow
     */
    public void execute(SRPN srpn) throws ExceptionZeroDivision, ExceptionStackUnderflow, ExceptionStackOverflow {

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
