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

    public final String[] alphabet = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "!", "@", "#", "$",
            "%", "&", "?", "+", "=", "<", ">", "*", "~", "^"};



    public static int[][] SAMURAI_SQUARE =
            {{0}, {0},    {0},  {},    {1}, {1}, {1},
                    {0}, {0},    {0},  {},    {1}, {1}, {1},
                    {0}, {0}, {0, 2}, {2}, {1, 2}, {1}, {1},
                    {},  {},    {2}, {2},    {2},  {},  {},
                    {3}, {3}, {2, 3}, {2}, {2, 4}, {4}, {4},
                    {3}, {3},    {3}, {},     {4}, {4}, {4},
                    {3}, {3},    {3}, {},     {4}, {4}, {4}};

    public static int[][] SUDOKU_ROW =
            {{0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {-1,-1,-1,-1,-1,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8}};

    public static int[][] SUDOKU_COLUMN =
            {{0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {-1,-1,-1,-1,-1,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {0, 1, 2, 3, 4, 5, 6, 7, 8},
                    {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8}};

    public int dim;

    public void initializeVars() {
        PUZZLE_SIDE = 2*dim^2 + dim;
        PUZZLE_SIZE = PUZZLE_SIDE*PUZZLE_SIDE;
        SUDOKU_SIDE = dim*dim;
        SUDOKU_SIZE = SUDOKU_SIDE*SUDOKU_SIDE;
        SQUARE_SIDE = dim;
        COLUMN_SIZE = 2*PUZZLE_SIZE+SUDOKU_SIZE*10;
        BOX_AMOUNT = dim*3 - 2;

        //INITIALIZE SAMURAI SQUARE
        for(int i = 0; i < BOX_AMOUNT; i++) {
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
        for(int i = 0; i < 5; i++) {
            int[] row = new int[0];
            switch (i){
                case 0:
                case 1:
                    row = new int[dim*dim];
                    for(int k = 0; k < row.length; k++) {
                        row[k] = k;
                    }
                    break;
                case 2:
                    row = new int[2*dim*dim-dim];
                    for(int k = 0; k < row.length; k++) {
                        if(k < dim*dim-dim) {
                            row[k] = -1;
                        } else {
                            row[k] = k-dim*dim-dim;
                        }
                    }
                    break;
                case 3:
                case 4:
                    row = new int[dim*dim+dim];
                    for(int k = 0; k < row.length; k++) {
                        if(k < dim*dim+dim) {
                            row[k] = -1;
                        } else {
                            row[k] = k-dim*dim+dim;
                        }
                    }
                    break;
            }
            SUDOKU_ROW[i] = row;
        }

        //INITIALIZE SUDOKU COLUMN
        for(int i = 0; i < 5; i++) {
            int[] row = new int[0];
            switch (i){
                case 0:
                case 3:
                    row = new int[dim*dim];
                    for(int k = 0; k < row.length; k++) {
                        row[k] = k;
                    }
                    break;
                case 2:
                    row = new int[2*dim*dim-dim];
                    for(int k = 0; k < row.length; k++) {
                        if(k < dim*dim-dim) {
                            row[k] = -1;
                        } else {
                            row[k] = k-dim*dim-dim;
                        }
                    }
                    break;
                case 1:
                case 4:
                    row = new int[dim*dim+dim];
                    for(int k = 0; k < row.length; k++) {
                        if(k < dim*dim+dim) {
                            row[k] = -1;
                        } else {
                            row[k] = k-dim*dim+dim;
                        }
                    }
                    break;
            }
            SUDOKU_COLUMN[i] = row;
        }
    }

    public void createFromGiven(int[][] puzzle) {
        this.dim = (int) (Math.sqrt(3*puzzle.length+1) + 1)/3; //CORRECT: JOHN GOT WOLFRAM TO DO IT FOR HIM
        initializeVars();

        createMatrix(puzzle);

    }

    public void createMatrix(int[][] puzzle) {

    }
}
