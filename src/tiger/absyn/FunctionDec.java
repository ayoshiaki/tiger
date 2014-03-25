package tiger.absyn;

import tiger.semant.FunEntry;
import tiger.symbol.Symbol;
import tiger.types.RECORD;
import tiger.types.Type;

public class FunctionDec extends Dec {

    public Symbol name;
    public FieldList params;
    public NameTy result =null;  /* optional */

    public Exp body;
    public FunctionDec next;

    public FunctionDec(int p, Symbol n, FieldList a, NameTy r, Exp b, FunctionDec x) {
        pos = p;
        name = n;
        params = a;
        result = r;
        body = b;
        next = x;
    }

    @Override
    public Exp transDec() {
        
    
        return null; 
    }
}
