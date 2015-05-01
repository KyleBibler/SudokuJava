public abstract class Node {
    public Node up;
    public Node down;
    public Node left;
    public Node right;
    public Node() {
        this.up = null;
        this.down = null;
        this.right = null;
        this.left = null;
    }
    public abstract void detach();
    public abstract void attach();
}
