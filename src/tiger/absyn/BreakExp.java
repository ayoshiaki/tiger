package tiger.absyn;

public class BreakExp extends Exp {
   public BreakExp(Position p) {setPosition(p);}
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
