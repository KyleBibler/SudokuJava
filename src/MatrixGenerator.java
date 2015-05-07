import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Kyle on 4/30/2015.
 */
public class MatrixGenerator {

    public int dim = 1;
    public ArrayList<String> originalCells;
    public Head head;
    public boolean generating;

    public static int PUZZLE_SIDE;
    public static int SQUARE_SIDE;
    public static int PUZZLE_SIZE;
    public static int COLUMN_SIZE;

    public final String[] alphabet = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "!", "@", "#", "$",
            "%", "&", "?", "+", "=", "<", ">", "*", "~", "^"};

    public String createFromFile(File f) {
        String finalBoard = "";
        head = new Head(null, "HEAD");
        int[][] seedData;
        originalCells = new ArrayList<String>();
        this.generating = false;
        seedData = parseInput(f);
        setUp(seedData);
        ArrayList<ArrayList<String>> sets = solve(0);
        if(sets != null) {
            int i = 1;
            for (ArrayList<String> set : sets) {
                if (set == null) {
                    continue;
                }
                if(!finalBoard.equals("")){
                    for(int j = 0; j < dim*dim*dim; j++) {
                        finalBoard += "-";
                    }
                    finalBoard += "\n";
                }
                finalBoard += "Solution: " + i + "\n";
                finalBoard += boardToString(buildFinished(set));
                i++;
            }
        }
        else {
            finalBoard = "Puzzle is invalid";
        }
        return finalBoard;
    }

    public Tuple<String, String> createNew(int n, int difficulty) {
        this.generating = true;
        this.originalCells = new ArrayList<String>();
        int[][] seedData;
        this.dim = n;

        SudokuGenerator sg = new SudokuGenerator();
        seedData = sg.generateSeedData(n);
        setUp(seedData);

        //Get the fully solved puzzle from the seed
        ArrayList<String> solvedPuzzle = solve(1).get(0);
        int[][] solvedSet = convertSet(buildFinished(solvedPuzzle));
        String solvedBoard = boardToString(solvedSet);

        int[][] finalPuzzle = sg.createFinalPuzzle(solvedSet, difficulty);
        String finalBoard = boardToString(finalPuzzle);
        return new Tuple<String, String> (finalBoard, solvedBoard);
    }

    public String createFromGiven(int[][] puzzle) {
        String result = "Puzzle was invalid";
        this.generating = false;
        this.dim = (int) Math.sqrt(puzzle.length);
        this.originalCells = new ArrayList<String>();
        setUp(puzzle);
        ArrayList<ArrayList<String>> sets = solve(1);
        if(sets != null) {
            result = boardToString(buildFinished(sets.get(0)));
        }
        return result;

    }

    public void setUp(int[][] seedData) {
        addSeedData(seedData);
        PUZZLE_SIDE = dim*dim;
        SQUARE_SIDE = dim;
        PUZZLE_SIZE = PUZZLE_SIDE*PUZZLE_SIDE;
        COLUMN_SIZE = 4*PUZZLE_SIZE;
        createMatrix(seedData);
    }

    public void addSeedData(int[][] seedData) {
        for(int i = 0; i < seedData.length; i++) {
            for(int j = 0; j < seedData.length; j++) {
                if(seedData[i][j] != 0) {
                    originalCells.add("R" + (i+1) + "C" + (j+1) + "#" + seedData[i][j]);
                }
            }
        }
    }

    public ArrayList<ArrayList<String>> solve(int max_sols) {
        if(max_sols <= 0) {
            max_sols = 100;
        }
        int solutionSize = (int) Math.pow(dim, 4);
        DancingLinks dl = new DancingLinks(head, solutionSize, generating, max_sols);
        dl.search(0);
        if(dl.ableToSolve)
            return dl.solutionSets;
        else {
            return null;
        }
    }

    public int[][] convertSet(ArrayList<String> solution) {
        int[][] result = new int[PUZZLE_SIDE][PUZZLE_SIDE];
        int number;
        int length = result.length;
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                String s = solution.get(i*length+j);
                number = Integer.parseInt(s.substring(s.indexOf('#')+1).trim());
                result[i][j] = number;
            }
        }
        return result;
    }

    public int[][] parseInput(File f) {
        try {
            Scanner fs1 = new Scanner(f);
            String row;


            row = fs1.nextLine().trim();
            String[] boxes = row.split("   ");
            dim = boxes.length;

            int[][] result = new int[dim*dim][dim*dim];

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
                            //result[rowNum][col] = Integer.parseInt(numbers[n]);
                            result[rowNum][col] = Arrays.asList(alphabet).indexOf(numbers[n]);
                        }
                        col++;
                    }
                }
                rowNum++;
            }
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new int[1][1];
    }



    public String boardToString(int[][] finishedCells) {
        String result = "";
        int n = (int) Math.sqrt(finishedCells.length);
        for(int row = 0; row < finishedCells.length; row++) {
            for(int col = 0; col < finishedCells.length; col++) {
                String num;
                if(finishedCells[row][col] >= alphabet.length)
                   num = "" + finishedCells[row][col];
                else { num = alphabet[finishedCells[row][col]]; }
                result += num;
                if(col+1 == n*n) {
                    result +="\n";
                } else if((col + 1) % n == 0) {
                    result += " \t";
                } else {
                    result += " ";
                }
            }
            if((row+1) % n == 0) {
                result += "\n";
            }
        }
        return result;
    }

    public String boardToString(ArrayList<String> finishedCells) {
        String result = "";
        int row;
        int column;
        int number;
        for(String s : finishedCells) {
            row = Integer.parseInt(s.substring(1, s.indexOf('C')).trim());
            column = Integer.parseInt(s.substring(s.indexOf('C')+1, s.indexOf('#')).trim());
            number = Integer.parseInt(s.substring(s.indexOf('#')+1).trim());
            if(number >= alphabet.length) { result += number; }
            else {result += alphabet[number]; }
            if(column == dim*dim) {
                result += "\n";
                if(row % dim == 0) {
                    result += "\n";
                }
            }
            else if(column % dim == 0) {
                result += " \t";
            } else {
                result += " ";
            }

        }
        return result;
    }

    public ArrayList<String> buildFinished(ArrayList<String> finishedCells) {
        finishedCells.addAll(originalCells);
        Collections.sort(finishedCells, new SudokuComp());
        //System.out.println(boardToString(finishedCells));
        return finishedCells;
    }

    public Node[] createMatrix(int[][] seedData) {
        head = new Head(null, "HEAD");
        Head[] m = new Head[COLUMN_SIZE];
        int index = 0;


        for (int i = 0; i < COLUMN_SIZE; i++)
            m[i] = new Head(head, "C"+(i+1));


        Node[] l = new Node[PUZZLE_SIZE];
        int i = 0;


        for (int r = 0; r < PUZZLE_SIDE; r++)
            for (int c = 0; c < PUZZLE_SIDE; c++)
                for (int d = 0; d < PUZZLE_SIDE; d++)
                {

                    String k = "R" + (r+1) + "C" + (c+1) + "#" + (d+1);

                    Node n = new Node(m[(r * PUZZLE_SIDE) + c], k);
                    n.add(new Node(m[(PUZZLE_SIZE * 1) +
                            (r * PUZZLE_SIDE) + d], k));
                    n.add(new Node(m[(PUZZLE_SIZE * 2) +
                            (c * PUZZLE_SIDE) + d], k));
                    n.add(new Node(m[(PUZZLE_SIZE * 3) +
                            ((((r / SQUARE_SIDE) * SQUARE_SIDE) +
                                    (c / SQUARE_SIDE)) * PUZZLE_SIDE) +
                            d], k));


                    if (seedData[r][c] == (d + 1))
                        l[i++] = n;
                }

        Node[] o = new Node[PUZZLE_SIZE];

        for(Head h = (Head)this.head.right; h != this.head; h = (Head)h.right) {
            int size = 0;
            for (Node n = h.down; n != h; n = n.down) {
                size++;
            }
            if (size != h.size){
                System.out.println(h.row + " size is wrong");
                System.out.println("expected " + size + " and got " + h.size);
            }
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
