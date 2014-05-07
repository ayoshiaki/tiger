package tiger.tree;
import tiger.temp.Temp;
public class EXPR extends Stm {
  public Exp exp; 
  public EXPR(Exp e) {exp=e;}
  @Override
  public ExpList kids() {return new ExpList(exp,null);}
  @Override
  public Stm build(ExpList kids) {
    return new EXPR(kids.head);
  }
}

