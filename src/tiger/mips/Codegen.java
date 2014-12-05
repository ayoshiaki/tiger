package tiger.mips;

import tiger.assem.Instr;
import tiger.assem.InstrList;
import tiger.assem.OPER;
import tiger.temp.Label;
import tiger.temp.LabelList;
import tiger.temp.Temp;
import tiger.temp.TempList;
import tiger.temp.TempMap;
import tiger.tree.BINOP;
import tiger.tree.CALL;
import tiger.tree.CJUMP;
import tiger.tree.CONST;
import tiger.tree.CONSTFLOAT;
import tiger.tree.EXPR;
import tiger.tree.Exp;
import tiger.tree.ExpList;
import tiger.tree.JUMP;
import tiger.tree.LABEL;
import tiger.tree.MEM;
import tiger.tree.MOVE;
import tiger.tree.NAME;
import tiger.tree.Stm;
import tiger.tree.StmList;
import tiger.tree.TEMP;

public class Codegen {

    MipsFrame frame;
    static final String MOVE_INST = "move `d0, `s0";
    static final String LOADI_INST = "li `d0, ";
    static final String LOADW_INST = "lw `d0, ";
    static final String STOREW_INST = "sw `s0, ";

    public Codegen(MipsFrame f) {
        frame = f;
    }

    static tiger.assem.Instr OPER(String a, TempList d, TempList s) {
        return new tiger.assem.OPER(a, d, s);
    }

    private static CONST CONST16(Exp e) {
        if (e instanceof CONST) {
            CONST c = (CONST) e;
            int value = c.value;
            if (value == (short) value) {
                return c;
            }
        }
        return null;
    }
    private InstrList ilist = null, last = null;

    private void emit(Instr inst) {
        if (last != null) {
            last = last.tail = new InstrList(inst, null);
        } else {
            if (ilist != null) {
                throw new Error("Codegen.emit");
            }
            last = ilist = new InstrList(inst, null);
        }
    }

    InstrList codegen(Stm s) {
        munchStm(s);
        InstrList l = ilist;
        ilist = last = null;
        return l;
    }

    static Instr MOVE(String a, Temp d, Temp s) {
        return new tiger.assem.MOVE(a, d, s);
    }

    static TempList L(Temp h) {
        return new TempList(h, null);
    }

    static TempList L(Temp h, TempList t) {
        return new TempList(h, t);
    }

    public void munchStms(StmList slist) {
        StmList list = slist;
        for (; list != null; list = list.tail) {
            munchStm(list.head);
        }
    }

    void munchStm(Stm s) {
        if (s instanceof MOVE) {
            munchStm((MOVE) s);
        } else if (s instanceof EXPR) {
            munchStm((EXPR) s);
        } else if (s instanceof JUMP) {
            munchStm((JUMP) s);
        } else if (s instanceof CJUMP) {
            munchStm((CJUMP) s);
        } else if (s instanceof LABEL) {
            munchStm((LABEL) s);
        } else {
            throw new Error("Codegen.munchStm");
        }
    }

    void munchStm(CJUMP s) {
        Temp temp_esq = munchExp(s.left);
        Temp temp_dir = munchExp(s.right);

        emit(new OPER("", null, new TempList(temp_esq, new TempList(temp_dir, null))));

        /**
         * Para jumps "longe" necessita um label auxiliar
         */
        Label label_aux = new Label();

        /**
         * Verifica o tipo de jump para criar o codigo
         */
        switch (s.relop) {
            case CJUMP.EQ:
                emit(new OPER("beq `d0,`d1,L0", L(temp_esq, L(temp_dir , null)), null, new LabelList(label_aux, new LabelList(s.iftrue, new LabelList(s.iffalse, null)))));
                break;
            case CJUMP.NE:
                TempList l = L(temp_esq, L(temp_dir, null));
                emit(new OPER("bne `d0,`d1,L0", L(temp_esq, L(temp_dir , null)), null, new LabelList(label_aux, new LabelList(s.iftrue, new LabelList(s.iffalse, null)))));
                break;
            case CJUMP.LT:
                emit(new OPER("bltz `d0,L0", L(temp_esq, L(temp_dir , null)), null, new LabelList(label_aux, new LabelList(s.iftrue, new LabelList(s.iffalse, null)))));
                break;
            case CJUMP.LE:
                emit(new OPER("blez `d0,L0", L(temp_esq, L(temp_dir , null)), null, new LabelList(label_aux, new LabelList(s.iftrue, new LabelList(s.iffalse, null)))));
                break;
            case CJUMP.GT:
                emit(new OPER("bgtz `d0,L0", L(temp_esq, L(temp_dir , null)), null, new LabelList(label_aux, new LabelList(s.iftrue, new LabelList(s.iffalse, null)))));
                break;
            case CJUMP.GE:
                emit(new OPER("bgez `d0,L0", L(temp_esq, L(temp_dir , null)), null, new LabelList(label_aux, new LabelList(s.iftrue, new LabelList(s.iffalse, null)))));
                break;
        }

        /* faz o jump para false */
        emit(new tiger.assem.LABEL(label_aux.toString() + ":", label_aux));
        emit(new OPER("j L1", null, null, new LabelList(s.iftrue, null)));

    }

