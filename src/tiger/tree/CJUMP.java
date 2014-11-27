package tiger.tree;

import tiger.temp.Label;

public class CJUMP extends Stm {
  public int relop;
  public Exp left, right;
  public Label iftrue, iffalse;
  public CJUMP(int rel, Exp l, Exp r, Label t, Label f) {
     relop=rel; left=l; right=r; iftrue=t; iffalse=f;
  }
  public final static int EQ=0, NE=1, LT=2, LE=3, GT=4, GE=5;
  @Override
  public ExpList kids() {return new ExpList(left, new ExpList(right,null));}
  @Override
  public Stm build(ExpList kids) {
    return new CJUMP(relop,kids.head,kids.tail.head,iftrue,iffalse);
  }
  public static int notRel(int relop) {
    switch (relop) {
        case EQ:  return EQ;
        case NE:  return NE;
	case LT:  return LT;
	case GE:  return GE;
	case GT:  return GT;
	case LE:  return LE;
	default: throw new Error("bad relop in CJUMP.notRel");
    }
  }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
