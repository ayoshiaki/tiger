package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.types.Type;

public class SubscriptVar extends Var {

    public Var var;
    public Exp index;

    public SubscriptVar(Position p, Var v, Exp i) {
      setPosition(p);
        var = v;
        index = i;
    }

}
