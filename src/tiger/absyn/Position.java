package tiger.absyn;

public class Position {
    private int line;
    private int column;

    public Position (int l, int c){
        line = l;
        column = c;
    }
    /**
     * @return the line
     */
    public int getLine() {
        return line;
    }

    /**
     * @param line the line to set
     */
    public void setLine(int line) {
        this.line = line;
    }

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }
    
    public String toString(){
        return this.line + ":" + this.column;
    }
}
