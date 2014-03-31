package tiger.semant;
import tiger.symbol.Table;
import tiger.symbol.Symbol;
import tiger.types.INT;

import tiger.types.NAME;
public class Env {
    public Table venv;
    public Table tenv;
   
    public Env() {
        venv = new Table();
        tenv = new Table();
        NAME n = new NAME(Symbol.symbol("int"));
        n.bind(new INT());
        tenv.put(Symbol.symbol("int"),n);
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
