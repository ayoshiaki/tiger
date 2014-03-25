package tiger.absyn;

import tiger.types.Type;

public class RecordTy extends Ty {

    public FieldList fields;

    public RecordTy(int p, FieldList f) {
        pos = p;
        fields = f;
    }

    @Override
    public Type transTy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
