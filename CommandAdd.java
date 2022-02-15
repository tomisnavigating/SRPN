import java.math.BigInteger;

public class CommandAdd extends Command implements ICommand {

    /** 
     * @param srpn The SRPN on which to execute the command
     * @throws ExceptionStackUnderflow
     * @throws ExceptionStackOverflow
     */
    public void execute(SRPN srpn) throws ExceptionStackUnderflow, ExceptionStackOverflow {

        super.getOperands(srpn);

        BigInteger result = operandA.add(operandB);

        srpn.pushToStack(result);

    }
}
