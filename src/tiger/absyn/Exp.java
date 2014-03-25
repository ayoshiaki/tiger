package tiger.absyn;

import tiger.semant.ExpTy;

abstract public class Exp extends Absyn {
    abstract public ExpTy transExp();
}
