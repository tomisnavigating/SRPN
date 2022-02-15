
import java.math.BigInteger;

public class CommandNumeric implements ICommand {
    
    private BigInteger input;

    public CommandNumeric(String s) {
        input = new BigInteger(s);
    }

    @Override
    public void execute(SRPN srpn) {
        srpn.pushToStack(input);
    }

}
