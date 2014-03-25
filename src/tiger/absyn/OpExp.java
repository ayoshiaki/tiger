package tiger.absyn;

import tiger.semant.ExpTy;
import tiger.types.INT;
import tiger.types.Type;

public class OpExp extends Exp {

    public Exp left, right;
    public int oper;

    public OpExp(int p, Exp l, int o, Exp r) {
        pos = p;
        left = l;
        oper = o;
        right = r;
    }
    public final static int PLUS = 0, MINUS = 1, MUL = 2, DIV = 3,
            EQ = 4, NE = 5, LT = 6, LE = 7, GT = 8, GE = 9;

    @Override
    public ExpTy transExp() {
        ExpTy left = this.left.transExp();
        ExpTy right = this.right.transExp();
        if (!left.isType(Type.INT)) {
            System.err.println("Deveria ser um inteiro na posição " + pos + "!");
        }
        if (!right.isType(Type.INT)) {
            System.err.print("Deveria ser um inteiro na posição " + pos + " !");
        }
        return new ExpTy(null, new INT());
    }
}
