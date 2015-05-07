import java.util.ArrayList;

/**
 * Created by Kyle on 4/30/2015.
 */
public class DancingLinks {
    public int SOLUTION_SIZE;
    public ArrayList<ArrayList<String>> solutionSets;
    public String[] solutionSet;
    public int stopCount;
    public int MAX_COUNT;
    public Head head;
    public int DIM;
    public int DIM4;
    public boolean generating;
    public boolean ableToSolve;

    public DancingLinks(Head head, int solutionSize, boolean generating, int max_sols) {
        this.solutionSets = new ArrayList<ArrayList<String>>();
        this.ableToSolve = false;
        this.SOLUTION_SIZE = solutionSize;
        this.generating = generating;
        if(generating) {
            MAX_COUNT = 1;
        } else {
            MAX_COUNT = max_sols;
        }
        solutionSet = new String[SOLUTION_SIZE];
        for(int i = 0; i < solutionSet.length; i++) {
            solutionSet[i] = "";
        }
        this.head = head;
        this.stopCount = 0;
    }

    public void search(int depth) {
//        if(depth >= SOLUTION_SIZE && !generating) {
//            System.out.println("PUZZLE IS INVALID");
//            stopCount = MAX_COUNT;
//        }
        if(stopCount == MAX_COUNT) {
            return;
        }

        if(head.right == head) {
            ArrayList<String> mySolution = new ArrayList<String>();
            for (int i = 0; i < depth; i++) {
                String s = solutionSet[i];
                if(!s.equals(""))
                    mySolution.add(s);
            }
            this.ableToSolve = true;
            this.solutionSets.add(mySolution);
            this.stopCount++;
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
            if(stopCount == MAX_COUNT) {
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
