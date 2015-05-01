import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Kyle on 4/30/2015.
 */
public class Main {

    public static void main(String[] args) {
        File f = new File("sudoku3.txt");
        MatrixGenerator mg = new MatrixGenerator(f);
        SparseMatrix sm = new SparseMatrix();
        sm.createLinks(mg.matrix);
        DancingLinks dl = new DancingLinks(sm);
        dl.search(0);

        for (ArrayList<String> set : dl.solutionSets) {
            System.out.println(mg.buildFinished(set));
        }




    }
}
