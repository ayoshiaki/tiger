package tiger.absyn;

public class OpExp extends Exp {

    public Exp left, right;
    public int oper;

    public OpExp(Position p, Exp l, int o, Exp r) {
        setPosition(p);
        left = l;
        oper = o;
        right = r;
    }
    public final static int PLUS = 0, MINUS = 1, MUL = 2, DIV = 3,
            EQ = 4, NE = 5, LT = 6, LE = 7, GT = 8, GE = 9;

        @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
