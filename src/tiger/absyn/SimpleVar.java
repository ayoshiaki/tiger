package tiger.absyn;

import tiger.symbol.Symbol;

public class SimpleVar extends Var {

    public Symbol name;

    public SimpleVar(Position p, Symbol n) {
        setPosition(p);
        name = n;
    }
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }


}
