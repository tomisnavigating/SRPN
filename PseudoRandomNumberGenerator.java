import java.util.ArrayList;

public class PseudoRandomNumberGenerator {

    /*
     * This class is used to supply "random" numbers.
     * The legacy SRPN calculator's rng produces a stream of pseudorandom
     * numbers, which (at least in the replit environment) appears to
     * always be seeded with the same number, and hence the sequence is
     * the same each time the software is run. In order to replicate this
     * behaviour, this class simply contains that list of numbers.
     * 
     */

    private ArrayList<Integer> randomNumbers;
    private Integer pointer;

    public PseudoRandomNumberGenerator() {

        // Initialise an ArrayList, to which the sequence of pseudorandom numbers can be
        // added.
        randomNumbers = new ArrayList<Integer>();
        pointer = 0;

        // add the pseudorandom numbers 
        randomNumbers.add(1804289383);
        randomNumbers.add(846930886);
        randomNumbers.add(1681692777);
        randomNumbers.add(1714636915);
        randomNumbers.add(1957747793);
        randomNumbers.add(424238335);
        randomNumbers.add(719885386);
        randomNumbers.add(1649760492);
        randomNumbers.add(596516649);
        randomNumbers.add(1189641421);
        randomNumbers.add(1025202362);
        randomNumbers.add(1350490027);
        randomNumbers.add(783368690);
        randomNumbers.add(1102520059);
        randomNumbers.add(2044897763);
        randomNumbers.add(1967513926);
        randomNumbers.add(1365180540);
        randomNumbers.add(1540383426);
        randomNumbers.add(304089172);
        randomNumbers.add(1303455736);
        randomNumbers.add(35005211);
        randomNumbers.add(521595368);

    }

    /**
     * This function returns the next pseudorandom number in sequence.
     * 
     * @return the next Integer in the sequence
     */
    public Integer getRandomNumber() {

        Integer number = randomNumbers.get(pointer % randomNumbers.size());
        pointer++;
        return number;
    }
}