/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiger.absyn;

/**
 *
 * @author yoshiaki
 */
public interface Visitor {
    public void visit(VarDec e);
    
    public void visit(VarExp e);
    
    public void visit(ArrayExp e);

    public void visit(ArrayTy e);

    public void visit(AssignExp e);

    public void visit(BreakExp e);

    public void visit(CallExp e);

    public void visit(FieldExpList e);

    public void visit(FieldList e);

    public void visit(FieldVar e);

    public void visit(ForExp e);

    public void visit(FunctionDec e);

    public void visit(IfExp e);

    public void visit(IntExp e);

    public void visit(LetExp e);

    public void visit(NameTy e);

    public void visit(NilExp e);

    public void visit(OpExp e);

    public void visit(RecordExp e);

    public void visit(RecordTy e);

    public void visit(SeqExp e);

    public void visit(SimpleVar e);

    public void visit(StringExp e);

    public void visit(SubscriptVar e);

    public void visit(TypeDec e);

    public void visit(WhileExp e);


}
