package tiger.absyn;

import tiger.symbol.Symbol;

public  class RecordExp extends Exp {

    public Symbol typ;
    public FieldExpList fields;

    public RecordExp(Position p, Symbol t, FieldExpList f) {
        setPosition(p);
        typ = t;
        fields = f;
    }
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

 
    
}
