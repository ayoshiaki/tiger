package tiger.mips;

class InFrame extends tiger.frame.Access {
  int offset;
  InFrame(int o) {
    offset = o;
  }

  public tiger.tree.Exp exp(tiger.tree.Exp fp) {
    return new tiger.tree.MEM
      (new tiger.tree.BINOP(tiger.tree.BINOP.PLUS, fp, new tiger.tree.CONST(offset)));
  }

  public String toString() {
    Integer offset = new Integer(this.offset);
    return offset.toString();
  }
}
