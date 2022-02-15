import java.math.BigInteger;

public class CommandModulus extends Command {
    
    public CommandModulus() {
        try {
            operator = BigInteger.class.getMethod("mod", BigInteger.class);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
