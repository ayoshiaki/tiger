package tiger.translate;

import tiger.temp.Label;
import tiger.tree.CJUMP;
import tiger.tree.CONST;
import tiger.tree.EXPR;
import tiger.tree.JUMP;
import tiger.tree.Stm;
import tiger.tree.Exp;


class Ex extends tiger.translate.Exp {
  Exp exp;
  Ex(Exp e) {
    exp = e;
  }
  Exp unEx() {
    return exp;
  }
  Stm unNx() {
    return new EXPR(exp);
  }
  Stm unCx(Label t, Label f) {
    // if the exp is a constant, emit JUMP statement.
    if (exp instanceof CONST) {
      CONST c = (CONST)exp;
      if (c.value == 0)
        return new JUMP(f);
      else
        return new JUMP(t);
    }
    return new CJUMP(CJUMP.NE, exp, new CONST(0), t, f);
  }
}
