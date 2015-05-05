import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kyle on 5/4/2015.
 */
public class SudokuGenerator {

    public static void main(String[] args) {
        int dim = 3;
        long startTime = System.currentTimeMillis();
        int[][] puzzle = generatePuzzle(dim);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime/1000);
        String result = "";
        for(int i = 0; i < puzzle.length; i++) {
            for(int j = 0; j < puzzle[i].length; j++) {
               result += puzzle[i][j];
               if((j+1) % dim == 0) {
                   result += "    ";
               } else {
                   result += " ";
               }
            }
            result += "\n";
            if((i+1) % dim == 0) {
                result += "\n";
            }
        }
        System.out.println(result);

    }

    public static int[][] generatePuzzle(int n) {
        int nSq = n*n;
        Random rand = new Random();
        ArrayList<Integer> canUse = new ArrayList<Integer>();
        ArrayList<Integer> renew = new ArrayList<Integer>();
        for(int i = 0; i < nSq; i++) {
            canUse.add(i+1);
            renew.add(i+1);
        }
        int index;
        int[][] result = new int[nSq][nSq];
        boolean solvable = false;
        while(!solvable) {
            for (int i = 0; i < result.length; i += n) {
                for (int j = 0; j < result[i].length; j += n) {
                    for (int iBlock = i; iBlock < i + n; iBlock++) {
                        for (int jBlock = j; jBlock < j + n; jBlock++) {
                            index = rand.nextInt(canUse.size());
                            result[iBlock][jBlock] = canUse.get(index);
                            canUse.remove(index);
                        }
                    }
                    canUse.addAll(renew);
                }
            }

            boolean rowsAreValid = false;
            for(int i = 0; i < result.length; i++) {
                if(isValidRow(result[i])) {
                    rowsAreValid = true;
                } else {
                    rowsAreValid = false;
                    break;
                }
            }

            if(rowsAreValid) {
                boolean colsAreValid = false;
                for (int j = 0; j < result.length; j++) {
                    if (isValidCol(result, j)) {
                        colsAreValid = true;
                    } else {
                        colsAreValid = false;
                        break;
                    }
                }
                if(colsAreValid) {
                    solvable = true;
                }
            }
        }
        return result;
    }

    public static boolean isValidCol(int[][] puzzle, int c) {
        int[] colNums = new int[puzzle.length];
        int num;
        for(int i = 0; i < puzzle.length; i++) {
            num = puzzle[i][c]-1;
            if(colNums[num] != 0) {
                return false;
            } else  {
                colNums[num] = 1;
            }
        }
        return true;
    }

    public static boolean isValidRow(int[] row) {
        int[] rowNums = new int[row.length];
        int num;
        for(int j = 0; j < row.length; j++) {
            num = row[j] - 1;
            if(rowNums[num] != 0) {
                return false;
            }
            else {
                rowNums[num] = 1;
            }
        }
        return true;
    }
}
