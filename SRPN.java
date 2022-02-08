
import java.util.ArrayList;
import java.util.Stack;
import java.lang.Math;
import java.util.regex.*;

public class SRPN {

  // the 'stack' is the data structure we will use to store user input for
  // processing and/or display
  private Stack<Integer> stack;

  // the 'rStack' conatains a list of apparently random numbers. When the user
  // enters an 'r' command, a number is
  // popped off this stack and placed on the main stack. When the rStack is empty, SPRN
  // returns "Stack overflow." in response to any more 'r' commands.
  private Stack<Integer> rStack;

  // these static constants contain all legal input characters (apart from
  // whitespace nd digits). We can use them to determine whether input is legal or
  // not
  private static final String legalMathsOperators = "+-*/%^";
  private static final String legalActionKeys = "dr=";

  //this is the regular expression pattern which we'll use to extract discrete input parameters from the user input String. It will always be the same, and so for the sake of only compiling it once, it's placed here as a static member variable, for repeated use later.
  private static Pattern commandPattern = Pattern.compile("(-?[0-9]+)|[=/+-/*//%^dr]");

  public SRPN() {
    stack = new Stack<Integer>();

    rStack = new Stack<Integer>();
    rStack.push(1804289383);
    rStack.push(521595368);
    rStack.push(35005211);
    rStack.push(1303455736);
    rStack.push(304089172);
    rStack.push(1540383426);
    rStack.push(1365180540);
    rStack.push(1967513926);
    rStack.push(2044897763);
    rStack.push(1102520059);
    rStack.push(783368690);
    rStack.push(1350490027);
    rStack.push(1025202362);
    rStack.push(1189641421);
    rStack.push(596516649);
    rStack.push(1649760492);
    rStack.push(719885386);
    rStack.push(424238335);
    rStack.push(1957747793);
    rStack.push(1714636915);
    rStack.push(1681692777);
    rStack.push(846930886);
    rStack.push(1804289383);

  }

  private static String removeComments(String s) {
    // The replaceAll method on String takes a regular expression and replaces all
    // matches within the String with another String
    // The regular expression used here matches anything between "# " and "#", which is
    // treated in the reference SRPN as a comment. We replace the matched comment with an empty string, effectively removing it.
    return s.replaceAll("(#\\s)[^#]*#", "");
  }

  private static ArrayList<String> extractCommands(String s) {
    // make a ArrayList to contain the commands found in this input
    ArrayList<String> commandList = new ArrayList<String>();

    // Compile a regex which finds:
    // - Positive or negative numbers
    // - Any character which has meaning to the SRPN calculator

    // create a Matcher object which will search for matches in the input.
    Matcher commandMatcher = commandPattern.matcher(s);

    // Keep searching for matches and adding them to our list until no more matches
    // are found.
    while (commandMatcher.find()) {
      commandList.add(commandMatcher.group());
    }
    System.out.println("Debug:" + commandList);
    return commandList;

    // Solution inspired by the example found here:
    // https://www.tutorialspoint.com/getting-the-list-of-all-the-matches-java-regular-expressions
  }

  public void processCommand(String s) {
    // first we remove any comments in the input String.
    s = SRPN.removeComments(s);
    // then check that the remaining command string contains only legal characters
    if (!SRPN.isLegalCommand(s)) {
      // if the comand string contains any illegal characters, bail out now.
      return;
    }

    ArrayList<String> items = SRPN.extractCommands(s);



    // Iterate through a the list of extracted commands
    for (String item : items) {
      // if an Integer is received, put it straight on the stack
      try {
        Integer value = Integer.parseInt(item);
        stack.push(value);
        continue;
      } catch (NumberFormatException e) {
        // we can let this go - it just means we coudn't parse an integer.
      }

      // if it's not an integer, it's a char which needs to be actioned
      // Any string which remains at this point should be only 1 char long, so we'll
      // check that's true:

      if (item.length() != 1) {
        System.out.println(String.format("Invalid! something went wrong here with input: %s", item));
      }

      char charToProcess = item.charAt(0);

      if (SRPN.isOperator(charToProcess)) {
        processOperator(charToProcess);
      } else if (charToProcess == '=') {
        this.printStackHead();
      } else if (charToProcess == 'd') {
        this.printStack();
      } else if (charToProcess == 'r') {
        this.rFunction();
      }
    }
  }

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

  private static Boolean isLegalCommand(String s) {
    for (char c : s.toCharArray()) {
      if (!SRPN.isLegalCharacter(c)) {
        System.out.println(String.format("Unrecognised operator or operand \"%c\".", c));
      }
    }
    return true;
  }

  private static Boolean isLegalCharacter(char c) {
    return (Character.isDigit(c) || Character.isWhitespace(c) || SRPN.legalMathsOperators.indexOf(c) != -1
        || SRPN.legalActionKeys.indexOf(c) != -1);
  }

  private static Boolean isOperator(char c) {
    return SRPN.legalMathsOperators.indexOf(c) != -1;
  }

  private void rFunction() {
    if (!rStack.isEmpty()) {
      this.stack.add(rStack.pop());
    } else {
      System.out.println("Stack overflow.");
    }
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
