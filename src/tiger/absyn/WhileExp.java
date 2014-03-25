package tiger.absyn;

import tiger.semant.ExpTy;

public class WhileExp extends Exp {
   public Exp test, body;
   public WhileExp(int p, Exp t, Exp b) {pos=p; test=t; body=b;}

    @Override
    public ExpTy transExp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
