package tiger.translate;

import tiger.temp.Label;
import tiger.tree.Exp;
import tiger.tree.ExpList;
import tiger.tree.Stm;
import tiger.tree.Visitor;


class RelCx extends tiger.translate.Cx {
  int op;
  tiger.tree.Exp left, right;

  RelCx(int o, tiger.tree.Exp l, tiger.tree.Exp r) {
    op = o;
    left = l;
    right = r;
  }

  Stm unCx(Label t, Label f) {
    return new tiger.tree.CJUMP(op, left, right, t, f);
  }

    @Override
    public ExpList kids() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Exp build(ExpList kids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void accept(Visitor v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
