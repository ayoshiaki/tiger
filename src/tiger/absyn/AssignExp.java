package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.types.VOID;

public class AssignExp extends Exp {
   public Var var;
   public Exp exp;
   public AssignExp(Position p, Var v, Exp e) {setPosition(p); var=v; exp=e;}

  
}
