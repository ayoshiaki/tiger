package tiger.absyn;

import tiger.semant.ExpTy;

public class NilExp extends Exp {

    public NilExp(int p) {
        pos = p;
    }

    @Override
    public ExpTy transExp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
