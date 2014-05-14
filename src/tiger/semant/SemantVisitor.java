package tiger.semant;

import java.util.HashMap;
import tiger.absyn.Absyn;
import tiger.absyn.ArrayExp;
import tiger.absyn.ArrayTy;
import tiger.absyn.AssignExp;
import tiger.absyn.BreakExp;
import tiger.absyn.CallExp;
import tiger.absyn.Dec;
import tiger.absyn.DecList;
import tiger.absyn.Exp;
import tiger.absyn.ExpList;
import tiger.absyn.FieldList;
import tiger.absyn.FieldVar;
import tiger.absyn.ForExp;
import tiger.absyn.FunctionDec;
import tiger.absyn.IfExp;
import tiger.absyn.IntExp;
import tiger.absyn.LetExp;
import tiger.absyn.NameTy;
import tiger.absyn.NilExp;
import tiger.absyn.OpExp;
import tiger.absyn.Position;
import tiger.absyn.RecordExp;
import tiger.absyn.RecordTy;
import tiger.absyn.SeqExp;
import tiger.absyn.SimpleVar;
import tiger.absyn.StringExp;
import tiger.absyn.SubscriptVar;
import tiger.absyn.Ty;
import tiger.absyn.TypeDec;
import tiger.absyn.VarDec;
import tiger.absyn.VarExp;
import tiger.absyn.Visitor;
import tiger.absyn.WhileExp;
import tiger.types.ARRAY;
import tiger.types.INT;
import tiger.types.NAME;
import tiger.types.NIL;
import tiger.types.RECORD;
import tiger.types.STRING;
import tiger.types.Type;
import tiger.types.VOID;

public class SemantVisitor implements Visitor {

    private ExpTy expTy;
    private Env env;
    private Type ty;
    private Exp exp;
    static final VOID VOID = new VOID();
    static final INT INT = new INT();
    static final STRING STRING = new STRING();
    static final NIL NIL = new NIL();

    private tiger.translate.Exp checkComparable(ExpTy et, Position pos) {
        Type a = et.getTy().actual();
        if (!(a instanceof INT
                || a instanceof STRING
                || a instanceof NIL
                || a instanceof RECORD
                || a instanceof ARRAY)) {
            error(pos, "integer, string, nil, record or array required");
        }
        return et.getExp();
    }

    private tiger.translate.Exp checkOrderable(ExpTy et, Position pos) {
        Type a = et.getTy().actual();
        if (!(a instanceof INT
                || a instanceof STRING)) {
            error(pos, "integer or string required");
        }
        return et.getExp();
    }

    private tiger.translate.Exp checkInt(ExpTy et, Position pos) {
        if (!INT.coerceTo(et.getTy())) {
            error(pos, "integer required");
        }
        return et.getExp();
    }

    public SemantVisitor() {
        env = new Env();
    }

    public SemantVisitor(Env env) {
        this.env = env;
    }

    private void error(Position pos, String msg) {
        System.err.println(msg + ": " + pos);
    }

    private void putTypeFields(RECORD f) {
        if (f == null) {
            return;
        }
        env.venv.put(f.fieldName, new VarEntry(f.fieldType));
        putTypeFields(f.tail);
    }

    @Override
    public void visit(VarDec e) {
        e.init.accept(this);
        ExpTy init = getExpTy();
        Type type;
        if (e.typ == null) {
            if (init.getTy().coerceTo(NIL)) {
                error(e.pos, "record type required");
            }
            type = init.getTy();
        } else {
            e.typ.accept(this);
            type = ty;
            if (!init.getTy().coerceTo(type)) {
                error(e.pos, "assignment type mismatch");
            }
        }
        e.entry = new VarEntry(type);
        env.venv.put(e.name, e.entry);
    }

    @Override
    public void visit(VarExp e) {
        e.var.accept(this);
    }

    @Override
    public void visit(ArrayExp e) {
        NAME name = (NAME) env.tenv.get(e.typ);
        e.size.accept(this);
     
        ExpTy size = getExpTy();
        e.init.accept(this);
        ExpTy init = getExpTy();
        checkInt(size, e.size.pos);
        if (name != null) {
            Type actual = name.actual();
            if (actual.isType(Type.ARRAY)) {
                ARRAY array = (ARRAY) actual;
                if (!init.getTy().coerceTo(array.element)) {
                    error(e.init.pos, "element type mismatch");
                }
                setExpTy(new ExpTy(null, name));
                return;

            } else {
                error(e.pos, "undeclared type: " + e.typ);
            }

        }
        setExpTy(new ExpTy(null, VOID));
    }

