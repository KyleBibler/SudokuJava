import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kyle on 4/30/2015.
 */
public class DancingLinks {
    private final HashMap<String, Double> times;
    public ArrayList<ArrayList<String>> solutionSets;
    public HashMap<Integer, String> solutionSet;
    public SparseMatrix matrix;
    public boolean stop;

    public DancingLinks(SparseMatrix sm, HashMap<String, Double> times) {
        this.solutionSets = new ArrayList<ArrayList<String>>();
        this.solutionSet = new HashMap<Integer, String>();
        this.matrix = sm;
        this.stop = false;
        this.times = times;
        times.put("cover", 0.0);
        times.put("uncover", 0.0);
    }

    public void cover(Head col) {
//        long startTime = System.currentTimeMillis();
        col.left.right = col.right;
        col.right.left = col.left;
        Node rowNode = col.down;
        Node rowNeighbor;
        while(rowNode != col) {
            rowNeighbor = rowNode.right;
            while(rowNeighbor != rowNode) {
                rowNeighbor.up.down = rowNeighbor.down;
                rowNeighbor.down.up = rowNeighbor.up;
                rowNeighbor.col.size--;
                rowNeighbor = rowNeighbor.right;
            }
            rowNode = rowNode.down;
        }
//        long endTime   = System.currentTimeMillis();
//        long totalTime = endTime - startTime;
//        double coverTime = times.get("cover");
//        times.put("cover", coverTime+totalTime);
    }

    public void uncover(Head col) {
//        long startTime = System.currentTimeMillis();
        Node rowNode = col.up;
        Node rowNeighbor;
        while(rowNode != col) {
            rowNeighbor = rowNode.left;
            while(rowNeighbor != rowNode) {
                rowNeighbor.up.down = rowNeighbor;
                rowNeighbor.down.up = rowNeighbor;
                rowNeighbor.col.size++;
                rowNeighbor = rowNeighbor.left;
            }
            rowNode = rowNode.up;
        }
        col.right.left = col;
        col.left.right = col;
//        long endTime   = System.currentTimeMillis();
//        long totalTime = endTime - startTime;
//        double coverTime = times.get("cover");
//        times.put("uncover", coverTime+totalTime);
    }

    public void search(int depth) {
        Head matrixHead = matrix.head;

        if(matrixHead.right == matrixHead) {
            ArrayList<String> mySolution = new ArrayList<String>();
            for(Integer key : solutionSet.keySet()) {
                mySolution.add(solutionSet.get(key));
            }
            this.solutionSets.add(mySolution);
            return;
        }

        Head col = null;
        int min = Integer.MAX_VALUE;
        for(Head j = (Head) matrixHead.right; j != matrixHead; j = (Head) j.right) {
            if(min > j.size) {
                col = j;
                min = j.size;
            }
        }


        Node rowNode = col.down;
        Node rowNeighbor;
        cover(col);
        while(rowNode != col) {
            rowNeighbor = rowNode.right;
            solutionSet.put(depth, rowNode.row);
            while(rowNeighbor != rowNode) {
                cover(rowNeighbor.col);
                rowNeighbor = rowNeighbor.right;
            }
            search(depth+1);
            rowNeighbor = rowNode.left;
            while(rowNeighbor != rowNode) {
                uncover(rowNeighbor.col);
                rowNeighbor = rowNeighbor.left;
            }
            rowNode = rowNode.down;
        }

        uncover(col);
    }
}
