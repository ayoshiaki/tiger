package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.VarEntry;
import tiger.symbol.Symbol;

public class VarDec extends Dec {

    public Symbol name;
    public boolean escape = true;
    public NameTy typ; /* optional */

    public Exp init;

    public VarDec(int p, Symbol n, NameTy t, Exp i) {
        pos = p;
        name = n;
        typ = t;
        init = i;
    }

    @Override
    public Exp transDec() {
        System.out.println("Declarado variável " + name);
        Env.getEnv().getVenv().put(name, new VarEntry(init.transExp().getTy()));
        return null;
    }
}
