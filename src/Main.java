import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kyle on 4/30/2015.
 */
public class Main {

    public static void main(String[] args) {
        File f = new File("easySudoku.txt");
        MatrixGenerator mg = new MatrixGenerator(f);
        DancingLinks dl = new DancingLinks(mg.head, mg.dim);
        long startTime = System.currentTimeMillis();
        dl.search(0);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime/1000);

        for (ArrayList<String> set : dl.solutionSets) {
            System.out.println(mg.buildFinished(set));
        }




    }
}
