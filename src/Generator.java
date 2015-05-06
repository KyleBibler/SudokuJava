public class Generator {
    // Board of the game 9x9.
    private int[][] board;
    private final int length;
    /**
     * Differnt levels for the game as follows:
     * No.  Name
     * -------------------
     * 1    Extremely easy
     * 2    Easy
     * 3    Medium
     * 4    Difficult
     * 5    Evil
     */
    private int level;

    /**
     * Default constructor for the Generator class setting the level of the 
     * game to 1, each constructor generateSeedData a new board.
     */
    public Generator(){
        newBoard();
        length = board.length;
        setLevel(1);
    }

    /**
     * Constructor that takes allows to the user set the level for the board.
     * @param level the level of the game [1-5]
     */
    public Generator(int level){
        newBoard();
        length = board.length;
        setLevel(level);
    }

    /**
     * Defines a new board with the size of 9x9.
     */
    private void newBoard(){
        board = new int[9][9];
    }

    /**
     * Return the board at any time.
     * @return int[][] The board of 9x9
     */
    public int[][] getBoard(){
        return board;
    }

    /**
     * Set the current level at any time.
     * @param level
     */
    public void setLevel(int level){
        if(level >= 1 && level <= 5){
            this.level = level;
        }else{
            this.level = 1;
        }
        // TODO: Regenerate the board.
        // Issue #5 at github repo.
    }

    /**
     * Return the current level of the Game
     * @return  int     The current level of the game  
     */
    public int getLevel(){
        return this.level;
    }

    /**
     * Using the first sequence for the first level, the sequence is as follows
     * from left to right and from top to bottom
     */
    private void sequence1(){
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
            }
        }
    }
    /**
     * Second sequence. The sequences is as follows
     * Wandering along "S", at the time if it's in the last column jumps one cell
     * down in the current row, and follows from right to left.
     */
    private void sequence2(){
        int j = 0;
        for(int i = 0; i < length; i++){
            if(j == 0){
                for(j = 0; j < length; j++){
                }
            }else{
                for(j = length - 1; j >= 0; j--){
                }
            }
        }
    }

    /**
     * Third sequence. The sequence is as follows
     * Jumping one cell at the time, this means for example: 0,3,5,7.
     */
    private void sequence3(){
        for(int i = 0; i < length; i++){
            if(i % 2 == 0){
                for(int j = 0, k = 1; j < length; j += 2, k += 2){
                    board[i][j] = 0;
                    if(k < length){
                        board[i][k] = 0;
                    }
                }
            }else{
                for(int j = length - 2, k = length - 1; j >= 0; j -= 2, k -= 2){
                    board[i][j] = 0;
                    if(k >= 0){
                        board[i][k] = 0;
                    }
                }
            }
        }
    }

    /**
     * Override the natural behavior of toString to print te board as it should
     * be look like, at least something more easy to understand, just for debug
     * purpose 
     * @return
     */
    @Override
    public String toString(){
        String boardString = "";
        int length = board.length;

        for(int i = 0; i < length; i++){
            if(i % 3 == 0){
                boardString += "====================\n";
            }
            for(int j = 0; j < length; j++){
                if(j% 3 == 0 ){
                    boardString += " | ";
                }
                boardString += Integer.toString(board[i][j]);
            }
            boardString += "|";
            boardString += "\n";
        }
        boardString += "====================\n";

        return boardString;
    }
}