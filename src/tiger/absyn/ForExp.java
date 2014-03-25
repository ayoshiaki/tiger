package tiger.absyn;

import tiger.semant.ExpTy;

public class ForExp extends Exp {
   public VarDec var;
   public Exp hi, body;
   public ForExp(int p, VarDec v, Exp h, Exp b) {pos=p; var=v; hi=h; body=b;}

    @Override
    public ExpTy transExp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
