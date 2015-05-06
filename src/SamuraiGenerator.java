import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Kyle on 5/6/2015.
 */
public class SamuraiGenerator {
    public static int PUZZLE_SIDE = 21; //2n^2 + n
    public static int PUZZLE_SIZE = 441; //(2n^2+n)^2
    public static int SUDOKU_SIDE = 9; //n^2
    public static int SUDOKU_SIZE = 81; //n^4
    public static int SQUARE_SIDE = 3; //n
    public static int COLUMN_SIZE = 1692;
    public static int BOX_AMOUNT;
    public Head head;

    public final String[] alphabet = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "!", "@", "#", "$",
            "%", "&", "?", "+", "=", "<", ">", "*", "~", "^"};


    public static int[][] SAMURAI_SQUARE =
            {{0}, {0}, {0}, {}, {1}, {1}, {1},
                    {0}, {0}, {0}, {}, {1}, {1}, {1},
                    {0}, {0}, {0, 2}, {2}, {1, 2}, {1}, {1},
                    {}, {}, {2}, {2}, {2}, {}, {},
                    {3}, {3}, {2, 3}, {2}, {2, 4}, {4}, {4},
                    {3}, {3}, {3}, {}, {4}, {4}, {4},
                    {3}, {3}, {3}, {}, {4}, {4}, {4}};

    public static int[][] SUDOKU_ROW =
            {{0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {-1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8}};

    public static int[][] SUDOKU_COLUMN =
            {{0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {-1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8}};

    public int dim;
    private ArrayList<String> originalCells;

    public void initializeVars() {
        PUZZLE_SIDE = 2 * dim ^ 2 + dim;
        PUZZLE_SIZE = PUZZLE_SIDE * PUZZLE_SIDE;
        SUDOKU_SIDE = dim * dim;
        SUDOKU_SIZE = SUDOKU_SIDE * SUDOKU_SIDE;
        SQUARE_SIDE = dim;
        COLUMN_SIZE = 2 * PUZZLE_SIZE + SUDOKU_SIZE * 10;
        BOX_AMOUNT = dim * 3 - 2;

        //INITIALIZE SAMURAI SQUARE
        for (int i = 0; i < BOX_AMOUNT; i++) {
            for (int j = 0; j < BOX_AMOUNT; j++) {
                int[] box = new int[0];
                if (i < dim && j < dim) {
                    box = new int[1];
                    box[0] = 0;
                } else if (j >= dim + dim - 2 && i < dim) {
                    box = new int[1];
                    box[0] = 1;
                } else if (i >= dim + dim - 2 && j < dim) {
                    box = new int[1];
                    box[0] = 3;
                } else if (i >= dim + dim - 2 && j >= dim + dim - 2) {
                    box = new int[1];
                    box[0] = 4;
                }
                if (i >= dim - 1 && i <= dim + dim - 2 && j >= dim - 1 && j <= dim + dim - 2) {
                    box = new int[1];
                    box[0] = 2;
                    if (i == dim - 1) {
                        if (j == dim - 1) {
                            box = new int[2];
                            box[0] = 0;
                            box[1] = 2;
                        } else if (j == dim + dim - 2) {
                            box = new int[2];
                            box[0] = 1;
                            box[1] = 2;
                        }
                    } else if (i == dim + dim - 2) {
                        if (j == dim - 1) {
                            box = new int[2];
                            box[0] = 2;
                            box[1] = 3;
                        } else if (j == dim + dim - 2) {
                            box = new int[2];
                            box[0] = 2;
                            box[1] = 4;
                        }
                    }
                }
                SAMURAI_SQUARE[i * BOX_AMOUNT + j] = box;
            }
        }

        //INITIALIZE SUDOKU ROW
        for (int i = 0; i < 5; i++) {
            int[] row = new int[0];
            switch (i) {
                case 0:
                case 1:
                    row = new int[dim * dim];
                    for (int k = 0; k < row.length; k++) {
                        row[k] = k;
                    }
                    break;
                case 2:
                    row = new int[2 * dim * dim - dim];
                    for (int k = 0; k < row.length; k++) {
                        if (k < dim * dim - dim) {
                            row[k] = -1;
                        } else {
                            row[k] = k - dim * dim - dim;
                        }
                    }
                    break;
                case 3:
                case 4:
                    row = new int[dim * dim + dim];
                    for (int k = 0; k < row.length; k++) {
                        if (k < dim * dim + dim) {
                            row[k] = -1;
                        } else {
                            row[k] = k - dim * dim + dim;
                        }
                    }
                    break;
            }
            SUDOKU_ROW[i] = row;
        }

        //INITIALIZE SUDOKU COLUMN
        for (int i = 0; i < 5; i++) {
            int[] row = new int[0];
            switch (i) {
                case 0:
                case 3:
                    row = new int[dim * dim];
                    for (int k = 0; k < row.length; k++) {
                        row[k] = k;
                    }
                    break;
                case 2:
                    row = new int[2 * dim * dim - dim];
                    for (int k = 0; k < row.length; k++) {
                        if (k < dim * dim - dim) {
                            row[k] = -1;
                        } else {
                            row[k] = k - dim * dim - dim;
                        }
                    }
                    break;
                case 1:
                case 4:
                    row = new int[dim * dim + dim];
                    for (int k = 0; k < row.length; k++) {
                        if (k < dim * dim + dim) {
                            row[k] = -1;
                        } else {
                            row[k] = k - dim * dim + dim;
                        }
                    }
                    break;
            }
            SUDOKU_COLUMN[i] = row;
        }
    }

    public String createFromFile(File f) {
        int[][] puzzle = parseInput(f);
        if (dim == 1) {
            //THIS PUZZLE FILE WAS NOT CORRECT SYNTAX
            return "File is incorrect";
        }

        initializeVars();
        addSeedData(puzzle);
        //Create the matrix from the seed data
        createMatrix(puzzle);
        return "SUCCESS";
    }

    private void addSeedData(int[][] seedData) {
        for (int i = 0; i < seedData.length; i++) {
            for (int j = 0; j < seedData.length; j++) {
                if (seedData[i][j] != 0) {
                    originalCells.add("R" + (i + 1) + "C" + (j + 1) + "#" + seedData[i][j]);
                }
            }
        }
    }

    private int[][] parseInput(File f) {
        int[][] puzzle = new int[1][1];
        try {
            Scanner fs1 = new Scanner(f);
            String row;
            row = fs1.nextLine().trim();
            String[] rowCells = row.split(" ");
            puzzle = new int[rowCells.length][rowCells.length];
            Scanner fs2 = new Scanner(f);
            int i = 0;
            while(fs2.hasNext()) {
                row = fs2.nextLine();
                rowCells = row.split(" ");
                int[] insert = new int[rowCells.length];
                for(int k = 0; k < rowCells.length; k++) {
                    insert[k] = Integer.parseInt(rowCells[k]);
                }
                puzzle[i] = insert;
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.dim = (int) (Math.sqrt(3 * puzzle.length + 1) + 1) / 3; //CORRECT: JOHN GOT WOLFRAM TO DO IT FOR HIM
        return puzzle;
    }

    public ArrayList<ArrayList<String>> solve(int max_sols) {
        if(max_sols <= 0) {
            max_sols = 100;
        }
        DancingLinks dl = new DancingLinks(head, dim, false, max_sols);
        dl.search(0);
        if(dl.ableToSolve)
            return dl.solutionSets;
        else {
            return null;
        }
    }

    public Node[] createMatrix(int[][] puzzle) {
        head = new Head(null, "");
        Head[] m = new Head[COLUMN_SIZE];
        Node[] o;
        int index = 0;

        // Create the row of columns.

        for (int i = 0; i < COLUMN_SIZE; i++)
            m[i] = new Head(head, "C" + (i + 1));

        // List of rows that are part of the solution.

        Node[] l = new Node[PUZZLE_SIZE];
        int i = 0;

        // For each samurai (big) row, column and possible digit.

        for (int r = 0; r < PUZZLE_SIDE; r++)
            for (int c = 0; c < PUZZLE_SIDE; c++)
                for (int d = 0; d < SUDOKU_SIDE; d++) {
                    // Calculate row number for possible
                    // move in samurai (big) puzzle.

                    String k = "R" + (r + 1) + "C" + (c + 1) + "#" + (d + 1);
                    //int k = 1 + (r * PUZZLE_SIDE * SUDOKU_SIDE) + (c * SUDOKU_SIDE) + d;

                    // See what samurai (big) square we're in.

                    int s = (c / 3) + ((r / 3) * 7);

                    // If the slot is in a puzzle create a row of
                    // nodes.

                    if (SAMURAI_SQUARE[s].length > 0) {
                        // Create a node for the slot.

                        Node n = new Node(m[(r * PUZZLE_SIDE) + c], k);

                        // For each puzzle that this slot is in...

                        for (int j = 0; j < SAMURAI_SQUARE[s].length; j++) {
                            // Find which puzzle the slot is in.

                            int pz = SAMURAI_SQUARE[s][j];

                            // Find the puzzle row and column.

                            int pr = SUDOKU_ROW[pz][r];
                            int pc = SUDOKU_COLUMN[pz][c];

                            // Add a node for the puzzle, row and
                            // digit.

                            n.add(new Node(m[PUZZLE_SIZE +
                                    (pz * SUDOKU_SIZE) +
                                    (pr * SUDOKU_SIDE) + d], k));

                            // Add a node for the puzzle, column
                            // and digit.

                            n.add(new Node(m[PUZZLE_SIZE + 405 +
                                    (pz * SUDOKU_SIZE) +
                                    (pc * SUDOKU_SIDE) + d], k));
                        }

                        // Add a node for the samurai (big) square
                        // and digit.

                        n.add(new Node(m[PUZZLE_SIZE + 405 + 405 +
                                (s * SUDOKU_SIDE) + d], k));

                        // If this row is in the puzzle, add it to the
                        // list.


                        //ROW COLUMN NOT COLUMN ROW
                        if (puzzle[r][c] == (d + 1))
                            l[i++] = n;
                    }
                }

        // There will be empty columns corresponding to the unused
        // slots in the samurai (big) puzzle. Remove the empty
        // columns.

        for (Head c = (Head) head.right; c != head; c = (Head) c.right)
            if (c.size == 0)
                c.cover();

        // Create an array for the output.

        o = new Node[PUZZLE_SIZE];

        // Remove the rows in the list and add them to the output.

        for (int j = 0; j < i; j++) {
            l[j].remove();
            o[index++] = l[j];
        }
        return o;
    }

    public int[][] convertSet(ArrayList<String> solution) {
        int[][] result = new int[PUZZLE_SIDE][PUZZLE_SIDE];
        int number;
        int length = result.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                String s = solution.get(i * length + j);
                number = Integer.parseInt(s.substring(s.indexOf('#') + 1).trim());
                result[i][j] = number;
            }
        }
        return result;
    }
}
