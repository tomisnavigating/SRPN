import Exceptions.ExceptionNegativePower;
import Exceptions.ExceptionPrintEmptyStack;
import Exceptions.ExceptionStackEmpty;
import Exceptions.ExceptionStackOverflow;
import Exceptions.ExceptionStackUnderflow;
import Exceptions.ExceptionZeroDivision;

public interface ICommand {

    /*
     * This Interface is used as a way to make the handling of Commmand objects
     * generic.
     * Classes which implement it must provide an execute() method which
     * manupiulates the SRPN
     * in a way which emulates the legacy calulator
     */

    void execute(SRPN srpn) throws ExceptionZeroDivision, ExceptionNegativePower, ExceptionStackUnderflow,
            ExceptionStackEmpty, ExceptionStackOverflow, ExceptionPrintEmptyStack;

}
