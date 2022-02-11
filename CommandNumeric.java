public class CommandNumeric extends Command {
    
    private String input;

    public CommandNumeric(String s) {
        input = s;
    }

    @Override
    public void execute(SRPN srpn) {
        System.out.println(input);
    }

}
