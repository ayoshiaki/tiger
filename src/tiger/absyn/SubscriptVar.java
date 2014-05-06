package tiger.absyn;

public class SubscriptVar extends Var {

    public Var var;
    public Exp index;

    public SubscriptVar(Position p, Var v, Exp i) {
      setPosition(p);
        var = v;
        index = i;
    }

        @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
