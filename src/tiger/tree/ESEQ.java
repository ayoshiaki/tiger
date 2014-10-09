package tiger.tree;

public class ESEQ extends Exp {
  public Stm stm;
  public Exp exp;
  public ESEQ(Stm s, Exp e) {stm=s; exp=e;}
  @Override
  public ExpList kids() {throw new Error("kids() not applicable to ESEQ");}
  @Override
  public Exp build(ExpList kids) {throw new Error("build() not applicable to ESEQ");}

    @Override
    public void accept(Visitor v, int d) {
        v.visit(this, d);
    }
}
