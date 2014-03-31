package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.symbol.Symbol;

public  class RecordExp extends Exp {

    public Symbol typ;
    public FieldExpList fields;

    public RecordExp(Position p, Symbol t, FieldExpList f) {
        setPosition(p);
        typ = t;
        fields = f;
    }

 
    
}
