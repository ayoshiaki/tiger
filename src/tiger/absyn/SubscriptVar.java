package tiger.absyn;

import tiger.semant.ExpTy;
import tiger.types.Type;

public class SubscriptVar extends Var {

    public Var var;
    public Exp index;

    public SubscriptVar(int p, Var v, Exp i) {
        pos = p;
        var = v;
        index = i;
    }

    @Override
    public ExpTy transVar() {
        if(!index.transExp().getTy().isType(Type.INT)) {
            System.err.println("O indice do vetor deve ser um inteiro !");
        }
        return new ExpTy(null, var.transVar().getTy());
    }
}
