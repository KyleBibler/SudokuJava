import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Kyle on 4/30/2015.
 */
public class Main {

    public static void main(String[] args) {
        File f = new File("sudoku2.txt");
        MatrixGenerator mg = new MatrixGenerator(f);
        SparseMatrix sm = new SparseMatrix();
        sm.createLinks(mg.matrix);
        HashMap<String, Double> times = new HashMap<String, Double>();
        DancingLinks dl = new DancingLinks(sm, times);
        long startTime = System.currentTimeMillis();
        dl.search(0);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime/1000);
        System.out.println((times.get("cover")/1000));
        System.out.println((times.get("uncover")/1000));


        for (ArrayList<String> set : dl.solutionSets) {
            System.out.println(mg.buildFinished(set));
        }




    }
}
