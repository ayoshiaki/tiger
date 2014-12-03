package tiger.types;

public class FLOAT extends Type {
	public FLOAT () {}
	public boolean coerceTo(Type t) {return (t != null && t.actual().isType(Type.FLOAT));}

    @Override
    public boolean isType(int type) {
          return Type.FLOAT == type;
   }
}

