package tiger.assem;

import tiger.temp.Label;
import tiger.temp.TempList;

public class LABEL extends Instr {
   public Label label;

   public LABEL(String a, Label l) {
      assem=a; label=l;
   }

   public TempList use() {return null;}
   public TempList def() {return null;}
   public Targets jumps()     {return null;}

}
