package tiger.absyn;

import tiger.semant.ExpTy;

abstract public class Var extends Absyn {
    abstract public ExpTy transVar();
}
