package Exceptions;


public class ExceptionStackEmpty extends Exception{

    /*
    This is a simple custom exception which can 
    be used when the message "Stack empty." should
    be printed
    */
    private static String message = "Stack empty.";

    public ExceptionStackEmpty()
    {
        super(message);
    }
}