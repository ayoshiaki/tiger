package tiger.absyn;

import tiger.semant.Entry;
import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.semant.FunEntry;
import tiger.symbol.Symbol;
import tiger.types.RECORD;
import tiger.types.VOID;
import tiger.types.VOID;

public class CallExp extends Exp {

    public Symbol func;
    public ExpList args;

    public CallExp(Position p, Symbol f, ExpList a) {
        setPosition(p);
        func = f;
        args = a;
    }



   
}
