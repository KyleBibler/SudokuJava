import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kyle on 4/30/2015.
 */
public class Main {

    public static void main(String[] args) {
        File f = new File("sudokuZero.txt");

        MatrixGenerator mg = new MatrixGenerator();
        //mg.createFromFile(f);
        //SamuraiGenerator mg = new SamuraiGenerator();
        long startTime = System.currentTimeMillis();
        mg.createFromFile(f);
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");


    }
}
