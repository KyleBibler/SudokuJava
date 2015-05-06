import java.util.Comparator;

public class SudokuComp implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        int row1 = Integer.parseInt(s1.substring(1, s1.indexOf('C')).trim());
        int col1 = Integer.parseInt(s1.substring(s1.indexOf('C')+1, s1.indexOf('#')).trim());
        int num1 = Integer.parseInt(s1.substring(s1.indexOf('#') + 1).trim());
        int row2 = Integer.parseInt(s2.substring(1, s2.indexOf('C')).trim());
        int col2 = Integer.parseInt(s2.substring(s2.indexOf('C')+1, s2.indexOf('#')).trim());
        int num2 = Integer.parseInt(s2.substring(s2.indexOf('#')+1).trim());
        if(row1 != row2) {
            return row1 - row2;
        } else if(col1 != col2) {
            return col1 - col2;
        } else {
            return num1 - num2;
        }
    }
}