    @Override
    public void visit(ArrayTy t) {
        NAME name = (NAME) env.tenv.get(t.typ);
        if (name != null) {
            ty = new ARRAY(name);
            return;
        }
        error(t.pos, "undeclared type: " + t.typ);
    }

    @Override
    public void visit(AssignExp e) {
        e.var.accept(this);
        ExpTy var = getExpTy();
        e.exp.accept(this);
        ExpTy exp = getExpTy();
        if (!exp.getTy().coerceTo(var.getTy())) {
            error(e.pos, "assignment type mismatch");
        }
        setExpTy(new ExpTy(null, VOID));
    }

    @Override
    public void visit(BreakExp e) {
        error(e.pos, "break outside loop");
        setExpTy(new ExpTy(null, VOID));
    }

    private tiger.semant.ExpList visit(Position epos, RECORD formal, ExpList args) {
        if (formal == null) {
            if (args != null) {
                error(args.head.pos, "too many arguments");
            }
            return null;
        }
        if (args == null) {
            error(epos, "missing argument for " + formal.fieldName);
            return null;
        }
        args.head.accept(this);
        ExpTy e = getExpTy();
        if (!e.getTy().coerceTo(formal.fieldType)) {
            error(args.head.pos, "argument type mismatch");
        }
        return new tiger.semant.ExpList(e.getExp(), visit(epos, formal.tail, args.tail));
    }

    @Override
    public void visit(CallExp e) {
        Entry x = (Entry) env.venv.get(e.func);
        if (x.getEntryType() == Entry.FUNENTRY) {
            FunEntry f = (FunEntry) x;
            tiger.semant.ExpList args = visit(e.getPosition(), f.getFormals(), e.args);
            setExpTy(new ExpTy(null,
                    f.getResult()));
            return;

        }
        error(e.pos, "undeclared function: " + e.func);
        setExpTy(new ExpTy(null, VOID));
    }

    @Override
    public void visit(FieldVar v) {
        v.accept(this);
       
        ExpTy var = getExpTy();
        Type actual = var.getTy().actual();
        if (actual.isType(Type.RECORD)) {
            int count = 0;
            for (RECORD field = (RECORD) actual;
                    field != null;
                    field = field.tail) {
                if (field.fieldName == v.field) {
                    expTy = new ExpTy(null,
                            field.fieldType);
                    return;
                }
                ++count;
            }
            error(v.pos, "undeclared field: " + v.field);
        } else {
            error(v.var.pos, "record required");
        }
        expTy = new ExpTy(null, VOID);
    }

    @Override
    public void visit(ForExp e) {
        e.var.init.accept(this);
        checkInt(getExpTy(), e.var.pos);
        e.hi.accept(this);
        checkInt(getExpTy(), e.hi.pos);
        env.venv.beginScope();
        e.var.entry = new LoopVarEntry(INT);
        env.venv.put(e.var.name, e.var.entry);
        LoopSemantVisitor loop = new LoopSemantVisitor(env);
        e.body.accept(loop);
        ExpTy body = loop.getExpTy();
        env.venv.endScope();
        if (!body.getTy().coerceTo(VOID)) {
            error(e.body.pos, "result type mismatch");
        }
        setExpTy(new ExpTy(null,
                VOID));
    }

    private RECORD transTypeFields(HashMap hash, FieldList f) {
        if (f == null) {
            return null;
        }
        NAME name = (NAME) env.tenv.get(f.typ);
        if (name == null) {
            error(f.pos, "undeclared type: " + f.typ);
        }
        if (hash.put(f.name, f.name) != null) {
            error(f.pos, "function parameter/record field redeclared: " + f.name);
        }
        return new RECORD(f.name, name, transTypeFields(hash, f.tail));
    }

