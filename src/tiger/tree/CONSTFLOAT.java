package tiger.tree;

public class CONSTFLOAT extends Exp {
  public float value;
  public CONSTFLOAT(float v) {value=v;}
  public ExpList kids() {return null;}
  public Exp build(ExpList kids) {return this;}

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
