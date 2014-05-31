package tiger.canon;

import tiger.temp.Label;
import tiger.tree.CJUMP;
import tiger.tree.JUMP;
import tiger.tree.LABEL;
import tiger.tree.Stm;
import tiger.tree.StmList;

public class TraceSchedule {

  public StmList stms;
  BasicBlocks theBlocks;
  java.util.Dictionary table = new java.util.Hashtable();

  StmList getLast(StmList block) {
     StmList l=block;
     while (l.tail.tail!=null)  l=l.tail;
     return l;
  }

  void trace(StmList l) {
   for(;;) {
     LABEL lab = (LABEL)l.head;
     table.remove(lab.label);
     StmList last = getLast(l);
     Stm s = last.tail.head;
     if (s instanceof JUMP) {
	JUMP j = (JUMP)s;
        StmList target = (StmList)table.get(j.targets.head);
	if (j.targets.tail==null && target!=null) {
               last.tail=target;
	       l=target;
        }
	else {
	  last.tail.tail=getNext();
	  return;
        }
     }
     else if (s instanceof CJUMP) {
	CJUMP j = (CJUMP)s;
        StmList t = (StmList)table.get(j.iftrue);
        StmList f = (StmList)table.get(j.iffalse);
        if (f!=null) {
	  last.tail.tail=f; 
	  l=f;
	}
        else if (t!=null) {
	  last.tail.head=new CJUMP(CJUMP.notRel(j.relop),
					j.left,j.right,
					j.iffalse,j.iftrue);
	  last.tail.tail=t;
	  l=t;
        }
        else {
	  Label ff = new Label();
	  last.tail.head=new CJUMP(j.relop,j.left,j.right,
					j.iftrue,ff);
	  last.tail.tail=new StmList(new LABEL(ff),
		           new StmList(new JUMP(j.iffalse),
					    getNext()));
	  return;
        }
     }
     else throw new Error("Bad basic block in TraceSchedule");
    }
  }

  StmList getNext() {
      if (theBlocks.blocks==null) 
	return new StmList(new LABEL(theBlocks.done), null);
      else {
	 StmList s = theBlocks.blocks.head;
	 LABEL lab = (LABEL)s.head;
	 if (table.get(lab.label) != null) {
          trace(s);
	  return s;
         }
         else {
	   theBlocks.blocks = theBlocks.blocks.tail;
           return getNext();
         }
      }
  }

  public TraceSchedule(BasicBlocks b) {
    theBlocks=b;
    for(StmListList l = b.blocks; l!=null; l=l.tail)
       table.put(((LABEL)l.head.head).label, l.head);
    stms=getNext();
    table=null;
  }        
}


