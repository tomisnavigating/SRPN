
import java.util.ArrayList;
import java.util.Stack;

import Exceptions.ExceptionPrintEmptyStack;
import Exceptions.ExceptionStackEmpty;
import Exceptions.ExceptionStackOverflow;
import Exceptions.ExceptionStackUnderflow;

import java.math.BigInteger;

public class SRPN {

  /*
   * This is the SRPN class which attempts to emulate the legacy SRPN calculator.
   * 
   * ** General Principles of Operation **
   * 
   * The underlying stack contains data of Integer type, but to
   * handle saturation correctly:
   * - Numbers are popped from the stack as BigIntegers so that arithmetic which
   * would overflow on Integers can succeed.
   * - Numbers (user input and calculation results) are pushed onto the stack as
   * BigIntegers so that overflow can be handled (by saturation and safe casting
   * to Integers) in one place.
   * 
   * User input is converted to Command Objects by the CommandParser, (which uses
   * Regular expressions to extract individual command items and comments).
   * Command Objects all implement the ICommand interface, which means they have
   * an execute() method, and their actual implementation is transparent to the
   * SRPN class.
   */

  private Stack<Integer> stack;

  private PseudoRandomNumberGenerator prng;

  private CommandParser commandParser;

  private static final BigInteger MAX_VALUE = BigInteger.valueOf(Integer.MAX_VALUE);
  private static final BigInteger MIN_VALUE = BigInteger.valueOf(Integer.MIN_VALUE);

  private static final Integer MAX_STACK_SIZE = 23;

  public SRPN() {
    stack = new Stack<Integer>();
    commandParser = new CommandParser();
    prng = new PseudoRandomNumberGenerator();
  }

  /**
   * The entry point for the main program using the SRPN calculator.
   * Receives user input in String format, parses the input into actionable
   * commands and executes those commands on the SRPN stack.
   * 
   * @param s the user input.
   */
  public void processCommand(String s) {

    // Extract the commands contained within the input using the CommandParser.
    ArrayList<ICommand> commands = commandParser.ParseInput(s);

    // Iterate through a the list of extracted commands, and try to execute them.
    for (ICommand command : commands) {

      try {
        command.execute(this);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Returns the current size of the stack
   * @return int
   */
  public int getStackSize() {
    return stack.size();
  }

  /**
   * Casts a number of type BigInteger to Integer, using saturation to handle
   * overflow and pushes the Integer to the stack. If the stack has reached
   * its maximum capacity, an appropriate exception is thrown.
   * 
   * @param value
   * @throws ExceptionStackOverflow
   */
  public void pushToStack(BigInteger value) throws ExceptionStackOverflow {

    if (getStackSize() >= SRPN.MAX_STACK_SIZE) {
      throw new ExceptionStackOverflow();
    } else {
      if (value.compareTo(BigInteger.valueOf(0)) >= 0) // a positive number or 0
      {
        stack.push(value.min(SRPN.MAX_VALUE).intValue());
      } else {
        stack.push(value.max(SRPN.MIN_VALUE).intValue());
      }
    }
  }

  /**
   * Returns an array containing the two numbers on top of the stack as
   * BigIntegers.
   * 
   * @return BigInteger[] The top two numbers on the stack.
   * @throws ExceptionStackUnderflow
   */
  public BigInteger[] getOperands() throws ExceptionStackUnderflow {

    if (getStackSize() >= 2) {
      BigInteger[] operands = { BigInteger.valueOf(stack.pop()), BigInteger.valueOf(stack.pop()) };
      return operands;
    } else {
      throw new ExceptionStackUnderflow();
    }
  }

  /**
   * Pushes a random number to the stack from the pseudorandom number generator.
   * 
   * @throws ExceptionStackOverflow if stack is full.
   */
  public void addRandomNumberToStack() throws ExceptionStackOverflow {
    pushToStack(BigInteger.valueOf(prng.getRandomNumber()));
  }

  /**
   * Prints the number on the top of the stack, or throws an exception
   * with appropriate message if the stack is empty.
   * 
   * @throws ExceptionStackEmpty
   */
  void printStackHead() throws ExceptionStackEmpty {
    if (!stack.isEmpty()) {
      System.out.println(this.stack.peek());
    } else {
      throw new ExceptionStackEmpty();
    }
  }

  /**
   * Prints all numbers on the stack, each on a new line.
   * Throws an exception with appropriate message if the stack is empty.
   * 
   * @throws ExceptionPrintEmptyStack
   */
  void printStack() throws ExceptionPrintEmptyStack {

    if (this.stack.isEmpty()) {
      throw new ExceptionPrintEmptyStack();
    }

    for (int i : this.stack) {
      System.out.println(i);
    }
  }
}
