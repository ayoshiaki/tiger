package tiger.absyn;

public class IntExp extends Exp {
   public int value;
   public IntExp(Position p, int v) {setPosition(p); value=v;}
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
