package tiger.tree;

import tiger.temp.Temp;

public class TEMP extends Exp {
  public Temp temp;
  public TEMP(Temp t) {temp=t;}
  @Override
  public ExpList kids() {return null;}
  @Override
  public Exp build(ExpList kids) {return this;}
}
