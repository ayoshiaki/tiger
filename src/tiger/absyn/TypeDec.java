package tiger.absyn;

import tiger.semant.Env;
import tiger.symbol.Symbol;

public class TypeDec extends Dec {

    public Symbol name;
    public Ty ty;
    public TypeDec next;

    public TypeDec(int p, Symbol n, Ty t, TypeDec x) {
        pos = p;
        name = n;
        ty = t;
        next = x;
    }

    @Override
    public Exp transDec() {
        Env.getEnv().getTenv().put(name, ty.transTy());
        return null;
    }
}
