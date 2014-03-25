package tiger.types;

import tiger.symbol.*;

public class RECORD extends Type {
   public Symbol fieldName;
   public Type fieldType;
   public RECORD tail;
   public RECORD(Symbol n, Type t, RECORD x) {
       fieldName=n; fieldType=t; tail=x;
   }
   public boolean coerceTo(Type t) {
	return this==t.actual();
   }

    @Override
    public boolean isType(int type) {
         return Type.RECORD == type;
   }
}
   

