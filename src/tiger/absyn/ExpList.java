package tiger.absyn;

import tiger.semant.ExpTy;
import tiger.types.RECORD;

public class ExpList {

    public Exp head;
    public ExpList tail;

    public ExpList(Exp h, ExpList t) {
        head = h;
        tail = t;
    }

   
}
