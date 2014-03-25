package tiger.absyn;
import tiger.semant.Env;
import tiger.symbol.Symbol;
import tiger.types.ARRAY;
import tiger.types.NAME;
import tiger.types.Type;
import tiger.types.VOID;
public class ArrayTy extends Ty {
   public Symbol typ;
   public ArrayTy(int p, Symbol t) {pos=p; typ=t;}

    @Override
    public Type transTy() {
        NAME n = (NAME)Env.getEnv().getTenv().get(typ);
        if(n == null) {
                System.err.println("Tipo nao definido: " + typ);
                return new VOID();
        }               
        return  new ARRAY(n);
    }
}
