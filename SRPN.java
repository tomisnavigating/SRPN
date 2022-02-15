
import java.util.ArrayList;
import java.util.Stack;
import java.math.BigInteger;



public class SRPN {

  // the 'stack' is the data structure we will use to store user input for
  // processing and/or display
  private Stack<Integer> stack;

  private PseudoRandomNumberGenerator prng;

  private CommandParser commandParser;

  private static final BigInteger MAX_VALUE = BigInteger.valueOf(Integer.MAX_VALUE);
  private static final BigInteger MIN_VALUE = BigInteger.valueOf(Integer.MIN_VALUE);

  public SRPN() {
    stack = new Stack<Integer>();
    commandParser = new CommandParser();
    prng = new PseudoRandomNumberGenerator();
  }


  
  public void processCommand(String s) {

    // Extract the commands contained within the input using the CommandParser.
    ArrayList<Command> commands = commandParser.ParseInput(s);

    // Iterate through a the list of extracted commands, and execute them.
    for (Command command : commands) {
      
      command.execute(this);
      
    }
  }

  public int getStackSize() {
    return stack.size();
  }  

  
  public void pushToStack(BigInteger value) {

    if (value.compareTo(BigInteger.valueOf(0)) >= 0 ) // a positive number or 0 
    { 
      stack.push(value.min(SRPN.MAX_VALUE).intValue());
    }
    else {
      stack.push(value.max(SRPN.MIN_VALUE).intValue());
    }
  }

  public BigInteger[] getOperands() throws ExceptionStackUnderflow {
    // this function returns the two numbers on top of the stack.
    // The return type is an array of BigIntegers, so that mathematical
    // operations are alowed to overflow the max or min Integer values.
    // The operation results can then be saturated on the way back in.
    if (getStackSize() >= 2) {
      BigInteger [] operands = {BigInteger.valueOf(stack.pop()), BigInteger.valueOf(stack.pop())};
      return operands;
    } 
    else {
      throw new ExceptionStackUnderflow();
    }
  }

  public void addRandomNumberToStack() {
    try {
      stack.add(prng.getRandomNumber());
    }
    catch (ExceptionStackOverflow e) {

    }
  }

  void printStackHead() {
    if (!stack.isEmpty()) {
      System.out.println(this.stack.peek());
    } else {
      System.out.println("Stack empty.");
    }
  }

  void printStack() {
    // prints the contents of the SPRN's stack.
    // A flaw (?) in the SRPN program seems to be that is the stack is empty,
    // -2147483648 is printed

    if (this.stack.isEmpty()) {
      System.out.println(Integer.MIN_VALUE);
    }

    for (int i : this.stack) {
      System.out.println(i);
    }
  }
}
