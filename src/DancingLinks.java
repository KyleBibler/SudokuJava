import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kyle on 4/30/2015.
 */
public class DancingLinks {
    public ArrayList<ArrayList<String>> solutionSets;
    public String[] solutionSet;
    public boolean stop;
    public Head head;

    public DancingLinks(Head head, int dim) {
        this.solutionSets = new ArrayList<ArrayList<String>>();
        solutionSet = new String[dim*dim*dim*dim];
        for(int i = 0; i < solutionSet.length; i++) {
            solutionSet[i] = "";
        }
        this.head = head;
        this.stop = false;
    }

    public void search(int depth) {
        if(stop) {
            return;
        }

        if(head.right == head) {
            ArrayList<String> mySolution = new ArrayList<String>();
            for(String s : solutionSet) {
                if(!s.equals(""))
                mySolution.add(s);
            }
            this.solutionSets.add(mySolution);
            this.stop = true;
            return;
        }

        Head col = null;
        int min = Integer.MAX_VALUE;
        for(Head j = (Head) head.right; j != head; j = (Head) j.right) {
            if(min > j.size) {
                col = j;
                min = j.size;
            }
        }


        col.cover();
        Node rn;
        for(Node rowNode = col.down; rowNode != col; rowNode = rowNode.down) {
            if(stop) {
                break;
            }
            solutionSet[depth] = rowNode.row;
            for(rn = rowNode.right; rn != rowNode; rn = rn.right) {
                rn.col.cover();
            }
            search(depth+1);

            for(rn = rowNode.left; rn != rowNode; rn = rn.left) {
                rn.col.uncover();
            }
        }
        col.uncover();
    }
}
