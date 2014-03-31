package tiger.absyn;

import tiger.semant.Entry;
import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.semant.VarEntry;
import tiger.symbol.Symbol;
import tiger.types.INT;

public class SimpleVar extends Var {

    public Symbol name;

    public SimpleVar(Position p, Symbol n) {
        setPosition(p);
        name = n;
    }


}
