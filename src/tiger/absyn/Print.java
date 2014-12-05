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
        prDec(e);
    }

    @Override
    public void visit(VarExp e) {
        prExp(e);
    }

    @Override
    public void visit(ArrayExp e) {
        prExp(e);
    }

    @Override
    public void visit(ArrayTy e) {
        prTy(e); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(AssignExp e) {
        prExp(e); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(BreakExp e) {
        prExp(e); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(CallExp e) {
        prExp(e);
    }

    @Override
    public void visit(FieldVar e) {
        prVar(e);
    }

    @Override
    public void visit(ForExp e) {
        prExp(e);
    }

    @Override
    public void visit(FunctionDec e) {
     prDec(e);
    }

    @Override
    public void visit(IfExp e) {
        prExp(e);
    }

    @Override
    public void visit(IntExp e) {
        prExp(e);
    }

    @Override
    public void visit(LetExp e) {
        prExp(e);
    }

    @Override
    public void visit(NameTy e) {
        prTy(e);
    }

    @Override
    public void visit(NilExp e) {
        prExp(e);
    }

    @Override
    public void visit(OpExp e) {
        prExp(e);
    }

    @Override
    public void visit(RecordExp e) {
       prExp(e);
    }

    @Override
    public void visit(RecordTy e) {
        prTy(e);
    }

    @Override
    public void visit(SeqExp e) {
        prExp(e);
    }

    @Override
    public void visit(SimpleVar e) {
        prVar(e, d);
    }

    @Override
    public void visit(StringExp e) {
       prExp(e); 
    }

    @Override
    public void visit(SubscriptVar e) {
        prVar(e);
    }

    @Override
    public void visit(TypeDec e) {
        prDec(e);
    }

    @Override
    public void visit(WhileExp e) {
        prExp(e);
    }

    @Override
    public void visit(Ty t) {
        t.accept(this);
    }
    
    java.io.PrintWriter out;

    public Print(java.io.PrintWriter o) {
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

    void prVar(FieldVar v) {
        sayln("FieldVar(");
        d += 1;
        prVar(v.var);
        d -= 1;
        sayln(",");
        indent(d += 1);
        d -= 1;
        say(v.field.toString());
        say(")");
    }

    void prVar(SubscriptVar v) {
        sayln("SubscriptVar(");
        d += 1;
        prVar(v.var);
        d -= 1;
        sayln(",");
        d += 1;
        prExp(v.index);
        d -= 1;
        say(")");
    }

    /* Print A_var types. Indent d spaces. */
    void prVar(Var v) {
        indent(d);
        try{
            v.accept(this);
        }catch(Exception c){
            throw new Error("Print.prVar");
        }
    }
    
    void prExp(OpExp e) {
        sayln("OpExp(");
        indent(d += 1);
        d -= 1;
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
        d += 1;
        prExp(e.left);
        d -= 1;
        sayln(",");
        d += 1;
        prExp(e.right);
        d -= 1;
        say(")");
    }

    void prExp(VarExp e) {
        sayln("varExp(");
        prVar(e.var);
        say(")");
    }

    void prExp(NilExp e) {
        say("NilExp()");
    }

    void prExp(IntExp e) {
        say("IntExp(");
        say(e.value);
        say(")");
    }

    void prExp(StringExp e) {
        say("StringExp(");
        say(e.value);
        say(")");
    }

    void prExp(CallExp e) {
        say("CallExp(");
        say(e.func.toString());
        sayln(",");
        prExplist(e.args, d += 1);
        d -= 1;
        say(")");
    }

    void prExp(RecordExp e) {
        say("RecordExp(");
        say(e.typ.toString());
        sayln(",");
        prFieldExpList(e.fields, d += 1);
        d -= 1;
        say(")");
    }

    void prExp(SeqExp e) {
        sayln("SeqExp(");
        prExplist(e.list, d += 1);
        d -= 1;
        say(")");
    }

    void prExp(AssignExp e) {
        sayln("AssignExp(");
        d += 1;
        prVar(e.var);
        d -= 1;
        sayln(",");
        d += 1;
        prExp(e.exp);
        d -= 1;
        say(")");
    }

    void prExp(IfExp e) {
        sayln("IfExp(");
        d += 1;
        prExp(e.test);
        d -= 1;
        sayln(",");
        d += 1;
        prExp(e.thenclause);
        d -= 1;
        if (e.elseclause != null) { /* else is optional */
            sayln(",");
            prExp(e.elseclause);
            d -= 1;
        }
        say(")");
    }

    void prExp(WhileExp e) {
        sayln("WhileExp(");
        d += 1;
        prExp(e.test);
        d -= 1;
        sayln(",");
        d += 1;
        prExp(e.body);
        d -= 1;
        sayln(")");
    }

    void prExp(ForExp e) {
        sayln("ForExp(");
        indent(d + 1);
        prDec(e.var);
        d -= 1;
        sayln(",");
        d += 1;
        prExp(e.hi);
        d -= 1;
        sayln(",");
        d += 1;
        prExp(e.body);
        d -= 1;
        say(")");
    }

    void prExp(BreakExp e) {
        say("BreakExp()");
    }

    void prExp(LetExp e) {
        say("LetExp(");
        sayln("");
        prDecList(e.decs, d += 1);
        d -= 1;
        sayln(",");
        d += 1;
        prExp(e.body);
        d -= 1;
        say(")");
    }

    void prExp(ArrayExp e) {
        say("ArrayExp(");
        say(e.typ.toString());
        sayln(",");
        d += 1;
        prExp(e.size);
        d -= 1;
        sayln(",");
        d += 1;
        prExp(e.init);
        d -= 1;
        say(")");
    }

    /* Print Exp class types. Indent d spaces. */
    public void prExp(Exp e) {
        indent(d);
        try{ 
        e.accept(this);
        }catch (Exception c){
            throw new Error("Print.prExp");
        }
    }
    
    void prDec(FunctionDec d) {
        say("FunctionDec(");
        if (d != null) {
            sayln(d.name.toString());
            prFieldlist(d.params, this.d += 1);
            this.d -= 1;
            sayln(",");
            if (d.result != null) {
                indent(this.d += 1);
                this.d -= 1;
                sayln(d.result.name.toString());
            }
            prExp(d.body);
            sayln(",");
            indent(this.d += 1);
            this.d -= 1;
            prDec(d.next);
            this.d -= 1;
        }
        say(")");
    }

    void prDec(VarDec d) {
        say("VarDec(");
        say(d.name.toString());
        sayln(",");
        if (d.typ != null) {
            indent(this.d += 1);
            this.d -= 1;
            say(d.typ.name.toString());
            sayln(",");
        }
        prExp(d.init);
        sayln(",");
        indent(this.d += 1);
        this.d -= 1;
        say(d.escape);
        say(")");
    }

    void prDec(TypeDec d) {
        if (d != null) {
            say("TypeDec(");
            say(d.name.toString());
            sayln(",");
            prTy(d.ty);
            this.d -= 1;
            if (d.next != null) {
                say(",");
                prDec(d.next);
                this.d -= 1;
            }
            say(")");
        }
    }

    public void prDec(Dec d) {
        indent(this.d);
        try{
            d.accept(this);
        } catch (Exception c){
            throw new Error("Print.prDec");
        }
    }
    
    void prTy(NameTy t) {
        say("NameTy(");
        say(t.name.toString());
        say(")");
    }

    void prTy(RecordTy t) {
        sayln("RecordTy(");
        prFieldlist(t.fields, d += 1);
        d -= 1;
        say(")");
    }

    void prTy(ArrayTy t) {
        say("ArrayTy(");
        say(t.typ.toString());
        say(")");
    }

    void prTy(Ty t) {
        if (t != null) {
            indent(d);
            try{
             t.accept(this);
            } catch (Exception c) {
                throw new Error("Print.prTy");
            }
        }
    }

    void prFieldlist(FieldList f, int d) {
        indent(this.d);
        say("Fieldlist(");
        if (f != null) {
            sayln("");
            indent(this.d += 1);
            this.d -= 1;
            say(f.name.toString());
            sayln("");
            indent(this.d += 1);
            this.d -= 1;
            say(f.typ.toString());
            sayln(",");
            indent(this.d += 1);
            this.d -= 1;
            say(f.escape);
            sayln(",");
            prFieldlist(f.tail, this.d += 1);
            this.d -= 1;
        }
        say(")");
    }

    void prExplist(ExpList e, int d) {
        indent(this.d);
        say("ExpList(");
        if (e != null) {
            sayln("");
            prExp(e.head);
            if (e.tail != null) {
                sayln(",");
                prExplist(e.tail, this.d += 1);
                this.d -= 1;
            }
        }
        say(")");
    }

    void prDecList(DecList v, int d) {
        indent(this.d);
        say("DecList(");
        if (v != null) {
            sayln("");
            prDec(v.head);
            sayln(",");
            prDecList(v.tail, this.d += 1);
            this.d -= 1;
        }
        say(")");
    }

    void prFieldExpList(FieldExpList f, int d) {
        indent(this.d);
        say("FieldExpList(");
        if (f != null) {
            sayln("");
            say(f.name.toString());
            sayln(",");
            prExp(f.init);
            sayln(",");
            prFieldExpList(f.tail, this.d += 1);
            this.d -= 1;
        }
        say(")");
    }
}
