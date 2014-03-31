package tiger.semant;

import tiger.absyn.ArrayExp;
import tiger.absyn.AssignExp;
import tiger.absyn.BreakExp;
import tiger.absyn.CallExp;
import tiger.absyn.Exp;
import tiger.absyn.ForExp;
import tiger.absyn.IfExp;
import tiger.absyn.IntExp;
import tiger.absyn.LetExp;
import tiger.absyn.OpExp;
import tiger.absyn.RecordExp;
import tiger.absyn.SeqExp;
import tiger.absyn.StringExp;
import tiger.absyn.VarExp;
import tiger.absyn.WhileExp;

public interface Visitor {

    public ExpTy transExp(Exp e);

    public ExpTy transExp(ArrayExp e);

    public ExpTy transExp(AssignExp e);

    public ExpTy transExp(BreakExp e);

    public ExpTy transExp(CallExp e);

    public ExpTy transExp(ForExp e);

    public ExpTy transExp(IfExp e);

    public ExpTy transExp(IntExp e);

    public ExpTy transExp(LetExp e);

    public ExpTy transExp(OpExp e);

    public ExpTy transExp(RecordExp e);

    public ExpTy transExp(SeqExp e);

    public ExpTy transExp(StringExp e);

    public ExpTy transExp(VarExp e);

    public ExpTy transExp(WhileExp e);
}