    void munchStm(MOVE s) {
        if (s.dst instanceof TEMP) {
            Temp dest = ((TEMP) s.dst).temp;
            emit(OPER("add `d0,`s0,$0", L(dest),
                    L(munchExp(s.src))));
            return;
        }
        if (s.dst instanceof MEM) {
            Exp memDst = ((MEM) s.dst).exp;
            if (memDst instanceof CONST) {
                emit(OPER("sw `s1,0(`s0)", null, L(
                        munchExp(s.dst), L(munchExp(s.src), null))));
                return;
            }
            if (memDst instanceof BINOP && ((BINOP) memDst).binop == 0) {
                BINOP b = (BINOP) memDst;
                if (b.left instanceof CONST) {
                    emit(OPER("sw `s1," + ((CONST) b.left).value
                            + "(`s0)", null, L(munchExp(b.right), L(
                            munchExp(s.src)))));
                    return;
                }
                if (b.right instanceof CONST) {
                    emit(OPER("sw `s1,"
                            + ((CONST) b.right).value + "(`s0)", null, L(
                            munchExp(b.left), L(munchExp(s.src)))));
                    return;
                }
            }
            emit(OPER("sw `s1,0(`s0)", null, L(
                    munchExp(s.dst), L(munchExp(s.src), null))));
            return;
        }
        throw new Error("move node has illegal destination type");
    }

    void munchStm(EXPR s) {
        munchExp(s.exp);
    }

    void munchStm(JUMP s) {
        if (s.exp instanceof NAME) {
            NAME name = (NAME) s.exp;
            Label label = name.label;
            emit(new OPER("j `j0", null, null, s.targets));
        } else {
            throw new Error("can't JUMP to a non-label");
        }
    }
 
    void munchStm(LABEL l) {
        emit(new tiger.assem.LABEL(l.label.toString() + ":", l.label));
    }

    Temp munchExp(Exp s) {
        if (s instanceof CONST) {
            return munchExp((CONST) s);
        }else if(s instanceof CONSTFLOAT) {
            return munchExp((CONSTFLOAT) s);
        } else if (s instanceof NAME) {
            return munchExp((NAME) s);
        } else if (s instanceof TEMP) {
            return munchExp((TEMP) s);
        } else if (s instanceof BINOP) {
            return munchExp((BINOP) s);
        } else if (s instanceof MEM) {
            return munchExp((MEM) s);
        } else if (s instanceof CALL) {
            return munchExp((CALL) s);
        } else {
            throw new Error("Codegen.munchExp");
        }
    }

    Temp munchExp(CONST e) {
        Temp r = new Temp();
        emit(OPER("addi `d0,$0," + e.value, L(r), null));
        return r;
    }
    
    Temp munchExp(CONSTFLOAT e) {
        Temp r = new Temp();
        emit(OPER("addi `d0,$0," + e.value, L(r), null));
        return r;
    }

    Temp munchExp(NAME e) {
        Temp r = new Temp();
        emit(OPER("la `d0," + e.label.toString() + "", L(r), null));
        return r;
    }

    Temp munchExp(TEMP e) {
        if (e.temp == frame.FP) {
            Temp t = new Temp();
            emit(OPER("addu `d0,`s0," + frame.name + "_framesize", L(t),
                    L(frame.SP)));
            return t;
        }
        return e.temp;
    }
    private static String[] BINOP = new String[10];

