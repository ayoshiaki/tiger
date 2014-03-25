package tiger.absyn;

import tiger.semant.ExpTy;
import tiger.types.VOID;

public class BreakExp extends Exp {
   public BreakExp(int p) {pos=p;}

    @Override
    public ExpTy transExp() {
        System.err.println("Instrucao de Break fora de laço");
        return new ExpTy(null, new VOID());
    }
}
