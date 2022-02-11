import java.util.Stack;

public class PseudoRandomNumberGenerator {
    
        /*
        This class is used to supply a "random" numbers.
        The legacy SRPN calculator produces a stream of pseudorandom 
        numbers, which (at least in the replit environment) appears to 
        always be seeded with the same number, and hence the sequence is 
        the same each time the software is run. In order to replicate this
        behaviour, this class simply contains that list of numbers.
        After the generator has provided 23 random numbers its supply is 
        exhausted, and the message "Stack overflow." is printed.
        */

        // the stack will contain the supply of random numbers.
        private Stack<Integer> stack;

    public PseudoRandomNumberGenerator() {

        // Initialise the stack
        stack = new Stack<Integer>();

        // push the pseudorandom numbers into it in reverse order
        // so that they pop off in the same order as the legacy calculator.
        stack.push(1804289383);
        stack.push(521595368);
        stack.push(35005211);
        stack.push(1303455736);
        stack.push(304089172);
        stack.push(1540383426);
        stack.push(1365180540);
        stack.push(1967513926);
        stack.push(2044897763);
        stack.push(1102520059);
        stack.push(783368690);
        stack.push(1350490027);
        stack.push(1025202362);
        stack.push(1189641421);
        stack.push(596516649);
        stack.push(1649760492);
        stack.push(719885386);
        stack.push(424238335);
        stack.push(1957747793);
        stack.push(1714636915);
        stack.push(1681692777);
        stack.push(846930886);
        stack.push(1804289383); 
    }

    public Integer getRandomNumber() throws ExceptionStackOverflow {

        // This function either provides the next pseudorandom number 
        // in sequence, or throws an appropriate exception if the stack is
        // empty.

        if (!stack.isEmpty()) {
            return stack.pop();
        } else {
            throw new ExceptionStackOverflow();
        }
    }
}