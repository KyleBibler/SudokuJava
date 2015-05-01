public class Head extends Node{
    public int size;
    public int col;

    public Head(int col) {
        super();
        this.col = col;
        this.size = 0;
    }

    public void detach() {
        this.left.right = this.right;
        this.right.left = this.left;
    }

    public void attach() {
        this.left.right = this;
        this.right.left = this;
    }

}
