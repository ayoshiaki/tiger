package tiger.types;

public class NIL extends Type {
	public NIL () {}
	public boolean coerceTo(Type t) {
	    Type a = t.actual();
	    return (a instanceof RECORD) || (a instanceof NIL);
        }

    @Override
    public boolean isType(int type) {
           return Type.NIL == type;
 }
}

