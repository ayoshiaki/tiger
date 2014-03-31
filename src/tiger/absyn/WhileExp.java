package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;

public class WhileExp extends Exp {
   public Exp test, body;
   public WhileExp(Position p, Exp t, Exp b) {setPosition(p); test=t; body=b;}

 }
