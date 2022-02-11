public class ExceptionStackUnderflow extends Exception {

    /*
    This is a simple custom exception which can 
    be used when the message "Stack overflow." should
    be printed
    */
    private static String message = "Stack underflow.";

    public ExceptionStackUnderflow()
    {
        super(message);
        System.out.println(message);
    }
}