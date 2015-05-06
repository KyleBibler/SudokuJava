import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kyle on 4/30/2015.
 */
public class Main {

    public static void main(String[] args) {
        File f = new File("sudoku.txt");

        MatrixGenerator mg = new MatrixGenerator();
        //mg.createFromFile(f);
        long startTime = System.currentTimeMillis();
        mg.createNew(6);
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

//        if (!generating){
//            for (ArrayList<String> set : dl.solutionSets) {
//                System.out.println(mg.buildFinished(set));
//            }
//        } else {
//            new SudokuGenerator().createFinalPuzzle(dl.solutionSets.get(0));
//        }

//        Generator g = new Generator(5);
//        System.out.println(g.toString());




    }
}
