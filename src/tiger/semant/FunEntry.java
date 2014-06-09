package tiger.semant;

import tiger.translate.Level;
import tiger.types.RECORD;
import tiger.types.Type;

public class FunEntry extends Entry {

    private RECORD formals;
    private Type result;
    private Level level;

    public FunEntry(RECORD f, Type r) {
        formals = f;
        result = r;
        level = null;
    }

    FunEntry(Level newLevel, RECORD fields, Type type) {
        level = newLevel;
        formals = fields;
        result = type;
    }

    @Override
    public int getEntryType() {
        return Entry.FUNENTRY;
    }

    /**
     * @return the formals
     */
    public RECORD getFormals() {
        return formals;
    }

    /**
     * @param formals the formals to set
     */
    public void setFormals(RECORD formals) {
        this.formals = formals;
    }

    /**
     * @return the result
     */
    public Type getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Type result) {
        this.result = result;
    }

    /**
     * @return the level
     */
    public Level getLevel() {
        return level;
    }
}
