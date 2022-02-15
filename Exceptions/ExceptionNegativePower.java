package Exceptions;
public class ExceptionNegativePower extends Exception {

    /*
    This is a simple custom exception which can 
    be used when the message "Negative power." should
    be printed
    */
    private static String message = "Negative power.";

    public ExceptionNegativePower()
    {
        super(message);
    }
}