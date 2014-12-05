package tiger.semant;

import tiger.symbol.Table;
import tiger.symbol.Symbol;

import tiger.types.NAME;
import tiger.types.RECORD;
import tiger.types.Type;
import tiger.types.VOID;

public class Env {

    private Table venv;
    private Table tenv;
    private static final VOID VOID = SemantVisitor.VOID;

    private static RECORD RECORD(Symbol n, Type t, RECORD x) {
        return new RECORD(n, t, x);
    }

    private static RECORD RECORD(Symbol n, Type t) {
        return new RECORD(n, t, null);
    }

    private static FunEntry FunEntry(RECORD f, Type r) {
        return new FunEntry(f, r);
    }

    private static Symbol sym(String s) {
        return Symbol.symbol(s);
    }

    public Env() {
        venv = new Table();
        tenv = new Table();
        // initialize tenv and venv with predefined identifiers
        NAME INT = new NAME(sym("int"));
        NAME FLOAT = new NAME(sym("float"));
        INT.bind(SemantVisitor.INT);
        FLOAT.bind(SemantVisitor.FLOAT);
        tenv.put(sym("int"), INT);
        tenv.put(sym("float"), FLOAT);
        NAME STRING = new NAME(sym("string"));
        STRING.bind(SemantVisitor.STRING);
        tenv.put(sym("string"), STRING);
        
        
        venv.put(sym("printi"), FunEntry(RECORD(sym("i"), INT), VOID));
        venv.put(sym("printfloat"), FunEntry(RECORD(sym("f"), FLOAT), VOID));
        venv.put(sym("print"), FunEntry(RECORD(sym("s"), STRING), VOID));
        venv.put(sym("flush"), FunEntry(null, VOID));
        venv.put(sym("getchar"), FunEntry(null, STRING));
        venv.put(sym("ord"), FunEntry(RECORD(sym("s"), STRING), INT));
        venv.put(sym("chr"), FunEntry(RECORD(sym("i"), INT), STRING));
        venv.put(sym("size"), FunEntry(RECORD(sym("s"), STRING), INT));
        venv.put(sym("substring"), FunEntry(RECORD(sym("s"), STRING,
                RECORD(sym("first"), INT,
                RECORD(sym("n"), INT))),
                STRING));
        venv.put(sym("concat"), FunEntry(RECORD(sym("s1"), STRING,
                RECORD(sym("s2"), STRING)),
                STRING));
        venv.put(sym("not"), FunEntry(RECORD(sym("i"), INT), INT));
        venv.put(sym("exit"), FunEntry(RECORD(sym("i"), INT), VOID));

    }

    /**
     * @return the venv
     */
    public Table getVenv() {
        return venv;
    }

    /**
     * @param venv the venv to set
     */
    public void setVenv(Table venv) {
        this.venv = venv;
    }

    /**
     * @return the tenv
     */
    public Table getTenv() {
        return tenv;
    }

    /**
     * @param tenv the tenv to set
     */
    public void setTenv(Table tenv) {
        this.tenv = tenv;
    }
}
