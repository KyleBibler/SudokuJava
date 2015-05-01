import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Kyle on 4/30/2015.
 */
public class MatrixGenerator {

    public HashMap<String, ArrayList<String>> matrix;
    public int dim;
    private ArrayList<String> originalCells;

    public MatrixGenerator(File f) {
        this.dim = 1;
        ArrayList<String> seedData = parseInput(f);

        int nSq = (int) Math.pow(dim, 2);
        int nFr = (int) Math.pow(dim, 4);
        matrix = new HashMap<String, ArrayList<String>>();

        for(int r = 1; r <= nSq; r++) {
            for(int c = 1; c <= nSq; c++) {
                for(int e = 1; e <= nSq; e++) {
                    String[] row = new String[4*nFr];
                    Arrays.fill(row, "");
                    int index;
                    int box;

                    //RC constraint
                    index = nSq*(r-1)+c-1;
                    row[index] = "R" + r + "C" + c;

                    //RN constraint
                    index = nFr + nSq*(r-1)+e-1;
                    row[index] = "R" + r + "#" + e;

                    //CN constraint
                    index = 2*nFr + nSq*(c-1)+e-1;
                    row[index] = "C" + c + "#" + e;

                    //BN constraint
                    box = (int) (dim*Math.floor((double)(r-1)/dim) + Math.ceil((double)c/dim));
                    index = 3*nFr + (int)(box-1)*nSq + (e-1);
                    row[index] = "B" + box + "#" + e;

                    //add to matrix
                    matrix.put("R" + r + "C" + c + "#" + e, new ArrayList<String>(Arrays.asList(row)));

                }
            }
        }
        reduceMatrix(seedData);
    }

    public ArrayList<String> parseInput(File f) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            Scanner fs = new Scanner(f);
            String row;
            int rowNum = 0;
            while(fs.hasNext()) {
                row = fs.nextLine().trim();
                if(row.isEmpty()) {
                    continue;
                }
                String[] boxes = row.split("   ");
                dim = boxes.length;
                rowNum++;
                int col = 1;
                for(int j = 0; j < boxes.length; j++) {
                    String[] numbers = boxes[j].trim().split(" ");
                    for(int n = 0; n < numbers.length; n++) {
                        if(!numbers[n].equals("0")) {
                            result.add(("R" + rowNum + "C" + col + "#" + numbers[n]).trim());
                        }
                        col++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        originalCells = new ArrayList<String>(result);
        return result;
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
            if(column == 9) {
                result += "\n";
                if(row == 3 || row == 6) {
                    result += "\n";
                }
            }
            else if(column % 3 == 0) {
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
}
