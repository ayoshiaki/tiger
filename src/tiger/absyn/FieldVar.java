package tiger.absyn;

import tiger.symbol.Symbol;

public class FieldVar extends Var {

    public Var var;
    public Symbol field;

    public FieldVar(Position p, Var v, Symbol f) {
        setPosition(p);
        var = v;
        field = f;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }


}