    @Override
    public void visit(FunctionDec d) {
        // 1st pass - handles the function headers
        HashMap hash = new HashMap();
        for (FunctionDec f = d; f != null; f = f.next) {
            if (hash.put(f.name, f.name) != null) {
                error(f.pos, "function redeclared");
            }
            RECORD fields = transTypeFields(new HashMap(), f.params);
            f.result.accept(this);
            Type type = ty;
            f.entry = new FunEntry(fields, type);
            env.venv.put(f.name, f.entry);
        }
        // 2nd pass - handles the function bodies
        for (FunctionDec f = d; f != null; f = f.next) {
            env.venv.beginScope();
            putTypeFields(f.entry.getFormals());
            SemantVisitor fun = new SemantVisitor(env);
            ExpTy body;
            f.body.accept(fun);
            body = fun.getExpTy();
            if (!body.getTy().coerceTo(f.entry.getResult())) {
                error(f.body.pos, "result type mismatch");
            }
            env.venv.endScope();
        }
    }

    @Override
    public void visit(IfExp e) {
        e.test.accept(this);
        ExpTy test = getExpTy();
        checkInt(test, e.test.pos);
        e.thenclause.accept(this);
        ExpTy thenclause = getExpTy();
        e.elseclause.accept(this);
        ExpTy elseclause = expTy;
        if (!thenclause.getTy().coerceTo(elseclause.getTy())
                && !elseclause.getTy().coerceTo(thenclause.getTy())) {
            error(e.pos, "result type mismatch");
        }
        expTy = new ExpTy(null,
                elseclause.getTy());
    }

    @Override
    public void visit(IntExp e) {
        setExpTy(new ExpTy(null, INT));
    }

    @Override
    public void visit(LetExp e) {
        env.venv.beginScope();
        env.tenv.beginScope();
        for (DecList d = e.decs; d != null; d = d.tail)
            d.head.accept(this);
        e.body.accept(this);
        env.venv.endScope();
        env.tenv.endScope();
        setExpTy(new ExpTy(null, getExpTy().getTy()));
    }

    @Override
    public void visit(NameTy t) {
        if (t == null) {
            ty = VOID;
            return;
        }
        NAME name = (NAME) env.tenv.get(t.name);
        if (name != null) {
            ty = name;
            return;
        }
        error(t.pos, "undeclared type: " + t.name);

    }

    @Override
    public void visit(NilExp e) {
        setExpTy(new ExpTy(null, NIL));
    }

    @Override
    public void visit(OpExp e) {
        e.left.accept(this);

        ExpTy left = getExpTy();
        e.right.accept(this);
        ExpTy right = getExpTy();
        switch (e.oper) {
            case OpExp.PLUS:
            case OpExp.MINUS:
            case OpExp.MUL:
            case OpExp.DIV:
                checkInt(left, e.left.pos);
                checkInt(right, e.right.pos);
                setExpTy(new ExpTy(null, INT));
                return;
            case OpExp.EQ:
            case OpExp.NE:
                checkComparable(left, e.left.pos);
                checkComparable(right, e.right.pos);
                if (STRING.coerceTo(left.getTy()) && STRING.coerceTo(right.getTy())) {
                    setExpTy(new ExpTy(null, INT));
                    return;
                } else if (!left.getTy().coerceTo(right.getTy()) && !right.getTy().coerceTo(left.getTy())) {
                    error(e.pos, "incompatible operands to equality operator");
                }
                setExpTy(new ExpTy(null, INT));
                return;
            case OpExp.LT:
            case OpExp.LE:
            case OpExp.GT:
            case OpExp.GE:
                checkOrderable(left, e.left.pos);
                checkOrderable(right, e.right.pos);
                if (STRING.coerceTo(left.getTy()) && STRING.coerceTo(right.getTy())) {
                    setExpTy(new ExpTy(null, INT));
                    return;
                } else if (!left.getTy().coerceTo(right.getTy()) && !right.getTy().coerceTo(left.getTy())) {
                    error(e.pos, "incompatible operands to inequality operator");
                }
                setExpTy(new ExpTy(null, INT));
                return;
            default:
                throw new Error("unknown operator");
        }

    }

    @Override
    public void visit(RecordExp e) {
        NAME name = (NAME) env.tenv.get(e.typ);
        if (name != null) {
            Type actual = name.actual();
            if (actual.isType(Type.RECORD)) {
                RECORD r = (RECORD) actual;
                setExpTy(new ExpTy(null,
                        name));
                return;
            }
            error(e.pos, "record type required");
        } else {
            error(e.pos, "undeclared type: " + e.typ);
        }
        setExpTy(new ExpTy(null, VOID));
    }

