package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;

public class LetExp extends Exp {

    public DecList decs;
    public Exp body;

    public LetExp(int p, DecList d, Exp b) {
        pos = p;
        decs = d;
        body = b;
    }

    @Override
    public ExpTy transExp() {
        Env env = Env.getEnv();
        env.getVenv().beginScope();
        env.getTenv().beginScope();
        for(DecList p = decs; p!=null;p=p.tail)
            p.head.transDec();
        ExpTy et = body.transExp();
        env.getVenv().endScope();
        env.getTenv().endScope();
        return new ExpTy(null, et.getTy());
    }
}
