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
        this.up = this;
        this.down = this;
        this.right = this;
        this.left = this;

        if(col != null) {
            col.add(this);
        }
    }

    public void add(Node n)
    {
        n.left = this.left;
        n.right = this;

        this.left.right = n;
        this.left = n;
    }
    public void remove()
    {
        Node n = this;

        // Cover this node's column and move on to the next right.
        do {
            n.col.cover();
            n = n.right;
        } while (n != this);
    }

}
