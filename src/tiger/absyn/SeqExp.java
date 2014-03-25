package tiger.absyn;

import tiger.semant.ExpTy;
import tiger.types.INT;
import tiger.types.STRING;
import tiger.types.Type;

public class SeqExp extends Exp {

    public ExpList list;

    public SeqExp(int p, ExpList l) {
        pos = p;
        list = l;
    }

    @Override
    public ExpTy transExp() {
        ExpTy t = null;
        for (ExpList p = list; p != null; p = p.tail) {
            if (t == null) {
                t = p.head.transExp();
            }
        }
        if (t.isType(Type.INT)) {
            return new ExpTy(null, new INT());
        } else if (t.isType(Type.STRING)) {
            return new ExpTy(null, new STRING());
        }
        System.out.println("SeqExp, error  " + pos + " " );
        return new ExpTy(null, new INT());

    }
}
