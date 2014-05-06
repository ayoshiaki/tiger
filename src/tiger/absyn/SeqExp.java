package tiger.absyn;

public class SeqExp extends Exp {

    public ExpList list;

    public SeqExp(Position p, ExpList l) {
        setPosition(p);
        list = l;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
