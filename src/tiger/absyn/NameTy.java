package tiger.absyn;

import tiger.symbol.Symbol;
import tiger.types.Type;

public class NameTy extends Ty {

    public Symbol name;

    public NameTy(int p, Symbol n) {
        pos = p;
        name = n;
    }

    @Override
    public Type transTy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
