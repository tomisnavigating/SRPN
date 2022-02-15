/**
 * CommandUnrecognised
 */
public class CommandUnrecognised implements ICommand {

    private String input;

    public CommandUnrecognised(String s) {
        this.input = s;
    }

    @Override
    public void execute(SRPN srpn) {
        for (char c: input.toCharArray()) {
            System.out.println(String.format("Unrecognised operator or operand \"%c\".", c));
        }
    }
}