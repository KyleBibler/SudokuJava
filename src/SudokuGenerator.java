import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kyle on 5/4/2015.
 */
public class SudokuGenerator {

    public int[][] generateSeedData(int n) {
        int n2 = n*n;
        int[][] result = new int[n2][n2];
        ArrayList<Integer> avail = new ArrayList<Integer>();
        for(int i = 1; i <= n2; i++) {
            avail.add(i);
        }
        Random rand = new Random();
        for(int i = 0; i < result.length; i++) {
            int index = rand.nextInt(avail.size());
            result[i][i] = avail.get(index);
            avail.remove(index);
        }
        return result;
    }

    public int[][] createFinalPuzzle(int[][] fp, int difficulty) {
        if(difficulty > 2 || difficulty < 0) {
            difficulty = 0;
        }
        Random rand = new Random();
        ArrayList<Tuple<Integer, Integer>> indeces = new ArrayList<Tuple<Integer, Integer>>();
        int n = fp.length;
        int x, y;
        //int max = n*n*3/5 + rand.nextInt(n/5);
        int max = n*n/(4-difficulty)+n*difficulty;
        for(int i = 0; i < max; i++) {
            Tuple<Integer, Integer> t;
            do {
                x = rand.nextInt(n);
                y = rand.nextInt(n);
                t = new Tuple<Integer, Integer>(x, y);
            }
            while(containsTuple(indeces, t));
            indeces.add(t);
        }
        for(Tuple<Integer, Integer> t : indeces) {
            x = t.x;
            y = t.y;
            fp[x][y] = 0;
        }
        return fp;
    }



    public boolean containsTuple(ArrayList<Tuple<Integer, Integer>> tuples, Tuple<Integer, Integer> t) {
        for(Tuple<Integer, Integer> tup : tuples) {
            if(tup.x.equals(t.x) && tup.y.equals(t.y)) {
                return true;
            }
        }
        return false;
    }
}
