package tiger.translate;

import tiger.temp.Label;
import tiger.tree.Stm;

class Nx extends Exp {
  Stm stm;
  Nx(Stm s) {
    stm = s;
  }

  tiger.tree.Exp unEx() {
    return null;
  }

  Stm unNx() {
    return stm;
  }

  Stm unCx(Label t, Label f) {
    return null;
  }
}
