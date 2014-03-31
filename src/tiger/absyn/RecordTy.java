package tiger.absyn;

import tiger.semant.Env;
import tiger.types.Type;

public class RecordTy extends Ty {

    public FieldList fields;

    public RecordTy(Position p, FieldList f) {
        setPosition(p);
        fields = f;
    }

   
}
