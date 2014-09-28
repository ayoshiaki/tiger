/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiger.absyn;

/**
 *
 * @author Daniel
 */
public class Print implements Visitor{
    
    int d=0;
    int i=0;
    
    @Override
    public void visit(Absyn e) {
        e.accept(this);
    }

    @Override
    public void visit(Exp e) {
        e.accept(this);
    }

    @Override
    public void visit(Dec d) {
        d.accept(this);
    }

    @Override
    public void visit(VarDec e) {
        prDec(e, i);
    }

    @Override
    public void visit(VarExp e) {
        prExp(e, d);
    }

    @Override
    public void visit(ArrayExp e) {
        prExp(e, d);
    }

    @Override
    public void visit(ArrayTy e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(AssignExp e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(BreakExp e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(CallExp e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(FieldVar e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(ForExp e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(FunctionDec e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(IfExp e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(IntExp e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(LetExp e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(NameTy e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(NilExp e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(OpExp e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(RecordExp e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(RecordTy e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(SeqExp e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(SimpleVar e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(StringExp e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(SubscriptVar e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(TypeDec e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(WhileExp e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Ty t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    java.io.PrintStream out;

    public Print(java.io.PrintStream o) {
        out = o;
    }

    void indent(int d) {
        for (int i = 0; i < d; i++) {
            out.print(' ');
        }
    }

    void say(String s) {
        out.print(s);
    }

    void say(int i) {
        Integer local = new Integer(i);
        out.print(local.toString());
    }

    void say(boolean b) {
        Boolean local = new Boolean(b);
        out.print(local.toString());
    }

    void sayln(String s) {
        say(s);
        say("\n");
    }
    
    void prVar(SimpleVar v, int d) {
        say("SimpleVar(");
        say(v.name.toString());
        say(")");
    }

    void prVar(FieldVar v, int d) {
        sayln("FieldVar(");
        prVar(v.var, d + 1);
        sayln(",");
        indent(d + 1);
        say(v.field.toString());
        say(")");
    }

    void prVar(SubscriptVar v, int d) {
        sayln("SubscriptVar(");
        prVar(v.var, d + 1);
        sayln(",");
        prExp(v.index, d + 1);
        say(")");
    }

    /* Print A_var types. Indent d spaces. */
    void prVar(Var v, int d) {
        indent(d);
        try{
            v.accept(this);
        }catch(Exception c){
            throw new Error("Print.prVar");
        }
    }
    
    void prExp(OpExp e, int d) {
        sayln("OpExp(");
        indent(d + 1);
        switch (e.oper) {
            case OpExp.PLUS:
                say("PLUS");
                break;
            case OpExp.MINUS:
                say("MINUS");
                break;
            case OpExp.MUL:
                say("MUL");
                break;
            case OpExp.DIV:
                say("DIV");
                break;
            case OpExp.EQ:
                say("EQ");
                break;
            case OpExp.NE:
                say("NE");
                break;
            case OpExp.LT:
                say("LT");
                break;
            case OpExp.LE:
                say("LE");
                break;
            case OpExp.GT:
                say("GT");
                break;
            case OpExp.GE:
                say("GE");
                break;
            default:
                throw new Error("Print.prExp.OpExp");
        }
        sayln(",");
        prExp(e.left, d + 1);
        sayln(",");
        prExp(e.right, d + 1);
        say(")");
    }

    void prExp(VarExp e, int d) {
        sayln("varExp(");
        prVar(e.var, d + 1);
        say(")");
    }

    void prExp(NilExp e, int d) {
        say("NilExp()");
    }

    void prExp(IntExp e, int d) {
        say("IntExp(");
        say(e.value);
        say(")");
    }

    void prExp(StringExp e, int d) {
        say("StringExp(");
        say(e.value);
        say(")");
    }

    void prExp(CallExp e, int d) {
        say("CallExp(");
        say(e.func.toString());
        sayln(",");
        prExplist(e.args, d + 1);
        say(")");
    }

    void prExp(RecordExp e, int d) {
        say("RecordExp(");
        say(e.typ.toString());
        sayln(",");
        prFieldExpList(e.fields, d + 1);
        say(")");
    }

    void prExp(SeqExp e, int d) {
        sayln("SeqExp(");
        prExplist(e.list, d + 1);
        say(")");
    }

    void prExp(AssignExp e, int d) {
        sayln("AssignExp(");
        prVar(e.var, d + 1);
        sayln(",");
        prExp(e.exp, d + 1);
        say(")");
    }

    void prExp(IfExp e, int d) {
        sayln("IfExp(");
        prExp(e.test, d + 1);
        sayln(",");
        prExp(e.thenclause, d + 1);
        if (e.elseclause != null) { /* else is optional */
            sayln(",");
            prExp(e.elseclause, d + 1);
        }
        say(")");
    }

    void prExp(WhileExp e, int d) {
        sayln("WhileExp(");
        prExp(e.test, d + 1);
        sayln(",");
        prExp(e.body, d + 1);
        sayln(")");
    }

    void prExp(ForExp e, int d) {
        sayln("ForExp(");
        indent(d + 1);
        prDec(e.var, d + 1);
        sayln(",");
        prExp(e.hi, d + 1);
        sayln(",");
        prExp(e.body, d + 1);
        say(")");
    }

    void prExp(BreakExp e, int d) {
        say("BreakExp()");
    }

    void prExp(LetExp e, int d) {
        say("LetExp(");
        sayln("");
        prDecList(e.decs, d + 1);
        sayln(",");
        prExp(e.body, d + 1);
        say(")");
    }

    void prExp(ArrayExp e, int d) {
        say("ArrayExp(");
        say(e.typ.toString());
        sayln(",");
        prExp(e.size, d + 1);
        sayln(",");
        prExp(e.init, d + 1);
        say(")");
    }

    /* Print Exp class types. Indent d spaces. */
    public void prExp(Exp e, int d) {
        indent(d);
        try{ 
        e.accept(this);
        }catch (Exception c){
            throw new Error("Print.prExp");
        }
    }
    
    void prDec(FunctionDec d, int i) {
        say("FunctionDec(");
        if (d != null) {
            sayln(d.name.toString());
            prFieldlist(d.params, i + 1);
            sayln(",");
            if (d.result != null) {
                indent(i + 1);
                sayln(d.result.name.toString());
            }
            prExp(d.body, i + 1);
            sayln(",");
            indent(i + 1);
            prDec(d.next, i + 1);
        }
        say(")");
    }

    void prDec(VarDec d, int i) {
        say("VarDec(");
        say(d.name.toString());
        sayln(",");
        if (d.typ != null) {
            indent(i + 1);
            say(d.typ.name.toString());
            sayln(",");
        }
        prExp(d.init, i + 1);
        sayln(",");
        indent(i + 1);
        say(d.escape);
        say(")");
    }

    void prDec(TypeDec d, int i) {
        if (d != null) {
            say("TypeDec(");
            say(d.name.toString());
            sayln(",");
            prTy(d.ty, i + 1);
            if (d.next != null) {
                say(",");
                prDec(d.next, i + 1);
            }
            say(")");
        }
    }

    public void prDec(Dec d, int i) {
        indent(i);
        try{
            d.accept(this);
        } catch (Exception c){
            throw new Error("Print.prDec");
        }
    }    
    
}
