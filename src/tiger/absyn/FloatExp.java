package tiger.absyn;

public class FloatExp extends Exp {
   public float value;
   public FloatExp(Position p, float v) {setPosition(p); value=v;}
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    

}
