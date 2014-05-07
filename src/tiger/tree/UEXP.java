package tiger.tree;
public class UEXP extends Stm {
  public Exp exp; 
  public UEXP(Exp e) {exp=e;}
  @Override
  public ExpList kids() {return new ExpList(exp,null);}
  @Override
  public Stm build(ExpList kids) {
    return new UEXP(kids.head);
  }
}
