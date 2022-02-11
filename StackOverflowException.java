public class StackOverflowException extends Exception {

    /*
    This is a simple custom exception which can 
    be used when the message "Stack overflow." should
    be printed
    */

    public StackOverflowException()
    {
        super ("Stack overflow.");
    }
}
