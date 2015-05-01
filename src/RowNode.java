public class RowNode extends Node {
    public String row;
    public Head col;
    public RowNode(String row, Head col) {
        super();
        this.row = row;
        this.col = col;
    }

    public void detach() {
        this.up.down = this.down;
        this.down.up = this.up;
        this.col.size--;
    }

    public void attach() {
        this.col.size++;
        this.down.up = this;
        this.up.down = this;
    }
}
