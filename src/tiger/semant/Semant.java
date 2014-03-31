package tiger.semant;

import tiger.absyn.*;
import tiger.types.*;
import java.util.Hashtable;

public class Semant {

    Env env;

    public Semant() {
        env = new Env();
    }

    public Semant(Env env) {
        this.env = env;
    }

    public void transProg(Exp exp) {
        ExpTy body = transExp(exp);
    }

    private void error(Position pos, String msg) {
        System.err.println(msg + ": " + pos);
    }
    static final VOID VOID = new VOID();
    static final INT INT = new INT();
    static final STRING STRING = new STRING();
    static final NIL NIL = new NIL();

    private Exp checkInt(ExpTy et, Position pos) {
        if (!INT.coerceTo(et.getTy())) {
            error(pos, "integer required");
        }
        return et.getExp();
    }

    private Exp checkComparable(ExpTy et, Position pos) {
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

    private Exp checkOrderable(ExpTy et, Position pos) {
        Type a = et.getTy().actual();
        if (!(a instanceof INT
                || a instanceof STRING)) {
            error(pos, "integer or string required");
        }
        return et.getExp();
    }

    ExpTy transExp(Exp e) {
        ExpTy result;

        if (e == null) {
            return new ExpTy(null, VOID);
        } else if (e instanceof VarExp) {
            result = transExp((VarExp) e);
        } else if (e instanceof NilExp) {
            result = transExp((NilExp) e);
        } else if (e instanceof IntExp) {
            result = transExp((IntExp) e);
        } else if (e instanceof StringExp) {
            result = transExp((StringExp) e);
        } else if (e instanceof CallExp) {
            result = transExp((CallExp) e);
        } else if (e instanceof OpExp) {
            result = transExp((OpExp) e);
        } else if (e instanceof RecordExp) {
            result = transExp((RecordExp) e);
        } else if (e instanceof SeqExp) {
            result = transExp((SeqExp) e);
        } else if (e instanceof AssignExp) {
            result = transExp((AssignExp) e);
        } else if (e instanceof IfExp) {
            result = transExp((IfExp) e);
        } else if (e instanceof WhileExp) {
            result = transExp((WhileExp) e);
        } else if (e instanceof ForExp) {
            result = transExp((ForExp) e);
        } else if (e instanceof BreakExp) {
            result = transExp((BreakExp) e);
        } else if (e instanceof LetExp) {
            result = transExp((LetExp) e);
        } else if (e instanceof ArrayExp) {
            result = transExp((ArrayExp) e);
        } else {
            throw new Error("Semant.transExp");
        }
        e.type = result.getTy();
        return result;
    }

    ExpTy transExp(VarExp e) {
        return transVar(e.var);
    }

    ExpTy transVar(Var v) {
        return transVar(v, false);
    }

    ExpTy transVar(Var v, boolean lhs) {
        if (v instanceof SimpleVar) {
            return transVar((SimpleVar) v, lhs);
        }
        if (v instanceof FieldVar) {
            return transVar((FieldVar) v);
        }
        if (v instanceof SubscriptVar) {
            return transVar((SubscriptVar) v);
        }
        throw new Error("Semant.transVar");
    }

    ExpTy transVar(SimpleVar v, boolean lhs) {
        Entry x = (Entry) env.venv.get(v.name);
        if (x instanceof VarEntry) {
            VarEntry ent = (VarEntry) x;
            if (lhs && ent instanceof LoopVarEntry) {
                error(v.pos, "assignment to loop index");
            }
            return new ExpTy(null, ent.getTy());
        }
        error(v.pos, "undeclared variable: " + v.name);
        return new ExpTy(null, VOID);
    }

    ExpTy transVar(FieldVar v) {
        ExpTy var = transVar(v.var);
        Type actual = var.getTy().actual();
        if (actual instanceof RECORD) {
            int count = 0;
            for (RECORD field = (RECORD) actual;
                    field != null;
                    field = field.tail) {
                if (field.fieldName == v.field) {
                    return new ExpTy(null,
                            field.fieldType);
                }
                ++count;
            }
            error(v.pos, "undeclared field: " + v.field);
        } else {
            error(v.var.pos, "record required");
        }
        return new ExpTy(null, VOID);
    }

    ExpTy transVar(SubscriptVar v) {
        ExpTy var = transVar(v.var);
        ExpTy index = transExp(v.index);
        checkInt(index, v.index.pos);
        Type actual = var.getTy().actual();
        if (actual instanceof ARRAY) {
            ARRAY array = (ARRAY) actual;
            return new ExpTy(null,
                    array.element);
        }
        error(v.var.pos, "array required");
        return new ExpTy(null, VOID);
    }

    ExpTy transExp(NilExp e) {
        return new ExpTy(null, NIL);
    }

    ExpTy transExp(IntExp e) {
        return new ExpTy(null, INT);
    }

    ExpTy transExp(StringExp e) {
        return new ExpTy(null, STRING);
    }

    ExpTy transExp(CallExp e) {
        Entry x = (Entry) env.venv.get(e.func);
        if (x instanceof FunEntry) {
            FunEntry f = (FunEntry) x;
            ExpList args = transArgs(e.getPosition(), f.getFormals(), e.args);
            return new ExpTy(null,
                    f.getResult());

        }
        error(e.pos, "undeclared function: " + e.func);
        return new ExpTy(null, VOID);
    }

    private ExpList transArgs(Position epos, RECORD formal, ExpList args) {
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
        ExpTy e = transExp(args.head);
        if (!e.getTy().coerceTo(formal.fieldType)) {
            error(args.head.pos, "argument type mismatch");
        }
        return new ExpList(e.getExp(), transArgs(epos, formal.tail, args.tail));
    }

    ExpTy transExp(OpExp e) {
        ExpTy left = transExp(e.left);
        ExpTy right = transExp(e.right);

        switch (e.oper) {
            case OpExp.PLUS:
            case OpExp.MINUS:
            case OpExp.MUL:
            case OpExp.DIV:
                checkInt(left, e.left.pos);
                checkInt(right, e.right.pos);
                return new ExpTy(null, INT);
            case OpExp.EQ:
            case OpExp.NE:
                checkComparable(left, e.left.pos);
                checkComparable(right, e.right.pos);
                if (STRING.coerceTo(left.getTy()) && STRING.coerceTo(right.getTy())) {
                    return new ExpTy(null, INT);
                } else if (!left.getTy().coerceTo(right.getTy()) && !right.getTy().coerceTo(left.getTy())) {
                    error(e.pos, "incompatible operands to equality operator");
                }
                return new ExpTy(null, INT);
            case OpExp.LT:
            case OpExp.LE:
            case OpExp.GT:
            case OpExp.GE:
                checkOrderable(left, e.left.pos);
                checkOrderable(right, e.right.pos);
                if (STRING.coerceTo(left.getTy()) && STRING.coerceTo(right.getTy())) {
                    return new ExpTy(null, INT);
                } else if (!left.getTy().coerceTo(right.getTy()) && !right.getTy().coerceTo(left.getTy())) {
                    error(e.pos, "incompatible operands to inequality operator");
                }
                return new ExpTy(null, INT);
            default:
                throw new Error("unknown operator");
        }
    }

    ExpTy transExp(RecordExp e) {
        NAME name = (NAME) env.tenv.get(e.typ);
        if (name != null) {
            Type actual = name.actual();
            if (actual instanceof RECORD) {
                RECORD r = (RECORD) actual;
                return new ExpTy(null,
                        name);
            }
            error(e.pos, "record type required");
        } else {
            error(e.pos, "undeclared type: " + e.typ);
        }
        return new ExpTy(null, VOID);
    }

    private ExpList transFields(Position epos, RECORD f, FieldExpList exp) {
        if (f == null) {
            if (exp != null) {
                error(exp.pos, "too many expressions");
            }
            return null;
        }
        if (exp == null) {
            error(epos, "missing expression for " + f.fieldName);
            return null;
        }
        ExpTy e = transExp(exp.init);
        if (exp.name != f.fieldName) {
            error(exp.pos, "field name mismatch");
        }
        if (!e.getTy().coerceTo(f.fieldType)) {
            error(exp.pos, "field type mismatch");
        }
        return new ExpList(e.getExp(), transFields(epos, f.tail, exp.tail));
    }

    ExpTy transExp(SeqExp e) {
        Type type = VOID;
        ExpList head = new ExpList(null, null), prev = head;
        for (ExpList exp = e.list; exp != null; exp = exp.tail) {
            ExpTy et = transExp(exp.head);
            type = et.getTy();
            prev = prev.tail = new ExpList(et.getExp(), null);
        }
        return new ExpTy(null, type);
    }

    ExpTy transExp(AssignExp e) {
        ExpTy var = transVar(e.var, true);
        ExpTy exp = transExp(e.exp);
        if (!exp.getTy().coerceTo(var.getTy())) {
            error(e.pos, "assignment type mismatch");
        }
        return new ExpTy(null, VOID);
    }

    ExpTy transExp(IfExp e) {
        ExpTy test = transExp(e.test);
        checkInt(test, e.test.pos);
        ExpTy thenclause = transExp(e.thenclause);
        ExpTy elseclause = transExp(e.elseclause);
        if (!thenclause.getTy().coerceTo(elseclause.getTy())
                && !elseclause.getTy().coerceTo(thenclause.getTy())) {
            error(e.pos, "result type mismatch");
        }
        return new ExpTy(null,
                elseclause.getTy());
    }

    ExpTy transExp(WhileExp e) {
        ExpTy test = transExp(e.test);
        checkInt(test, e.test.pos);
        LoopSemant loop = new LoopSemant(env);
        ExpTy body = loop.transExp(e.body);
        if (!body.getTy().coerceTo(VOID)) {
            error(e.body.pos, "result type mismatch");
        }
        return new ExpTy(null, VOID);
    }

    ExpTy transExp(ForExp e) {
        ExpTy lo = transExp(e.var.init);
        checkInt(lo, e.var.pos);
        ExpTy hi = transExp(e.hi);
        checkInt(hi, e.hi.pos);
        env.venv.beginScope();
        e.var.entry = new LoopVarEntry(INT);
        env.venv.put(e.var.name, e.var.entry);
        LoopSemant loop = new LoopSemant(env);
        ExpTy body = loop.transExp(e.body);
        env.venv.endScope();
        if (!body.getTy().coerceTo(VOID)) {
            error(e.body.pos, "result type mismatch");
        }
        return new ExpTy(null,
                VOID);
    }

    ExpTy transExp(BreakExp e) {
        error(e.pos, "break outside loop");
        return new ExpTy(null, VOID);
    }

    ExpTy transExp(LetExp e) {
        env.venv.beginScope();
        env.tenv.beginScope();
        ExpList head = new ExpList(null, null), prev = head;
        for (DecList d = e.decs; d != null; d = d.tail) {
            prev = prev.tail = new ExpList(transDec(d.head), null);
        }
        ExpTy body = transExp(e.body);
        env.venv.endScope();
        env.tenv.endScope();
        return new ExpTy(null, body.getTy());
    }

    ExpTy transExp(ArrayExp e) {
        NAME name = (NAME) env.tenv.get(e.typ);
        ExpTy size = transExp(e.size);
        ExpTy init = transExp(e.init);
        checkInt(size, e.size.pos);
        if (name != null) {
            Type actual = name.actual();
            if (actual instanceof ARRAY) {
                ARRAY array = (ARRAY) actual;
                if (!init.getTy().coerceTo(array.element)) {
                    error(e.init.pos, "element type mismatch");
                }
                return new ExpTy(null, name);
            } else {
                error(e.pos, "array type required");
            }
        } else {
            error(e.pos, "undeclared type: " + e.typ);
        }
        return new ExpTy(null, VOID);
    }

    Exp transDec(Dec d) {
        if (d instanceof VarDec) {
            return transDec((VarDec) d);
        }
        if (d instanceof TypeDec) {
            return transDec((TypeDec) d);
        }
        if (d instanceof FunctionDec) {
            return transDec((FunctionDec) d);
        }
        throw new Error("Semant.transDec");
    }

    Exp transDec(VarDec d) {
        ExpTy init = transExp(d.init);
        Type type;
        if (d.typ == null) {
            if (init.getTy().coerceTo(NIL)) {
                error(d.pos, "record type required");
            }
            type = init.getTy();
        } else {
            type = transTy(d.typ);
            if (!init.getTy().coerceTo(type)) {
                error(d.pos, "assignment type mismatch");
            }
        }
        d.entry = new VarEntry(type);
        env.venv.put(d.name, d.entry);
        return null;
    }

    Exp transDec(TypeDec d) {
        // 1st pass - handles the type headers
        // Using a local hashtable, check if there are two types 
        // with the same name in the same (consecutive) batch 
        // of mutually recursive types. See test38.tig!
        Hashtable hash = new Hashtable();
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
            name.bind(transTy(type.ty));
        }

        // check for illegal cycle in type declarations
        for (TypeDec type = d; type != null; type = type.next) {
            NAME name = (NAME) type.entry;
            if (name.isLoop()) {
                error(type.pos, "illegal type cycle");
            }
        }
        return null;
    }

