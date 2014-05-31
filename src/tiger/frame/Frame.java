package tiger.frame;

import tiger.assem.InstrList;
import tiger.temp.Label;
import tiger.temp.Temp;
import tiger.temp.TempList;
import tiger.temp.TempMap;
import tiger.tree.Stm;
import tiger.util.BoolList;



public abstract class Frame implements TempMap {
    public Label name;
    public tiger.frame.AccessList formals;

    static final Label error = new Label("error");

    public static Label getError() { return error; }

    abstract public Frame newFrame(Label name, BoolList formals);
    abstract public Access allocLocal(boolean escape);
    abstract public Temp FP();
    abstract public Temp SP();
    abstract public Temp RV();
    abstract public TempList registers();
    abstract public TempList colors();
    abstract public String tempMap(Temp temp);
    abstract public int wordSize();
    abstract public tiger.tree.Exp externalCall(String func, tiger.tree.ExpList args);
    abstract public String string(Label lab, String lit);
    abstract public Stm procEntryExit1(Stm body);
    abstract public InstrList procEntryExit2(InstrList body);
    abstract public Proc procEntryExit3(InstrList body);
    abstract public InstrList codegen(Stm stm);
    abstract public String pre();
    abstract public String post();
    //abstract public static String programTail();
}