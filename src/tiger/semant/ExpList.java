package tiger.semant;

import tiger.translate.Exp;
/**
 *
 * @author yoshiaki
 */
public class ExpList {
   public Exp head;
   public ExpList tail;
   public ExpList(Exp h, ExpList t) {head=h; tail=t;}
}