    Exp transDec(FunctionDec d) {
        // 1st pass - handles the function headers
        Hashtable hash = new Hashtable();
        for (FunctionDec f = d; f != null; f = f.next) {
            if (hash.put(f.name, f.name) != null) {
                error(f.pos, "function redeclared");
            }
            RECORD fields = transTypeFields(new Hashtable(), f.params);
            Type type = transTy(f.result);
            f.entry = new FunEntry(fields, type);
            env.venv.put(f.name, f.entry);
        }
        // 2nd pass - handles the function bodies
        for (FunctionDec f = d; f != null; f = f.next) {
            env.venv.beginScope();
            putTypeFields(f.entry.getFormals());
            Semant fun = new Semant(env);
            ExpTy body = fun.transExp(f.body);
            if (!body.getTy().coerceTo(f.entry.getResult())) {
                error(f.body.pos, "result type mismatch");
            }
            env.venv.endScope();
        }
        return null;
    }
    /*
     private Util.BoolList escapes(FieldList f) {
     if (f == null) {
     return null;
     }
     return new Util.BoolList(f.escape, escapes(f.tail));
     }
     */

    private RECORD transTypeFields(Hashtable hash, FieldList f) {
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
    /*
     private void putTypeFields(RECORD f, AccessList a) {
     if (f == null) {
     return;
     }
     env.venv.put(f.fieldName, new VarEntry(a.head, f.fieldType));
     putTypeFields(f.tail, a.tail);
     }
     */

    Type transTy(Ty t) {
        if (t instanceof NameTy) {
            return transTy((NameTy) t);
        }
        if (t instanceof RecordTy) {
            return transTy((RecordTy) t);
        }
        if (t instanceof ArrayTy) {
            return transTy((ArrayTy) t);
        }
        throw new Error("Semant.transTy");
    }

    Type transTy(NameTy t) {
        if (t == null) {
            return VOID;
        }
        NAME name = (NAME) env.tenv.get(t.name);
        if (name != null) {
            return name;
        }
        error(t.pos, "undeclared type: " + t.name);
        return VOID;
    }

    Type transTy(RecordTy t) {
        RECORD type = transTypeFields(new Hashtable(), t.fields);
        if (type != null) {
            return type;
        }
        return VOID;
    }

    Type transTy(ArrayTy t) {
        NAME name = (NAME) env.tenv.get(t.typ);
        if (name != null) {
            return new ARRAY(name);
        }
        error(t.pos, "undeclared type: " + t.typ);
        return VOID;
    }

    private void putTypeFields(RECORD f) {
        if (f == null) {
            return;
        }
        env.venv.put(f.fieldName, new VarEntry( f.fieldType));
    }
}

class LoopSemant extends Semant {

    public LoopSemant(Env env) {
        super(env);
    }

    ExpTy transExp(BreakExp e) {
        return new ExpTy(null, VOID);
    }
}
