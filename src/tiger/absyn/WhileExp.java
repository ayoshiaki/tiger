package tiger.absyn;

public class WhileExp extends Exp {

    public Exp test, body;

    public WhileExp(Position p, Exp t, Exp b) {
        setPosition(p);
        test = t;
        body = b;
    }
        @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