    private RECORD visit(HashMap hash, FieldList f) {
        if (f == null) {
            return null;
        }
        NAME name = (NAME) env.tenv.get(f.typ);
        if (name == null) {
            error(f.pos, "undeclared type: " + f.typ);
        }
        if (hash.put(f.name, f.name) != null) {
            error(f.pos, "function parameter/record field redeclared: " + f.name);
        }
        return new RECORD(f.name, name, transTypeFields(hash, f.tail));
    }

    @Override
    public void visit(RecordTy t) {
        RECORD type = visit(new HashMap(), t.fields);
        if (type != null) {
            ty = type;
        }
        ty = VOID;
    }

    @Override
    public void visit(SeqExp e) {
        Type type = VOID;
        tiger.semant.ExpList head = new tiger.semant.ExpList(null, null), prev = head;
        for (ExpList exp = e.list; exp != null; exp = exp.tail) {
            exp.head.accept(this);
            ExpTy et = getExpTy();
            type = et.getTy();
            prev = prev.tail = new tiger.semant.ExpList(et.getExp(), null);
        }
        expTy = new ExpTy(null, type);
    }

    @Override
    public void visit(SimpleVar v) {
        Entry x = (Entry) env.venv.get(v.name);
        if (x != null && x.getEntryType() == Entry.VARENTRY) {
            VarEntry ent = (VarEntry) x;
            expTy = new ExpTy(null, ent.getTy());
            return;
        }
        error(v.pos, "undeclared variable: " + v.name);
        expTy = new ExpTy(null, VOID);
    }

    @Override
    public void visit(StringExp e) {
        setExpTy(new ExpTy(null, STRING));
    }

    @Override
    public void visit(SubscriptVar v) {
        v.var.accept(this);
        ExpTy var = getExpTy();
        v.index.accept(this);
        ExpTy index = getExpTy();
        checkInt(index, v.index.pos);
        Type actual = var.getTy().actual();
        if (actual.isType(Type.ARRAY)) {
            ARRAY array = (ARRAY) actual;
            expTy = new ExpTy(null,
                    array.element);
            return;
        }
        error(v.var.pos, "array required");
        expTy = new ExpTy(null, VOID);
        return;
    }

    @Override
    public void visit(TypeDec d) {
        // 1st pass - handles the type headers
        // Using a local hashtable, check if there are two types 
        // with the same name in the same (consecutive) batch 
        // of mutually recursive types. See test38.tig!
        HashMap hash = new HashMap();
        for (TypeDec type = d; type != null; type = type.next) {
            if (hash.put(type.name, type.name) != null) {
                error(type.pos, "type redeclared");
            }
            type.entry = new NAME(type.name);
            env.tenv.put(type.name, type.entry);
        }

        // 2nd pass - handles the type bodies
        for (TypeDec type = d; type != null; type = type.next) {
            NAME name = (NAME) type.entry;
            type.ty.accept(this);
            name.bind(ty);
        }

        // check for illegal cycle in type declarations
        for (TypeDec type = d; type != null; type = type.next) {
            NAME name = (NAME) type.entry;
            if (name.isLoop()) {
                error(type.pos, "illegal type cycle");
            }
        }

    }

    @Override
    public void visit(WhileExp e) {
        e.test.accept(this);
        ExpTy test = expTy;
        checkInt(test, e.test.pos);
        LoopSemantVisitor loop = new LoopSemantVisitor(env);
        e.body.accept(loop);
        ExpTy body = loop.getExpTy();
        if (!body.getTy().coerceTo(VOID)) {
            error(e.body.pos, "result type mismatch");
        }
        expTy = new ExpTy(null, VOID);
    }

    @Override
    public void visit(Absyn e) {
        e.accept(this);
    }

    @Override
    public void visit(Exp e) {
        e.accept(this);
    }

    @Override
    public void visit(Ty t) {
        t.accept(this);
    }

    @Override
    public void visit(Dec d) {
        d.accept(this);
    }

    /**
     * @return the expTy
     */
    public ExpTy getExpTy() {
        return expTy;
    }

    /**
     * @param expTy the expTy to set
     */
    public void setExpTy(ExpTy expTy) {
        this.expTy = expTy;
    }
}

class LoopSemantVisitor extends SemantVisitor {

    public LoopSemantVisitor(Env env) {
        super(env);
    }

    @Override
    public void visit(BreakExp e) {
        setExpTy(new ExpTy(null, VOID));
    }
}