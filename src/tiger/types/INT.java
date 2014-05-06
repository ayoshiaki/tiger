package tiger.types;

public class INT extends Type {
	public INT () {}
	public boolean coerceTo(Type t) {return (t != null && t.actual().isType(Type.INT));}

    @Override
    public boolean isType(int type) {
          return Type.INT == type;
   }
}

