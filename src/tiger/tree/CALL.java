package tiger.tree;

public class CALL extends Exp {
  public Exp func;
  public ExpList args;
  public CALL(Exp f, ExpList a) {func=f; args=a;}
  @Override
  public ExpList kids() {return new ExpList(func,args);}
  @Override
  public Exp build(ExpList kids) {
    return new CALL(kids.head,kids.tail);
  }

    @Override
    public void accept(Visitor v, int d) {
         v.visit(this, d);
    }
  
}
