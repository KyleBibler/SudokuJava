public class Head extends Node{
    public int size;
    public int col;

    public Head(Head col, String colName) {
        super(col, colName);
        this.size = 0;

        if(col != null) {
            col.add(this);
        }
    }

    public void detach() {
        this.left.right = this.right;
        this.right.left = this.left;
    }

    public void cover() {

        right.left = left;
        left.right = right;

        for(Node d = down; d != this; d = d.down) {
            for(Node rowNeighbor = d.right; rowNeighbor != d; rowNeighbor = rowNeighbor.right) {
                if(rowNeighbor.getClass().equals(Head.class)) {
                    System.out.println("What happened?");
                }
                rowNeighbor.up.down = rowNeighbor.down;
                rowNeighbor.down.up = rowNeighbor.up;
                rowNeighbor.col.size--;
            }
        }
    }

    public void uncover() {
        Node rowNeighbor;
        for(Node u = up; u != this; u = u.up) {
            rowNeighbor = u.left;
            while(rowNeighbor != u) {
                rowNeighbor.up.down = rowNeighbor;
                rowNeighbor.down.up = rowNeighbor;
                rowNeighbor.col.size++;
                rowNeighbor = rowNeighbor.left;
            }
        }
        left.right = this;
        right.left = this;
    }

    public void add(Head c)
    {
        c.left = this.left;
        c.right = this;

        this.left.right = c;
        this.left = c;
    }

    // Add a node to the end of this column.

    public void add(Node n)
    {
        n.up = this.up;
        n.down = this;

        this.up.down = n;
        this.up = n;

        // Increment the column size.

        size++;
    }

}
