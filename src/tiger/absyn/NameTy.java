package tiger.absyn;

import tiger.symbol.Symbol;

public class NameTy extends Ty {

    public Symbol name;

    public NameTy(Position p, Symbol n) {
        setPosition(p);
        name = n;
    }

        @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