    static {
        BINOP[tiger.tree.BINOP.PLUS] = "add";
        BINOP[tiger.tree.BINOP.MINUS] = "sub";
        BINOP[tiger.tree.BINOP.MUL] = "mulo";
        BINOP[tiger.tree.BINOP.DIV] = "div";
        BINOP[tiger.tree.BINOP.AND] = "and";
        BINOP[tiger.tree.BINOP.OR] = "or";
        BINOP[tiger.tree.BINOP.LSHIFT] = "sll";
        BINOP[tiger.tree.BINOP.RSHIFT] = "srl";
        BINOP[tiger.tree.BINOP.ARSHIFT] = "sra";
        BINOP[tiger.tree.BINOP.XOR] = "xor";
    }

    private static int shift(int i) {
        int shift = 0;
        if ((i >= 2) && ((i & (i - 1)) == 0)) {
            while (i > 1) {
                shift += 1;
                i >>= 1;
            }
        }
        return shift;
    }

    Temp munchExp(BINOP e) {
        Temp r = new Temp();
        if (e.binop == 0) {
            if (e.left instanceof CONST) {
                emit(OPER("addi `d0,`s0," + ((CONST) e.left).value + "", L(r),
                        L(munchExp(e.right))));
                return r;
            }
            if (e.right instanceof CONST) {
                emit(OPER("addi `d0,`s0," + ((CONST) e.right).value + "", L(r),
                        L(munchExp(e.left))));
                return r;
            }
            emit(OPER("add `d0,`s0,`s1", L(r), L(munchExp(e.left),
                    L(munchExp(e.right)))));
            return r;
        }
        if (e.binop == 1) {
            emit(OPER("sub `d0,`s0,`s1", L(r), L(munchExp(e.left),
                    L(munchExp(e.right)))));
            return r;
        }
        emit(OPER(BINOP[e.binop] + " `d0,`s0,`s1", L(r), L(
                munchExp(e.left), L(munchExp(e.right)))));
        return r;
    }

    Temp munchExp(MEM e) {
        Temp r = new Temp();
        if (e.exp instanceof CONST) {
            emit(OPER("lw `d0," + ((CONST) e.exp).value
                    + "(`s0)", L(r), null));
            return r;
        }
        if (e.exp instanceof BINOP && ((BINOP) e.exp).binop == 0) {
            BINOP b = (BINOP) e.exp;
            if (b.left instanceof CONST) {
                emit(OPER("lw `d0,"
                        + ((CONST) b.left).value + "(`s0)", L(r),
                        L(munchExp(b.right))));
            }
            if (b.right instanceof CONST) {
                emit(OPER("lw `d0,"
                        + ((CONST) b.right).value + "(`s0)", L(r),
                        L(munchExp(b.left))));
            }
            return r;
        }
        emit(OPER("lw `d0,0(`s0)", L(r), L(munchExp(e.exp))));
        return r;
    }

    Temp munchExp(CALL s) {
        if (!(s.func instanceof NAME)) {
            throw new Error("Method name is not a NAME");
        }
        TempList argTemps = munchArgs(0, s.args);
        emit(new OPER("jal " + ((NAME) s.func).label,
                MipsFrame.calldefs, L(munchExp(s.func), argTemps)));
        return MipsFrame.V0;

    }

    private TempList munchArgs(int i, ExpList args) {
        if (args == null) {
            return null;
        }
        Temp src = munchExp(args.head);
        if (i > frame.maxArgs) {
            frame.maxArgs = i;
        }
        switch (i) {
            case 0:
                emit(MOVE("move `d0,`s0", frame.A0, src));
                break;
            case 1:
                emit(MOVE("move `d0,`s0", frame.A1, src));
                break;
            case 2:
                emit(MOVE("move `d0,`s0", frame.A2, src));
                break;
            case 3:
                emit(MOVE("move `d0,`s0", frame.A3, src));
                break;
            default:
                emit(OPER("sw `s0" + (i - 1) * frame.wordSize() + "(`s1)", null,
                        L(src, L(frame.SP))));
                break;
        }
        return L(src, munchArgs(i + 1, args.tail));
    }

    public InstrList getBareResult() {
        return ilist.reverse();
    }

    public String format(InstrList is, TempMap f) {
        String s = "";
        s = is.head.toString() + "";
        System.out.println(is.head);
        System.out.println(is.head.format(f));
        if (is.tail == null) {
            return s + is.head.format(f);
        } else {
            return s + is.head.format(f) + "" + format(is.tail, f);
        }
    }
}

