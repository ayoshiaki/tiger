/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiger.tree;

/**
 *
 * @author Mollon
 */
public interface Visitor {
    
     public void visit(BINOP var,int d);
     
     public void visit(CALL var, int d);
     
     public void visit(CJUMP var, int d);
     
     public void visit(CONST var,int d);
     
     public void visit(ESEQ var, int d);
     
     public void visit(EXPR var, int d);
     
     public void visit(Exp var,  int d);
     
     public void visit(JUMP var, int d);
     
     public void visit(LABEL var, int d);
     
     public void visit(MEM var, int d);
     
     public void visit(MOVE var, int d);
     
     public void visit(NAME var, int d);
     
     public void visit(SEQ var, int d);
     
     public void visit(Stm var, int d);
              
     public void visit(TEMP var, int d);
     
     public void visit(UEXP var, int d);
    
    
}
