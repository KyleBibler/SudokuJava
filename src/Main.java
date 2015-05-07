/**
 * Created by Kyle on 5/7/2015.
 */
public class Main {
    public static void main(String[] args) {
        MatrixGenerator mg = new MatrixGenerator();
        Tuple<String,String> finalPuzzle = mg.createNew(8, 1);
        System.out.println(finalPuzzle.y);
    }
}
