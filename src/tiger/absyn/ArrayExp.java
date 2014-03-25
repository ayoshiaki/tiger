package tiger.absyn;

import tiger.semant.Env;
import tiger.semant.ExpTy;
import tiger.symbol.Symbol;
import tiger.types.ARRAY;
import tiger.types.INT;
import tiger.types.NAME;
import tiger.types.Type;
import tiger.types.VOID;

public class ArrayExp extends Exp {

    public Symbol typ;
    public Exp size, init;

    public ArrayExp(int p, Symbol t, Exp s, Exp i) {
        pos = p;
        typ = t;
        size = s;
        init = i;
    }

    @Override
    public ExpTy transExp() {
        NAME name = (NAME) Env.getEnv().getTenv().get(typ);
        ExpTy size = this.size.transExp();
        ExpTy init = this.init.transExp();
        if (!size.isType(Type.INT)) {
            System.err.println("O tamanho do vetor deve ser um numero inteiro !");
        }
        if (name != null) {
            Type actual = name.actual();
            if (actual.isType(Type.ARRAY)) {
                ARRAY array = (ARRAY) actual;
                if (!init.getTy().coerceTo(array.element)) {
                    System.err.println("Tipo incompativel !");
                }
                return new ExpTy(null, name);
            } else {
                System.err.println("Pos: " + pos  + " requer tipo array");
            }
        } else {
            System.err.println("Tipo nao declarado: " + typ);
        }
        return new ExpTy(null,new VOID());

    }
}
