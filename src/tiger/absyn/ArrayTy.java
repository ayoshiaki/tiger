package tiger.absyn;
import tiger.semant.Env;
import tiger.symbol.Symbol;
import tiger.types.ARRAY;
import tiger.types.NAME;
import tiger.types.Type;
import tiger.types.VOID;
public class ArrayTy extends Ty {
   public Symbol typ;
   public ArrayTy(Position p, Symbol t) {setPosition(p); typ=t;}

  
}
