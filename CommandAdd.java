
import java.math.BigInteger;

public class CommandAdd extends Command {
    

    public CommandAdd() {
        try {
            operator = BigInteger.class.getMethod("add", BigInteger.class);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
