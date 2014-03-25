package tiger.absyn;

import tiger.semant.ExpTy;
import tiger.types.INT;
import tiger.types.NAME;
import tiger.types.NIL;
import tiger.types.RECORD;
import tiger.types.STRING;
import tiger.types.Type;
import tiger.types.VOID;

public class VarExp extends Exp {

    public Var var;

    public VarExp(int p, Var v) {
        pos = p;
        var = v;
    }

    @Override
    public ExpTy transExp() {
      return var.transVar();
      
    }
}

