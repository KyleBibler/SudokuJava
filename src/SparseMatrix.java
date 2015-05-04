import java.util.ArrayList;
import java.util.HashMap;

public class SparseMatrix {

    public Head head;
    public SparseMatrix() {
        this.head = new Head(null);
    }

    public void createLinks(HashMap<String, ArrayList<String>> matrix) {
        Node headItr = this.head;
        Node newHead;
        int rowLength = 0;
        for(String s : matrix.keySet()) {
            rowLength = matrix.get(s).size();
            break;
        }

        for(int i = 0; i < rowLength; i++) {
            newHead = new Head(null);
            headItr.right = newHead;
            newHead.left = headItr;
            headItr = newHead;
        }
        headItr.right = this.head;
        this.head.left = headItr;

        headItr = this.head.right;

        Node newNode;
        ArrayList<ArrayList<Node>> colNodes = new ArrayList<ArrayList<Node>>();
        Node firstRowNode = null;
        Node nodeItr = null;
        int nodesInRow = 0;
        ArrayList<String> currentRow;
        for(String key : matrix.keySet()) {
            currentRow = matrix.get(key);
            for(int j = 0; j < currentRow.size(); j++) {
                if(!currentRow.get(j).equals("")) {
                    newNode = new Node((Head) headItr, key);
                    if(j >= colNodes.size()) {
                        for(int n = colNodes.size(); n <= j; n++) {
                            colNodes.add(new ArrayList<Node>());
                        }
                    }
                    if(nodesInRow == 0) {
                        firstRowNode = newNode;
                    } else {
                        newNode.left = nodeItr;
                        nodeItr.right = newNode;
                    }
                    nodeItr = newNode;
                    colNodes.get(j).add(newNode);
                    nodesInRow++;
                }
                headItr = headItr.right;
            }
            if(nodesInRow > 0) {
                nodeItr.right = firstRowNode;
                firstRowNode.left = nodeItr;
            }
            nodesInRow = 0;
            headItr = this.head.right;
        }

        Head colHead = this.head;
        Node nextNode;
        ArrayList<Node> current;
        for(int i = 0; i < colNodes.size(); i++) {
            colHead = (Head) colHead.right;
            nodeItr = colHead;
            current = colNodes.get(i);
            if(current.size() != 0) {
                for(int j = 0; j < current.size(); j++) {
                    nextNode = current.get(j);
                    nextNode.up = nodeItr;
                    nodeItr.down = nextNode;
                    nodeItr = nextNode;
                    colHead.size++;
                }
                colHead.up = nodeItr;
                nodeItr.down = colHead;
            } else {
                colHead.detach();
            }
        }

        colHead = (Head) this.head.right;
        int i = 1;
        while(colHead != this.head) {
            if(colHead.size == 0) {
                colHead.detach();
            } else {
                colHead.col = i;
                i++;
            }
            colHead = (Head) colHead.right;
        }

    }
}

