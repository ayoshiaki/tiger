package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.VarEntry;
import tiger.symbol.Symbol;

public class VarDec extends Dec {

    public Symbol name;
    public boolean escape = true;
    public NameTy typ; /* optional */

    public VarEntry entry;
    public Exp init;

    public VarDec(Position p, Symbol n, NameTy t, Exp i) {
        setPosition(p);
        name = n;
        typ = t;
        init = i;
    }

 
}
