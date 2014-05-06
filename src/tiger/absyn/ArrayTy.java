package tiger.absyn;
import tiger.symbol.Symbol;
public class ArrayTy extends Ty {
   public Symbol typ;
   public ArrayTy(Position p, Symbol t) {setPosition(p); typ=t;}
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

  
}
