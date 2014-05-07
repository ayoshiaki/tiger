package tiger.translate;

import tiger.frame.Frame;
import tiger.tree.Stm;

public class ProcFrag extends Frag {
  public Stm body;
  public Frame frame;
  public ProcFrag(Stm b,Frame f) {
    body = b;
    frame = f;
  }
}
