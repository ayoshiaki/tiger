package tiger.tree;
public class MOVE extends Stm {
  public Exp dst, src;
  public MOVE(Exp d, Exp s) {dst=d; src=s;}
  @Override
  public ExpList kids() {
        if (dst instanceof MEM)
	   return new ExpList(((MEM)dst).exp, new ExpList(src,null));
	else return new ExpList(src,null);
  }
  @Override
  public Stm build(ExpList kids) {
        if (dst instanceof MEM)
	   return new MOVE(new MEM(kids.head), kids.tail.head);
	else return new MOVE(dst, kids.head);
  }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
