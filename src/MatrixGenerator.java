import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Kyle on 4/30/2015.
 */
public class MatrixGenerator {

    public HashMap<String, ArrayList<String>> matrix;
    public int dim;
    public ArrayList<String> originalCells;
    public Head head;

    public static int PUZZLE_SIDE;
    public static int SQUARE_SIDE;
    public static int PUZZLE_SIZE;
    public static int COLUMN_SIZE;

    public MatrixGenerator(File f) {
        head = new Head(null, "HEAD");
        this.dim = 1;
        originalCells = new ArrayList<String>();

        int[][] seedData = parseInput(f);
        createMatrix(seedData);

//        int nSq = (int) Math.pow(dim, 2);
//        int nFr = (int) Math.pow(dim, 4);
//        matrix = new HashMap<String, ArrayList<String>>();
//
//        for(int r = 1; r <= nSq; r++) {
//            for(int c = 1; c <= nSq; c++) {
//                for(int e = 1; e <= nSq; e++) {
//                    String[] row = new String[4*nFr];
//                    Arrays.fill(row, "");
//                    int index;
//                    int box;
//
//                    //RC constraint
//                    index = nSq*(r-1)+c-1;
//                    row[index] = "R" + r + "C" + c;
//
//                    //RN constraint
//                    index = nFr + nSq*(r-1)+e-1;
//                    row[index] = "R" + r + "#" + e;
//
//                    //CN constraint
//                    index = 2*nFr + nSq*(c-1)+e-1;
//                    row[index] = "C" + c + "#" + e;
//
//                    //BN constraint
//                    box = (int) (dim*Math.floor((double)(r-1)/dim) + Math.ceil((double)c/dim));
//                    index = 3*nFr + (int)(box-1)*nSq + (e-1);
//                    row[index] = "B" + box + "#" + e;
//
//                    //add to matrix
//                    matrix.put("R" + r + "C" + c + "#" + e, new ArrayList<String>(Arrays.asList(row)));
//
//                }
//            }
//        }
//        reduceMatrix(seedData);
//        buildLinks();
    }

