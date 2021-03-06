import java.math.BigInteger;

public class CommandModulus extends Command implements ICommand {
    
    /** 
     * @param srpn The SRPN on which to execute the command
     * @throws ExceptionStackUnderflow
     * @throws ExceptionStackOverflow
     */
    public void execute(SRPN srpn) throws ExceptionStackUnderflow, ExceptionStackOverflow {

        super.getOperands(srpn);

        BigInteger result = operandA.mod(operandB);

        srpn.pushToStack(result);

    }
}
