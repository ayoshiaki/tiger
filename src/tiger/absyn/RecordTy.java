package tiger.absyn;

public class RecordTy extends Ty {

    public FieldList fields;

    public RecordTy(Position p, FieldList f) {
        setPosition(p);
        fields = f;
    }
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

   
}
