package tiger.absyn;

import tiger.semant.ExpTy;
import tiger.symbol.Symbol;

public class CallExp extends Exp {

    public Symbol func;
    public ExpList args;

    public CallExp(int p, Symbol f, ExpList a) {
        pos = p;
        func = f;
        args = a;
    }

    @Override
    public ExpTy transExp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
