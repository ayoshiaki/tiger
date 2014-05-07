package tiger.tree;

import tiger.temp.Label;

public class NAME extends Exp {
  public Label label;
  public NAME(Label l) {label=l;}
  @Override
  public ExpList kids() {return null;}
  @Override
  public Exp build(ExpList kids) {return this;}
}
