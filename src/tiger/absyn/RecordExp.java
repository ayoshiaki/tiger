package tiger.absyn;

import tiger.semant.ExpTy;
import tiger.symbol.Symbol;

public  class RecordExp extends Exp {

    public Symbol typ;
    public FieldExpList fields;

    public RecordExp(int p, Symbol t, FieldExpList f) {
        pos = p;
        typ = t;
        fields = f;
    }

    @Override
   public ExpTy transExp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
