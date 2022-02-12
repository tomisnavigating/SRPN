
import java.util.ArrayList;
import java.util.Stack;
import java.lang.Math;



public class SRPN {

  // the 'stack' is the data structure we will use to store user input for
  // processing and/or display
  private Stack<Integer> stack;

  //
  private PseudoRandomNumberGenerator prng;

  private CommandParser commandParser;



  public SRPN() {
    stack = new Stack<Integer>();
    commandParser = new CommandParser();
    prng = new PseudoRandomNumberGenerator();
  }

  
  

  public void processCommand(String s) {
    // first we remove any comments in the input String.
    // s = SRPN.removeComments(s);
    // then check that the remaining command string contains only legal characters
    // if (!SRPN.isLegalCommand(s)) {
    //   // if the comand string contains any illegal characters, bail out now.
    //   return;
    // }

    // ArrayList<String> items = SRPN.extractCommands(s);
    ArrayList<Command> commands = commandParser.ParseInput(s);



    // Iterate through a the list of extracted commands
    for (Command command : commands) {
      
      command.execute(this);
      
    }
  }

  
  /** 
   * @param operator
   */
  private void processOperator(char operator) {

    if (this.stack.size() < 2) {
      System.out.println("Stack underflow.");
      return;
    }

    // We'll perform the maths operations on Long type, and then cast to Integer,
    // handling saturation.

    Long b = Long.valueOf(this.stack.pop());
    Long a = Long.valueOf(this.stack.pop());

    Long result = null;

    // The following switch statement provides a code path for each possible operator.
    switch (operator) {
    case '+':
      result = a + b;
      break;

    case '-':
      result = a - b;
      break;

    case '*':
      result = a * b;
      break;

    case '/':
      // prevent propagation of zero division exception by using a try catch:
      try {
        result = a / b;
      } catch (ArithmeticException e) {
        System.out.println("Divide by 0.");
      }
      break;

    case '%':
      result = a % b;
      break;

    case '^':
      result = (long) Math.pow(a, b);
      break;
    }

    if (result != null) {

      if (result > Long.valueOf(Integer.MAX_VALUE)) {
        this.stack.push(Integer.MAX_VALUE);
      } else if (result < Long.valueOf(Integer.MIN_VALUE)) {
        this.stack.push(Integer.MIN_VALUE);
      } else {
        this.stack.push(result.intValue());
      }
    }

  }

  

  private void rFunction() {
    try {
      stack.add(prng.getRandomNumber());
    }
    catch (ExceptionStackOverflow e) {
      printException(e);
    }
  }

  
  /** 
   * @param e
   */
  private static void printException(Exception e) {
    
    System.out.println(e.getMessage());
  }

  private void printStackHead() {
    if (!stack.isEmpty()) {
      System.out.println(this.stack.peek());
    } else {
      System.out.println("Stack empty.");
    }
  }

  private void printStack() {
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
