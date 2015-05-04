public class Node {
    public Node up;
    public Node down;
    public Node left;
    public Node right;
    public Head col;
    public String row;

    public Node(Head col, String row) {
        this.row = row;
        this.col = col;
        this.up = null;
        this.down = null;
        this.right = null;
        this.left = null;
    }

}
