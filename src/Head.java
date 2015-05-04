public class Head extends Node{
    public int size;
    public int col;

    public Head(Head col) {
        super(col, "0");
        this.size = 0;
    }

    public void detach() {
        this.left.right = this.right;
        this.right.left = this.left;
    }

}
