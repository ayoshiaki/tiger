package tiger.semant;

import tiger.types.RECORD;
import tiger.types.Type;

public class FunEntry extends Entry {

    RECORD formals;
    Type result;

    public FunEntry(RECORD f, Type r) {
        formals = f;
        result = r;
    }

    @Override
    public int getEntryType() {
        return Entry.FUNENTRY;
    }
}
