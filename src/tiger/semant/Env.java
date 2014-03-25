package tiger.semant;
import tiger.symbol.Table;
import tiger.symbol.Symbol;
import tiger.types.INT;

import tiger.types.NAME;
public class Env {
    private Table venv;
    private Table tenv;
    private static Env singleton;
    
    public static Env getEnv(){
        if (singleton == null){
            singleton  = new Env();
        }
        return singleton;
    }
    
    private Env() {
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
