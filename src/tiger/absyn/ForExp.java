package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.types.Type;
import tiger.types.VOID;
import tiger.semant.LoopVarEntry;
import tiger.types.INT;

public class ForExp extends Exp {

    public VarDec var;
    public Exp hi, body;

    public ForExp(Position p, VarDec v, Exp h, Exp b) {
        setPosition(p);
        var = v;
        hi = h;
        body = b;
    }

  
}
