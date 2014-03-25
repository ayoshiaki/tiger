package tiger.types;

public class VOID extends Type {
	public VOID () {}
	public boolean coerceTo(Type t) {return (t.actual() instanceof VOID);}

    @Override
    public boolean isType(int type) {
          return Type.VOID == type;
  }
}
