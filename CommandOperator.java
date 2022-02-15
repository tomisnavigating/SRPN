
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;

import Exceptions.ExceptionNegativePower;
import Exceptions.ExceptionStackUnderflow;
import Exceptions.ExceptionZeroDivision;

public class CommandOperator implements ICommand {

    // This is the super class for operator commands (+,-,*,/,%,^) entered by the user.
    // The SPRN class doesn't need to know how different concrete commands are
    // implemented,
    // it just needs to give the command access to its stack and the command can
    // execute itself.
    protected static Method operator;
    private Boolean isPower;

    public CommandOperator(OperatorType operatorType) {

        /*
         * Check whether this is a "pow" operator.
         * Unfortunately unlike the other possible operators, BigInteger.pow() doesn't
         * accept another BigInteger as a parameter, because the potential results
         * would be too large, so we need to recognise and deal with this special
         * case.
         */
        isPower = operatorType.name == "pow";
        Class parameterType;

        if (isPower) {
            parameterType = int.class;
        } else {
            parameterType = BigInteger.class;
        }

        try {
            // Use reflection to get a reference the appropriate method on BigInteger by
            // name so that it can be invoked later when the command is executed
            operator = BigInteger.class.getMethod(operatorType.name, parameterType);
        } catch (Exception e) {
            // This should work: Only valid options are passed in, because the constructor
            // takes an enum.
            System.out.println(e);
        }

    }

    public void execute(SRPN srpn) throws ExceptionZeroDivision, ExceptionNegativePower, ExceptionStackUnderflow {

        BigInteger[] operands = srpn.getOperands();
        BigInteger a = operands[1];
        BigInteger b = operands[0];

        try {
            BigInteger result;
            if (isPower) {
                result = (BigInteger) operator.invoke(a, b.intValue());
            } else {
                result = (BigInteger) operator.invoke(a, b);
            }
            srpn.pushToStack(result);
        } catch (InvocationTargetException e) {
            // The invokation failed, so put the operands back on the stack
            srpn.pushToStack(a);
            srpn.pushToStack(b);

            if (isPower) {
                throw new ExceptionNegativePower();
            } else {
                throw new ExceptionZeroDivision();
            }

        } catch (IllegalAccessException e) {
            // This shouldn't occur as it means the method doesn't exist or has been called
            // incorrectly. Catch it here and print the exception for debugging.
            System.out.println(e);
        }

    }
}
