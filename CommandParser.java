import java.util.regex.*;
import java.util.ArrayList;

public class CommandParser {

    // these static constants contain all legal input characters (apart from
    // whitespace nd digits). We can use them to determine whether input is legal or
    // not
    private static final String legalMathsOperators = "+-*/%^";
    private static final String legalActionKeys = "dr=";

    /*
     * This class uses regular expression patterns which to extract discrete
     * input parameters from the user input String.
     * The patters wont change, so for the sake of only compiling it once,
     * it's placed here as a static member variable, for efficient repeated use
     * later.
     * It matches different types of commands as follows:
     * Group 1: Numerical values
     * Group 2: +
     * Group 3: -
     * Group 4: *
     * Group 5: /
     * Group 6: %
     * Group 7: ^
     * Group 8: =
     * Group 9: r (put a random number on the stack)
     * Group 10: d (print the stack)
     * Group 11: Whole comments within a line of input, eg: # This is a comment with
     * a concluding hash #
     * Group 12: Unfinished comments
     */

    private static Pattern commandPattern = Pattern
            .compile("(-?[0-9]+)|(\\+)|(-)|(\\*)|(\\/)|(%)|(\\^)|(=)|(r)|(d)|(#\\s[^#]*#)|(#\\s[^#]*)");

    /*
     * Comments can span multiple lines of input, so if we detect an unfinished
     * comment on a line we need to
     * ignore all other input until we find the end of the comment. We'll use a
     * separate regex pattern for this.
     */
    private static Pattern commentCompletionPattern = Pattern.compile("[^#]*#");

    private Boolean currentlyWithinMultilineComment = false;

    public ArrayList<ICommand> ParseInput(String s) {

        /*
         * the first thing to check is whether we're within the middle of a multi-line
         * comment, as we need to find the end of it before we can begin parsing other
         * commands
         */

        if (currentlyWithinMultilineComment) {
            Matcher commentCompletionMatcher = commentCompletionPattern.matcher(s);
            if (commentCompletionMatcher.find()) {

                // If we find an end to the comment, remove the comment content and proceed with
                // processing the rest of the line
                s = commentCompletionMatcher.replaceFirst("");

                currentlyWithinMultilineComment = false;

            } else {
                // the comment hasn't ended, there's nothing else we can parse in this input
                // line
                // so return an empty list
                return new ArrayList<ICommand>();
            }
        }

        // The legacy SRPN calculator warns the user of unrecognised input content. 
        flagIllegalContent(s);

        // Make a ArrayList to contain the commands found in this input. Anything
        // retuned in this list implements the ICommand interface, and can therefore be
        // executed
        ArrayList<ICommand> commandList = new ArrayList<ICommand>();

        // create a Matcher object which will search for matches in the input.
        Matcher commandMatcher = commandPattern.matcher(s);

        // Keep dealing with input while the regex is still matching the patterm
        // Inspired by the example found here:
        // https://www.tutorialspoint.com/getting-the-list-of-all-the-matches-java-regular-expressions

        while (commandMatcher.find()) {

            String matchedCommand = commandMatcher.group();
            // Find out what command this is, create a an appropriate object
            // implementing ICommand and add it to the list.
            // Also handle comments.

            if (commandMatcher.group(1) != null) {
                commandList.add(new CommandNumeric(matchedCommand));
            } else if (commandMatcher.group(2) != null) {
                commandList.add(new CommandOperator(OperatorType.ADD));
            } else if (commandMatcher.group(3) != null) {
                commandList.add(new CommandOperator(OperatorType.SUBTRACT));
            } else if (commandMatcher.group(4) != null) {
                commandList.add(new CommandOperator(OperatorType.MULTIPLY));
            } else if (commandMatcher.group(5) != null) {
                commandList.add(new CommandOperator(OperatorType.DIVIDE));
            } else if (commandMatcher.group(6) != null) {
                commandList.add(new CommandOperator(OperatorType.MODULUS));
            } else if (commandMatcher.group(7) != null) {
                commandList.add(new CommandOperator(OperatorType.POWER));
            } else if (commandMatcher.group(8) != null) {
                commandList.add(new CommandEquals());
            } else if (commandMatcher.group(9) != null) {
                commandList.add(new CommandRandom());
            } else if (commandMatcher.group(10) != null) {
                commandList.add(new CommandPrintStack());
            } else if (commandMatcher.group(11) != null) {
                // this is a complete comment - we can ignore it.
                // System.out.println("Comment!!!");
                continue;
            } else if (commandMatcher.group(12) != null) {
                // this is an incomplete comment - we can't accept any more commands from this
                // line.
                // System.out.println("unfinished Comment!!!");
                currentlyWithinMultilineComment = true;
                break;
            }
        }
        return commandList;
    }

    private static void flagIllegalContent(String s) {

        // we shouldn't flag illegal content within a comment, so we will remove those
        // before making the check:
        // first the complete comments:
        s = s.replaceAll("(#\\s)[^#]*#", "");
        // and then the incomplete comments:
        s = s.replaceAll("(#\\s)[^#]*", "");

        for (char c : s.toCharArray()) {
            if (!CommandParser.isLegalCharacter(c)) {
                System.out.println(String.format("Unrecognised operator or operand \"%c\".", c));
            }
        }

    }

    private static Boolean isLegalCharacter(char c) {
        return (Character.isDigit(c) ||
                Character.isWhitespace(c) ||
                CommandParser.legalMathsOperators.indexOf(c) != -1 ||
                CommandParser.legalActionKeys.indexOf(c) != -1);
    }
}
