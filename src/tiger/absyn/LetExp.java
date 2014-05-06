package tiger.absyn;

public class LetExp extends Exp {

    public DecList decs;
    public Exp body;

    public LetExp(Position p, DecList d, Exp b) {
        setPosition(p);
        decs = d;
        body = b;
    }
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }


}
