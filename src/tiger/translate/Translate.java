package tiger.translate;

import java.io.PrintWriter;
import tiger.frame.Frame;
import tiger.symbol.Symbol;
import tiger.temp.Label;
import tiger.temp.Temp;
import tiger.tree.BINOP;
import tiger.tree.CALL;
import tiger.tree.CJUMP;
import tiger.tree.CONST;
import tiger.tree.ESEQ;
import tiger.tree.EXPR;
import tiger.tree.JUMP;
import tiger.tree.LABEL;
import tiger.tree.MEM;
import tiger.tree.NAME;
import tiger.tree.SEQ;
import tiger.tree.Stm;
import tiger.tree.TEMP;
import tiger.tree.ExpList;
import tiger.tree.Exp;

public class Translate {

    public Frame frame;

    public Translate(Frame f) {
        frame = f;
    }
    private Frag frags;

    public void procEntryExit(Level level, tiger.translate.Exp body) {
        Frame myframe = level.frame;
        tiger.tree.Exp bodyExp = body.unEx();
        tiger.tree.Stm bodyStm;
        if (bodyExp != null) {
            bodyStm = MOVE(TEMP(myframe.RV()), bodyExp);
        } else {
            bodyStm = body.unNx();
        }
        tiger.tree.Print printTree = new tiger.tree.Print(new PrintWriter(System.err,true));
        printTree.prStm(bodyStm);
        ProcFrag frag = new ProcFrag(myframe.procEntryExit1(bodyStm), myframe);
        frag.next = frags;
        frags = frag;
    }

    public Frag getResult() {
        return frags;
    }

    private static tiger.tree.Exp CONST(int value) {
        return new CONST(value);
    }

    private static Exp NAME(Label label) {
        return new NAME(label);
    }

    private static Exp TEMP(Temp temp) {
        return new TEMP(temp);
    }

    private static Exp BINOP(int binop, Exp left, Exp right) {
        return new BINOP(binop, left, right);
    }

    private static Exp MEM(Exp exp) {
        return new MEM(exp);
    }

    private static Exp CALL(tiger.tree.Exp func, tiger.tree.ExpList args) {
        return new CALL(func, args);
    }

    private static Exp ESEQ(Stm stm, Exp exp) {
        if (stm == null) {
            return exp;
        }
        return new ESEQ(stm, exp);
    }

    private static Stm MOVE(tiger.tree.Exp dst, tiger.tree.Exp src) {
        return new tiger.tree.MOVE(dst, src);
    }

    private static Stm EXPR(Exp exp) {
        return new EXPR(exp);
    }

    private static Stm JUMP(Label target) {
        return new JUMP(target);
    }

    private static Stm CJUMP(int relop, Exp l, Exp r, Label t,
            Label f) {
        return new CJUMP(relop, l, r, t, f);
    }

    private static Stm SEQ(Stm left, Stm right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return new SEQ(left, right);
    }

    private static Stm LABEL(Label label) {
        return new LABEL(label);
    }

    private static tiger.tree.ExpList ExpList(tiger.tree.Exp head, tiger.tree.ExpList tail) {
        return new ExpList(head, tail);
    }

    private static tiger.tree.ExpList ExpList(tiger.tree.Exp head) {
        return ExpList(head, null);
    }

    private static tiger.tree.ExpList ExpList(tiger.translate.ExpList exp) {
        if (exp == null) {
            return null;
        }
        return ExpList(exp.head.unEx(), ExpList(exp.tail));
    }

    public tiger.translate.Exp Error() {
        return new Ex(CONST(0));
    }

    public tiger.translate.Exp SimpleVar(Access access, Level level) {
        Level d = access.home;
        Exp base = TEMP(level.frame.FP());
        for (; level != d; level = level.parent) {
            base = level.frame.formals.head.exp(base);
        }
        return new tiger.translate.Ex(access.acc.exp(base));
    }

    public tiger.translate.Exp FieldVar(tiger.translate.Exp record, int index) {
        return new Ex(BINOP(0, MEM(record.unEx()), CONST(index)));
    }

    public tiger.translate.Exp SubscriptVar(tiger.translate.Exp array, tiger.translate.Exp index) {
        return new Ex(BINOP(0, MEM(array.unEx()), index.unEx()));
    }

    public tiger.translate.Exp NilExp() {
        return new Nx(null);
    }

    public tiger.translate.Exp IntExp(int value) {
        return new Ex(CONST(value));
    }
    private java.util.HashMap strings = new java.util.HashMap();

    public tiger.translate.Exp StringExp(String lit) {
        String u = lit.intern();
        Label lab = (Label) strings.get(u);
        if (lab == null) {
            lab = new Label();
            strings.put(u, lab);
            DataFrag frag = new DataFrag(frame.string(lab, u));
            frag.next = frags;
            frags = frag;
        }
        return new Ex(NAME(lab));
    }

