package tiger.absyn;

import tiger.semant.FunEntry;
import tiger.symbol.Symbol;

public class FunctionDec extends Dec {

    public Symbol name;
    public FieldList params;
    public NameTy result = null;  /* optional */

    public Exp body;
    public FunctionDec next;
    public FunEntry entry;

    public FunctionDec(Position p, Symbol n, FieldList a, NameTy r, Exp b, FunctionDec x) {
        setPosition(p);
        name = n;
        params = a;
        result = r;
        body = b;
        next = x;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }


}
