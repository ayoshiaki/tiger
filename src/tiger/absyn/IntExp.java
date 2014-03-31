package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.types.INT;

public class IntExp extends Exp {
   public int value;
   public IntExp(Position p, int v) {setPosition(p); value=v;}

}
