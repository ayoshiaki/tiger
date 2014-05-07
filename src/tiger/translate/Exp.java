package tiger.translate;

import tiger.temp.Label;

public abstract class Exp {
  abstract tiger.tree.Exp unEx();
  abstract tiger.tree.Stm unNx();
  abstract tiger.tree.Stm unCx(Label t, Label f);
}
