package tiger.absyn;

import tiger.semant.ExpTy;
import tiger.types.VOID;

public class AssignExp extends Exp {
   public Var var;
   public Exp exp;
   public AssignExp(int p, Var v, Exp e) {pos=p; var=v; exp=e;}

    @Override
    public ExpTy transExp() {
        ExpTy var = this.var.transVar();
        ExpTy exp = this.exp.transExp();
        if(!exp.getTy().coerceTo(var.getTy())){
            System.err.println("Posicao " + pos + ": tipos incompativeis na atribuicao");
            
        }
        return new ExpTy(null, new VOID());
    }
}
