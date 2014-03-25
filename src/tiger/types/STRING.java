package tiger.types;

public class STRING extends Type {
	public STRING(){}
	public boolean coerceTo(Type t) {return (t.actual() instanceof STRING);}

    @Override
    public boolean isType(int type) {
          return Type.STRING == type;
  }
}

