package tiger.absyn;

import tiger.symbol.Symbol;

public class FieldExpList extends Absyn {
   public Symbol name;
   public Exp init;
   public FieldExpList tail;
   public FieldExpList(Position p, Symbol n, Exp i, FieldExpList t) {setPosition(p);
	name=n; init=i; tail=t;
   }
}
