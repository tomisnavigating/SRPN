import java.util.regex.*;
import java.util.ArrayList;

public class CommandParser {

    /*
     * This class uses a regular expression pattern with named capture groups
     * to extract discrete input parameters from the user input String.
     * The patterns won't change, so for the sake of only compiling it once,
     * it's defined here as a static member variable for efficient repeated use
     * later.
    */
    private static Pattern commandPattern = Pattern
            .compile(
                "(?<numeric>-?[0-9]+)" +    // Numeric input (with or without preceeding "-") (no decimals)
                "|(?<add>\\+)" +            // +
                "|(?<subtract>-)" +         // -
                "|(?<multiply>\\*)" +       // *      
                "|(?<divide>\\/)" +         // /
                "|(?<modulus>%)" +          // %
                "|(?<power>\\^)" +          // ^
                "|(?<equals>=)" +           // =
                "|(?<random>r)" +           // r
                "|(?<printStack>d)" +       // d
                "|(?<terminatedComment>#\\s[^#]*#)" +     // Comments without termination
                "|(?<unterminatedComment>#\\s[^#]*)" +    // Comments without termination
                "|(?<unrecognisedContent>[^\\+\\-\\*\\/%\\^=rd\\s])");     // Unrecognised content

    /*
     * Comments can span multiple lines of input, so if an unterminated
     * comment is detected on a line, all other input is ignored until 
     * the end of the comment is found.
     * A separate regex pattern is used for this.
     */
    private static Pattern commentTerminationPattern = Pattern.compile("[^#]*#");

    private Boolean currentlyWithinMultilineComment = false;

    public ArrayList<ICommand> ParseInput(String s) {

        /*
         * the first thing to check is whether we're within the middle of a multi-line
         * comment, as we need to find the end of it before we can begin parsing other
         * commands
         */

        if (currentlyWithinMultilineComment) {
            Matcher commentCompletionMatcher = commentTerminationPattern.matcher(s);
            if (commentCompletionMatcher.find()) {

                // If we find an end to the comment, remove the comment content and proceed with
                // processing the rest of the line
                s = commentCompletionMatcher.replaceFirst("");

                currentlyWithinMultilineComment = false;

            } else {
                // the comment hasn't ended, there's nothing else we can parse in this input
                // line: return an empty list
                return new ArrayList<ICommand>();
            }
        }

        // Make a ArrayList to contain the commands found in this input. Anything
        // retuned in this list implements the ICommand interface, and can therefore be
        // has an execute() method.
        ArrayList<ICommand> commandList = new ArrayList<ICommand>();

        // create a Matcher object which will search for matches in the input.
        Matcher commandMatcher = commandPattern.matcher(s);

        // Keep dealing with input while the regex is still matching the pattern
        while (commandMatcher.find()) {

            String matchedCommand = commandMatcher.group(); // this is the matched String.

            /*
             * The following series of if/else statements determines
             * which named group the match appears in.
             * This indicates which subclass of Command is required.
             * The an isntance of the appropriate Command subclass is then
             * instantiated and added to the list of commands,
             * with the match String as an intialisation parameter if required.
             */

            if (commandMatcher.group("numeric") != null) {
                commandList.add(new CommandNumeric(matchedCommand));
            } else if (commandMatcher.group("add") != null) {
                commandList.add(new CommandAdd());
            } else if (commandMatcher.group("subtract") != null) {
                commandList.add(new CommandSubtract());
            } else if (commandMatcher.group("multiply") != null) {
                commandList.add(new CommandMultiply());
            } else if (commandMatcher.group("divide") != null) {
                commandList.add(new CommandDivide());
            } else if (commandMatcher.group("modulus") != null) {
                commandList.add(new CommandModulus());
            } else if (commandMatcher.group("power") != null) {
                commandList.add(new CommandPower());
            } else if (commandMatcher.group("equals") != null) {
                commandList.add(new CommandEquals());
            } else if (commandMatcher.group("random") != null) {
                commandList.add(new CommandRandom());
            } else if (commandMatcher.group("printStack") != null) {
                commandList.add(new CommandPrintStack());
            } else if (commandMatcher.group("terminatedComment") != null) {
                continue;
            } else if (commandMatcher.group("unterminatedComment") != null) {
                currentlyWithinMultilineComment = true;
                break;
            } else if (commandMatcher.group("unrecognisedContent") != null) {
                commandList.add(new CommandUnrecognised(matchedCommand));
            }
        }
        return commandList;
    }

}
