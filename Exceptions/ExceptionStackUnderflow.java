package Exceptions;
public class ExceptionStackUnderflow extends Exception {

    /*
    This is a simple custom exception which can 
    be used when the message "Stack underflow." should
    be printed
    */
    private static String message = "Stack underflow.";

    public ExceptionStackUnderflow()
    {
        super(message);
    }
}