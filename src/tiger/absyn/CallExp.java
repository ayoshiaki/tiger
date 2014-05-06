package tiger.absyn;

import tiger.symbol.Symbol;

public class CallExp extends Exp {

    public Symbol func;
    public ExpList args;

    public CallExp(Position p, Symbol f, ExpList a) {
        setPosition(p);
        func = f;
        args = a;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
        
 

}
