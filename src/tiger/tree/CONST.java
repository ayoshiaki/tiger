package tiger.tree;

public class CONST extends Exp {
  public int value;
  public CONST(int v) {value=v;}
  public ExpList kids() {return null;}
  public Exp build(ExpList kids) {return this;}

    @Override
    public void accept(Visitor v, int d) {
        v.visit(this, d);
    }
}
