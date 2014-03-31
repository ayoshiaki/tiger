package tiger.absyn;

import tiger.symbol.Symbol;

public class FieldList extends Absyn {
   public Symbol name;
   public Symbol typ;
   public FieldList tail;
   public boolean escape = true;
   public FieldList(Position p, Symbol n, Symbol t, FieldList x) {setPosition(p); name=n; typ=t; tail=x;}
}
