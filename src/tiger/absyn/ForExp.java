package tiger.absyn;

public class ForExp extends Exp {

    public VarDec var;
    public Exp hi, body;

    public ForExp(Position p, VarDec v, Exp h, Exp b) {
        setPosition(p);
        var = v;
        hi = h;
        body = b;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
