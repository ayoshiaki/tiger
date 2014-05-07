package tiger.translate;

import tiger.temp.Label;
import tiger.temp.Temp;
import tiger.tree.Exp;
import tiger.tree.CONST;
import tiger.tree.ESEQ;
import tiger.tree.LABEL;
import tiger.tree.MOVE;
import tiger.tree.SEQ;
import tiger.tree.Stm;
import tiger.tree.TEMP;

abstract class Cx extends Exp {

  tiger.tree.Exp unEx() {
    Temp r = new Temp();
    Label t = new Label();
    Label f = new Label();

    return new ESEQ
      (new SEQ
       (new MOVE(new TEMP(r), new CONST(1)),
	new SEQ(unCx(t, f),
		     new SEQ
		     (new LABEL(f),
		      new SEQ(new MOVE(new TEMP(r),
						 new CONST(0)),
				   new LABEL(t))))),
       new TEMP(r));
  }

  abstract Stm unCx(Label t, Label f);

  Stm unNx() {
    Label join = new Label();

    return new SEQ(unCx(join, join), new LABEL(join));
  }
}
