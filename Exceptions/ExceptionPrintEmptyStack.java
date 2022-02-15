package Exceptions;


public class ExceptionPrintEmptyStack extends Exception {

    /*
    This is a simple custom exception which can 
    be used when the the "d" command is invoked on an empty stack,
    and "-2147483648" should be printed.
    */
    private static String message = String.format("%d", Integer.MIN_VALUE);

    public ExceptionPrintEmptyStack()
    {
        super(message);
    }
}
