package tiger.absyn;

public class IfExp extends Exp {

    public Exp test;
    public Exp thenclause;
    public Exp elseclause; /* optional */


    public IfExp(Position p, Exp x, Exp y) {
        setPosition(p);
        test = x;
        thenclause = y;
        elseclause = null;
    }

    public IfExp(Position p, Exp x, Exp y, Exp z) {
        setPosition(p);
        test = x;
        thenclause = y;
        elseclause = z;
    }

      @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
