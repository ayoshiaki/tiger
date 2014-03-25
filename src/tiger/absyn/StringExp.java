package tiger.absyn;

import tiger.semant.ExpTy;
import tiger.types.STRING;

public class StringExp extends Exp {

    public String value;

    public StringExp(int p, String v) {
        pos = p;
        value = v;
    }

    @Override
   public ExpTy transExp() {
        return new ExpTy(null, new STRING());
    }
}
