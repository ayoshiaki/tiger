package tiger.absyn;

import tiger.symbol.Symbol;

public class ArrayExp extends Exp {

    public Symbol typ;
    public Exp size, init;

    public ArrayExp(Position p, Symbol t, Exp s, Exp i) {
        setPosition(p);
        typ = t;
        size = s;
        init = i;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    
  
}
