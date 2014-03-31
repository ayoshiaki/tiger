package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.types.Type;
import tiger.types.VOID;

public class SeqExp extends Exp {

    public ExpList list;

    public SeqExp(Position p, ExpList l) {
        setPosition(p);
        list = l;
    }


}