    private tiger.tree.Exp CallExp(Symbol f, tiger.translate.ExpList args, Level from) {
        return frame.externalCall(f.toString(), ExpList(args));
    }

    private tiger.tree.Exp CallExp(Level f, tiger.translate.ExpList args, Level from) {
        return frame.externalCall(f.name().toString(), ExpList(args));
    }

    public tiger.translate.Exp FunExp(Symbol f, tiger.translate.ExpList args, Level from) {
        return new Ex(CallExp(f, args, from));
    }

    public tiger.translate.Exp FunExp(Level f, tiger.translate.ExpList args, Level from) {
        return new Ex(CallExp(f, args, from));
    }

    public tiger.translate.Exp ProcExp(Symbol f, tiger.translate.ExpList args, Level from) {
        return new Nx(EXPR(CallExp(f, args, from)));
    }

    public tiger.translate.Exp ProcExp(Level f, tiger.translate.ExpList args, Level from) {
        return new Nx(EXPR(CallExp(f, args, from)));
    }

    public tiger.translate.Exp OpExp(int op, tiger.translate.Exp left, tiger.translate.Exp right) {
        switch (op) {
            case 0:
            case 1:
            case 2:
            case 3:
                return new Ex(BINOP(op, left.unEx(), right.unEx()));
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return new Ex(BINOP(op, left.unEx(), right.unEx()));
            // return new RelCx(op - 4, left.unEx(), right.unEx());
        }
        return Error();
    }

    public tiger.translate.Exp StrOpExp(int op, tiger.translate.Exp left, tiger.translate.Exp right) {
        switch (op) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return new Ex(BINOP(op, left.unEx(), right.unEx()));
        }
        return Error();
    }

    public tiger.translate.Exp RecordExp(tiger.translate.ExpList init) {
        return new Nx(null);
    }

    public tiger.translate.Exp SeqExp(tiger.translate.ExpList e) {
        Nx exp = null;
        if (e != null) {
            exp = new Nx(null);
        }
        while (e != null) {
            if (e.head != null) {
                exp = new Nx(SEQ(e.head.unNx(), exp.unNx()));
            }
            e = e.tail;
        }
        return exp;
    }

    public tiger.translate.Exp AssignExp(tiger.translate.Exp lhs, tiger.translate.Exp rhs) {
        return new Nx(MOVE(lhs.unEx(), rhs.unEx()));
    }

    public tiger.translate.Exp IfExp(tiger.translate.Exp cc, tiger.translate.Exp aa, tiger.translate.Exp bb) {
        return new IfThenElseExp(cc, aa, bb);
    }

    public tiger.translate.Exp WhileExp(tiger.translate.Exp test, tiger.translate.Exp body, Label done) {
        Label start = new Label();
        Label loopBody = new Label();
        return new Nx(SEQ(SEQ(SEQ(SEQ(SEQ(new LABEL(start), test.unCx(
                loopBody, done)), new LABEL(loopBody)), body.unNx()),
                JUMP(start)), new LABEL(done)));
    }

    public tiger.translate.Exp ForExp(Access i, tiger.translate.Exp lo, tiger.translate.Exp hi, tiger.translate.Exp body, Label done) {
        Access limit = i;
        Level d = i.home;
        return (new Nx(SEQ(AssignExp(SimpleVar(i, d), lo).unNx(), AssignExp(
                SimpleVar(limit, d), hi).unNx())));
    }

    public tiger.translate.Exp BreakExp(Label done) {
        return new Nx(JUMP(done));
    }

    public tiger.translate.Exp LetExp(tiger.translate.ExpList lets, tiger.translate.Exp body) {
        tiger.translate.ExpList tmp = null;
        tiger.translate.ExpList iter = lets;
        tiger.translate.Exp exp = body;
        while (iter != null) {
            tmp = new tiger.translate.ExpList(iter.head, tmp);
            iter = iter.tail;
        }
        if (tmp != null) {
            exp = new Nx(SEQ(exp.unNx(), tmp.head.unNx()));
            tmp = tmp.tail;
        }
        while (tmp != null) {
            exp = new Nx(SEQ(tmp.head.unNx(), exp.unNx()));
            tmp = tmp.tail;
        }
        return (exp);
    }

    public tiger.translate.Exp ArrayExp(tiger.translate.Exp size, tiger.translate.Exp init) {
        Label initArray = new Label("initArray");
        return new Ex(new CALL(NAME(initArray), new ExpList(size
                .unEx(), new ExpList(init.unEx(), null))));
    }

    public tiger.translate.Exp VarDec(Access a, tiger.translate.Exp init) {
        return new Nx(MOVE(a.acc.exp(TEMP(a.home.frame.FP())), init.unEx()));
    }

    public tiger.translate.Exp TypeDec() {
        return new Nx(null);
    }

    public tiger.translate.Exp FunctionDec() {
        return new Nx(null);
    }
}
