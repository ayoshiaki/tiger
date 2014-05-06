package tiger.absyn;

public class VarExp extends Exp {

    public Var var;

    public VarExp(Position p, Var v) {
        setPosition(p);
        var = v;
    }
        @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}

