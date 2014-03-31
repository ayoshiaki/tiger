package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.types.Type;

public class IfExp extends Exp {

    public Exp test;
    public Exp thenclause;
    public Exp elseclause; /* optional */


    public IfExp(Position p, Exp x, Exp y) {
        setPosition(p);
        test = x;
        thenclause = y;
        elseclause = null;
    }

    public IfExp(Position p, Exp x, Exp y, Exp z) {
        setPosition(p);
        test = x;
        thenclause = y;
        elseclause = z;
    }

  
}
