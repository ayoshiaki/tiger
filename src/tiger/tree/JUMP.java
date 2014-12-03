package tiger.tree;

import tiger.temp.Label;
import tiger.temp.LabelList;

public class JUMP extends Stm {
  public Exp exp;
  public LabelList targets;
  public JUMP(Exp e, LabelList t) {exp=e; targets=t;}
  public JUMP(Label target) {
      this(new NAME(target), new LabelList(target,null));
  }
  @Override
  public ExpList kids() {return new ExpList(exp,null);}
  @Override
  public Stm build(ExpList kids) {
    return new JUMP(kids.head,targets);
  }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
