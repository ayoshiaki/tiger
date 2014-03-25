package tiger.types;

public class INT extends Type {
	public INT () {}
	public boolean coerceTo(Type t) {return (t.actual() instanceof INT);}

    @Override
    public boolean isType(int type) {
          return Type.INT == type;
   }
}

