package tiger.types;

public class INT extends Type {
	public INT () {}
	public boolean coerceTo(Type t) {return (t != null && (t.actual().isType(Type.INT) || t.actual().isType(Type.STRING)));}

    @Override
    public boolean isType(int type) {
          return Type.INT == type;
   }
}

