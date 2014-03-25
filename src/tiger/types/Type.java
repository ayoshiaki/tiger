package tiger.types;

import tiger.semant.ExpTy;

public abstract class Type {
    
   public static int ARRAY = 1, INT =2, NAME=3, NIL=4, RECORD=5, VOID=6, STRING=7;
    
   public Type actual() {return this;}
         
   public boolean coerceTo(Type t) {return false;}
   
   abstract public boolean isType(int type);
   
  public static boolean checkInt(ExpTy ty){
      return ty.getTy().isType(INT);
    
  }
}

