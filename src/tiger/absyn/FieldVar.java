package tiger.absyn;

import tiger.semant.ExpTy;
import tiger.symbol.Symbol;

public class FieldVar extends Var {
   public Var var;
   public Symbol field;
   public FieldVar(int p, Var v, Symbol f) {pos=p; var=v; field=f;}

    @Override
    public ExpTy transVar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
