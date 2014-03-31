package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;

public class VarExp extends Exp {

    public Var var;

    public VarExp(Position p, Var v) {
        setPosition(p);
        var = v;
    }
}

