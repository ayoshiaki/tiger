package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;

public class LetExp extends Exp {

    public DecList decs;
    public Exp body;

    public LetExp(Position p, DecList d, Exp b) {
        setPosition(p);
        decs = d;
        body = b;
    }

}
