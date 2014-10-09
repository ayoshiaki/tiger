package tiger.tree;

import tiger.temp.DefaultMap;
import tiger.temp.TempMap;

public class Print implements Visitor {

    java.io.PrintWriter out;
    TempMap tmap;

    @Override
    public void visit(BINOP var, int d) {
        prExp(var, d);
    }

    @Override
    public void visit(CALL var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(CJUMP var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(CONST var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(ESEQ var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(EXPR var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Exp var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(JUMP var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(LABEL var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(MEM var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(MOVE var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(NAME var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(SEQ var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Stm var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(TEMP var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(UEXP var, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Print(java.io.PrintWriter o, TempMap t) {
        out = o;
        tmap = t;
    }

    public Print(java.io.PrintWriter o) {
        out = o;
        tmap = new DefaultMap();
    }

    void indent(int d) {
        for (int i = 0; i < d; i++) {
            out.print(' ');
        }
    }

    void say(String s) {
        out.print(s);
    }

    void sayln(String s) {
        out.println(s);
    }

    void prStm(SEQ s, int d) {
        indent(d);
        sayln("SEQ(");
        prStm(s.left, d + 1);
        sayln(",");
        prStm(s.right, d + 1);
        say(")");
    }

    void prStm(LABEL s, int d) {
        indent(d);
        say("LABEL ");
        say(s.label.toString());
    }

    void prStm(JUMP s, int d) {
        indent(d);
        sayln("JUMP(");
        prExp(s.exp, d + 1);
        say(")");
    }

    void prStm(CJUMP s, int d) {
        indent(d);
        say("CJUMP(");
        switch (s.relop) {
            case CJUMP.EQ:
                say("EQ");
                break;
            case CJUMP.NE:
                say("NE");
                break;
            case CJUMP.LT:
                say("LT");
                break;
            case CJUMP.GT:
                say("GT");
                break;
            case CJUMP.LE:
                say("LE");
                break;
            case CJUMP.GE:
                say("GE");
                break;
            default:
                throw new Error("Print.prStm.CJUMP");
        }
        sayln(",");
        prExp(s.left, d + 1);
        sayln(",");
        prExp(s.right, d + 1);
        sayln(",");
        indent(d + 1);
        say(s.iftrue.toString());
        say(",");
        say(s.iffalse.toString());
        say(")");
    }

    void prStm(MOVE s, int d) {
        indent(d);
        sayln("MOVE(");
        prExp(s.dst, d + 1);
        sayln(",");
        prExp(s.src, d + 1);
        say(")");
    }

    void prStm(EXPR s, int d) {
        indent(d);
        sayln("EXP(");
        prExp(s.exp, d + 1);
        say(")");
    }
    
    void prStm(Stm s, int d) {
        try {
            s.accept(this, d);
        } catch (Exception e) {
            throw new Error("Print.prStm");
        }
    }
}
