package tiger.mips;

import tiger.temp.Temp;

class InReg extends tiger.frame.Access {
  Temp temp;
  InReg(Temp t) {
    temp = t;
  }

  public tiger.tree.Exp exp(tiger.tree.Exp fp) {
    return new tiger.tree.TEMP(temp);
  }

  public String toString() {
    return temp.toString();
  }
}
