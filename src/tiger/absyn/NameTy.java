package tiger.absyn;

import tiger.semant.Env;
import tiger.symbol.Symbol;
import tiger.types.NAME;
import tiger.types.Type;
import tiger.types.VOID;

public class NameTy extends Ty {

    public Symbol name;

    public NameTy(Position p, Symbol n) {
        setPosition(p);
        name = n;
    }

}
