package tiger.absyn;

public class NilExp extends Exp {

    public NilExp(Position p) {
       setPosition(p);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
