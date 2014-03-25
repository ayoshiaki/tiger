package tiger.absyn;

import tiger.semant.ExpTy;
import tiger.types.INT;

public class IntExp extends Exp {
   public int value;
   public IntExp(int p, int v) {pos=p; value=v;}

    @Override
    public ExpTy transExp() {
       return new ExpTy(null, new INT());
    }
}
