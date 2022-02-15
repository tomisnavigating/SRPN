public class CommandRandom extends Command {
    
    @Override
    public void execute(SRPN srpn) {
        srpn.addRandomNumberToStack();
    }

}
