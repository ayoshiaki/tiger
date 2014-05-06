package tiger.absyn;

public class StringExp extends Exp {

    public String value;

    public StringExp(Position p, String v) {
       setPosition(p);
        value = v;
    }
        @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
