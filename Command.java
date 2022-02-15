import java.lang.reflect.Method;
import java.math.BigInteger;

public class Command {

    // This is an abstract base class for indivicual commands entered by the user.
    // The SPRN class doesn't need to know how different concrete commands are implemented,
    // it just needs to give the command access to its stack; the command can execute itself.
        protected static Method operator;

        public CommandAdd() {
            try {
                operator = BigInteger.class.getMethod("add", BigInteger.class);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        public void execute(SRPN srpn) {

            try {
            BigInteger[] operands = srpn.getOperands();

            try {
                BigInteger result = (BigInteger) operator.invoke(operands[0], operands[1]);
                srpn.pushToStack(result);

            } catch (Exception e) {
                System.out.println(e);
            }

            } catch (ExceptionStackUnderflow e) {

            }
            

        }
}
