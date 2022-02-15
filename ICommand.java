import Exceptions.ExceptionNegativePower;
import Exceptions.ExceptionPrintEmptyStack;
import Exceptions.ExceptionStackEmpty;
import Exceptions.ExceptionStackOverflow;
import Exceptions.ExceptionStackUnderflow;
import Exceptions.ExceptionZeroDivision;

public interface ICommand {
    void execute(SRPN srpn) throws ExceptionZeroDivision, ExceptionNegativePower, ExceptionStackUnderflow,
            ExceptionStackEmpty, ExceptionStackOverflow, ExceptionPrintEmptyStack;
}
