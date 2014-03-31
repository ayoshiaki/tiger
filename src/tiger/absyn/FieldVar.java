package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.symbol.Symbol;
import tiger.types.RECORD;
import tiger.types.Type;
import tiger.types.VOID;

public class FieldVar extends Var {

    public Var var;
    public Symbol field;

    public FieldVar(Position p, Var v, Symbol f) {
        setPosition(p);
        var = v;
        field = f;
    }


}
