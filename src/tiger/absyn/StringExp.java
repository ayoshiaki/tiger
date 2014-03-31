package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.types.STRING;

public class StringExp extends Exp {

    public String value;

    public StringExp(Position p, String v) {
       setPosition(p);
        value = v;
    }
}
