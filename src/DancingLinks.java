import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kyle on 4/30/2015.
 */
public class DancingLinks {
    public ArrayList<ArrayList<String>> solutionSets;
    public HashMap<Integer, String> solutionSet;
    public SparseMatrix matrix;

    public DancingLinks(SparseMatrix sm) {
        this.solutionSets = new ArrayList<ArrayList<String>>();
        this.solutionSet = new HashMap<Integer, String>();
        this.matrix = sm;
    }

    public void cover(Head col) {
        col.detach();
        Node rowNode = col.down;
        Node rowNeighbor;
        while(rowNode != col) {
            rowNeighbor = rowNode.right;
            while(rowNeighbor != rowNode) {
                rowNeighbor.detach();
                rowNeighbor = rowNeighbor.right;
            }
            rowNode = rowNode.down;
        }
    }

    public void uncover(Head col) {
        Node rowNode = col.up;
        Node rowNeighbor;
        while(rowNode != col) {
            rowNeighbor = rowNode.left;
            while(rowNeighbor != rowNode) {
                rowNeighbor.attach();
                rowNeighbor = rowNeighbor.left;
            }
            rowNode = rowNode.up;
        }
        col.attach();
    }

    public void search(int depth) {
        Node matrixHead = matrix.head;
        if(matrixHead.right == matrixHead) {
            ArrayList<String> mySolution = new ArrayList<String>();
            for(Integer key : solutionSet.keySet()) {
                mySolution.add(solutionSet.get(key));
            }
            this.solutionSets.add(mySolution);
            return;
        }
        Head col = this.matrix.chooseCol();
        Node rowNode = col.down;
        RowNode rowNeighbor;
        cover(col);
        while(rowNode != col) {
            rowNeighbor = (RowNode) rowNode.right;

            solutionSet.put(depth, ((RowNode) rowNode).row);
            while(rowNeighbor != rowNode) {
                cover(rowNeighbor.col);
                rowNeighbor = (RowNode) rowNeighbor.right;
            }
            search(depth+1);
            rowNeighbor = (RowNode) rowNode.left;
            while(rowNeighbor != rowNode) {
                uncover(rowNeighbor.col);
                rowNeighbor = (RowNode) rowNeighbor.left;
            }
            rowNode = rowNode.down;
        }
        uncover(col);
    }
}
