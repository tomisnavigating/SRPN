package Exceptions;
public class ExceptionZeroDivision extends Exception {
    
    /*
    This is a simple custom exception which can 
    be used when the message "Divide by zero." should
    be printed
    */
    private static String message = "Divide by 0.";

    public ExceptionZeroDivision()
    {
        super(message);
    }
}
