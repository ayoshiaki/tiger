package tiger.types;
import tiger.symbol.*;
public class NAME extends Type {
   public Symbol name;
   private Type binding;
   public NAME(Symbol n) {name=n;}
   public boolean isLoop() {
      Type b = binding; 
      boolean any;
      binding=null;
      if (b==null) any=true;
      else if (b instanceof NAME)
            any=((NAME)b).isLoop();
      else any=false;
      binding=b;
      return any;
     }
     
   public Type actual() {return binding.actual();}

   public boolean coerceTo(Type t) {
	return this.actual().coerceTo(t);
   }
   public void bind(Type t) {binding = t;}

    @Override
    public boolean isType(int type) {
         return Type.NAME == type;
   }
}
