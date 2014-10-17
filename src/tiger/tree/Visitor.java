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
    
     public void visit(BINOP var);
     
     public void visit(CALL var);
     
     public void visit(CJUMP var);
     
     public void visit(CONST var);
     
     public void visit(ESEQ var);
     
     public void visit(EXPR var);
     
     public void visit(Exp var);
     
     public void visit(JUMP var);
     
     public void visit(LABEL var);
     
     public void visit(MEM var);
     
     public void visit(MOVE var);
     
     public void visit(NAME var);
     
     public void visit(SEQ var);
     
     public void visit(Stm var);
              
     public void visit(TEMP var);
     
     public void visit(UEXP var);
    
    
}
