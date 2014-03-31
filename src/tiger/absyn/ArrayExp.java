package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.symbol.Symbol;
import tiger.types.ARRAY;
import tiger.types.INT;
import tiger.types.NAME;
import tiger.types.Type;
import tiger.types.VOID;

public class ArrayExp extends Exp {

    public Symbol typ;
    public Exp size, init;

    public ArrayExp(Position p, Symbol t, Exp s, Exp i) {
        setPosition(p);
        typ = t;
        size = s;
        init = i;
    }

  
}