    public int[][] parseInput(File f) {
        try {
            Scanner fs1 = new Scanner(f);
            String row;


            row = fs1.nextLine().trim();
            String[] boxes = row.split("   ");
            dim = boxes.length;
            PUZZLE_SIDE = dim*dim;
            SQUARE_SIDE = dim;
            PUZZLE_SIZE = PUZZLE_SIDE*PUZZLE_SIDE;
            COLUMN_SIZE = 4*PUZZLE_SIZE;

            int[][] result = new int[PUZZLE_SIDE][PUZZLE_SIDE];

            Scanner fs = new Scanner(f);
            int rowNum = 0;
            while(fs.hasNext()) {
                row = fs.nextLine();
                if(row.isEmpty()) {
                    continue;
                }
                boxes = row.split("   ");
                int col = 0;
                for (int j = 0; j < boxes.length; j++) {
                    String[] numbers = boxes[j].trim().split(" ");
                    for (int n = 0; n < numbers.length; n++) {
                        if (!numbers[n].equals("0")) {
                            result[rowNum][col] = Integer.parseInt(numbers[n]);
                        }
                        col++;
                    }
                }
                rowNum++;
            }
            for(int i = 0; i < result.length; i++) {
                for(int j = 0; j < result.length; j++) {
                    if(result[i][j] != 0) {
                        originalCells.add("R" + (i+1) + "C" + (j+1) + "#" + result[i][j]);
                    }
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new int[1][1];
    }

    public void buildLinks() {
        Node headItr = head;
        Node newHead;
        int rowLength = 0;
        for(String s : matrix.keySet()) {
            rowLength = matrix.get(s).size();
            break;
        }

        for(int i = 0; i < rowLength; i++) {
            newHead = new Head(null, "C"+(i+1));
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

    public String buildFinished(ArrayList<String> finishedCells) {
        finishedCells.addAll(originalCells);
        Collections.sort(finishedCells);
        String result = "";
        int row;
        int column;
        String number;
        for(String s : finishedCells) {
            row = Integer.parseInt(s.substring(1, s.indexOf('C')).trim());
            column = Integer.parseInt(s.substring(s.indexOf('C')+1, s.indexOf('#')).trim());
            number = s.substring(s.indexOf('#')+1).trim();
            result += number;
            if(column == dim*dim) {
                result += "\n";
                if(row % dim == 0) {
                    result += "\n";
                }
            }
            else if(column % dim == 0) {
                result += "   ";
            } else {
                result += " ";
            }

        }


        return result;
    }

    public void reduceMatrix(ArrayList<String> seedData) {
        ArrayList<String> constraintTags = new ArrayList<String>();
        ArrayList<String> tagsToRemove = new ArrayList<String>();

        String row;
        String column;
        String number;
        String deleteTag;
        int box;

        for(String tag : seedData) {
            row = tag.substring(1, tag.indexOf('C')).trim();
            column = tag.substring(tag.indexOf('C')+1, tag.indexOf('#')).trim();
            number = tag.substring(tag.indexOf('#')+1).trim();
            box = (int) (dim*Math.floor((Integer.parseInt(row)-1)/(double)dim) + Math.ceil(Integer.parseInt(column) / (double) dim));
            deleteTag = tag.substring(0, tag.indexOf('#')).trim();

            for(int i = 1; i <= dim*dim; i++) {
                tagsToRemove.add((deleteTag + "#" + i).trim());
            }

            constraintTags.add(("R" + row + "C" + column).trim());
            constraintTags.add(("R" + row + "#" + number).trim());
            constraintTags.add(("C" + column + "#" + number).trim());
            constraintTags.add(("B" + box + "#" + number).trim());
        }

        for(String tag : tagsToRemove) {
            if(matrix.containsKey(tag))
                matrix.remove(tag);
        }

        ArrayList<String> rowArray;
        for(String key : matrix.keySet()) {
            rowArray = matrix.get(key);
            for(int i = 0; i < rowArray.size(); i++) {
                for (String tag : constraintTags) {
                    if(tag.trim().equals(rowArray.get(i).trim())) {
                        rowArray.set(i, "");
                    }
                }
            }
        }
    }

    public Node[] createMatrix(int[][] seedData) {
        Node[] o = new Node[PUZZLE_SIZE];
        head = new Head(null, "HEAD");
        Head[] m = new Head[COLUMN_SIZE];
        int index = 0;

        // Create the row of columns.

        for (int i = 0; i < COLUMN_SIZE; i++)
            m[i] = new Head(head, "C"+(i+1));

        // List of rows that are part of the solution.

        Node[] l = new Node[PUZZLE_SIZE];
        int i = 0;

        // For each row, column and possible digit.

        for (int r = 0; r < PUZZLE_SIDE; r++)
            for (int c = 0; c < PUZZLE_SIDE; c++)
                for (int d = 0; d < PUZZLE_SIDE; d++)
                {
                    // Calculate row number.

                    String k = "R" + (r+1) + "C" + (c+1) + "#" + (d+1);
                    //int k = 1 + (r * PUZZLE_SIZE) + (c * PUZZLE_SIDE) + d;

                    // Create the row of nodes.

                    Node n = new Node(m[(r * PUZZLE_SIDE) + c], k);
                    n.add(new Node(m[(PUZZLE_SIZE * 1) +
                            (r * PUZZLE_SIDE) + d], k));
                    n.add(new Node(m[(PUZZLE_SIZE * 2) +
                            (c * PUZZLE_SIDE) + d], k));
                    n.add(new Node(m[(PUZZLE_SIZE * 3) +
                            ((((r / SQUARE_SIDE) * SQUARE_SIDE) +
                                    (c / SQUARE_SIDE)) * PUZZLE_SIDE) +
                            d], k));

                    // If this row is in the puzzle, add it to the
                    // list.

                    if (seedData[c][r] == (d + 1))
                        l[i++] = n;
                }

        // Remove the rows in the list and add them to the output.

        for (int j = 0; j < i; j++)
        {
            l[j].remove();
            o[index++] = l[j];
        }

        return o;
    }
}
