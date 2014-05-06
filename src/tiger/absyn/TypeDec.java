package tiger.absyn;

import tiger.symbol.Symbol;
import tiger.types.NAME;

public class TypeDec extends Dec {

    public Symbol name;
    public Ty ty;
    public TypeDec next;
    public NAME entry;

    public TypeDec(Position p, Symbol n, Ty t, TypeDec x) {
        setPosition(p);
        name = n;
        ty = t;
        next = x;
    }
        @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
