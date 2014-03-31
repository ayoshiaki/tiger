package tiger.absyn;

import java.util.Hashtable;
import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.semant.FunEntry;
import tiger.semant.VarEntry;
import tiger.symbol.Symbol;
import tiger.types.NAME;
import tiger.types.RECORD;
import tiger.types.Type;

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



}
