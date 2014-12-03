package tiger.absyn;


abstract public class Absyn {
  public Position pos;

    /**
     * @return the pos
     * testando commit
     */
     
    public Position getPosition() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPosition(Position pos) {
        this.pos = pos;
    }
    
    public abstract void accept(Visitor v);
  
}
