package tiger.translate;

import tiger.temp.Label;
import tiger.temp.Temp;
import tiger.tree.ESEQ;
import tiger.tree.JUMP;
import tiger.tree.LABEL;
import tiger.tree.MOVE;
import tiger.tree.NAME;
import tiger.tree.SEQ;
import tiger.tree.Stm;
import tiger.tree.TEMP;
import tiger.tree.Exp;

class IfThenElseExp extends tiger.translate.Exp {
	tiger.translate.Exp cond, a, b;

	Label t = new Label();

	Label f = new Label();

	Label join = new Label();

	IfThenElseExp(tiger.translate.Exp cc, tiger.translate.Exp aa, tiger.translate.Exp bb) {
		cond = cc;
		a = aa;
		b = bb;
	}

	private static Stm SEQ(Stm left, Stm right) {
		if (left == null)
			return right;
		if (right == null)
			return left;
		return new SEQ(left, right);
	}

	private static LABEL LABEL(Label l) {
		return new LABEL(l);
	}

	private static Exp ESEQ(Stm stm, Exp exp) {
		if (stm == null)
			return exp;
		return new ESEQ(stm, exp);
	}

	private static Stm MOVE(Exp dst, Exp src) {
		return new MOVE(dst, src);
	}

	private static Stm JUMP(Label l) {
		return new JUMP(l);
	}

	private static Exp TEMP(Temp t) {
		return new TEMP(t);
	}

	Stm unCx(Label tt, Label ff) {
		// This is the naive implementation; you should extend it to eliminate
		// unnecessary JUMP nodes
		Stm aStm = a.unCx(tt, ff);
		if (aStm instanceof JUMP) {
			JUMP aJump = (JUMP) aStm;
			if (aJump.exp instanceof NAME) {
				NAME aName = (NAME) aJump.exp;
				aStm = null;
				t = aName.label;
			}
		}
		Stm bStm = b.unCx(tt, ff);
		if (bStm instanceof JUMP) {
			JUMP bJump = (JUMP) bStm;
			if (bJump.exp instanceof NAME) {
				NAME bName = (NAME) bJump.exp;
				bStm = null;
				f = bName.label;
			}
		}

		Stm condStm = cond.unCx(t, f);

		if (aStm == null && bStm == null)
			return condStm;
		if (aStm == null)
			return SEQ(condStm, SEQ(LABEL(f), bStm));
		if (bStm == null)
			return SEQ(condStm, SEQ(LABEL(t), aStm));
		return SEQ(condStm, SEQ(SEQ(LABEL(t), aStm), SEQ(LABEL(f), bStm)));
	}

	Exp unEx() {
		// You must implement this function
		Exp aExp = a.unEx();
		if (aExp == null)
			return null;
		Exp bExp = b.unEx();
		if (bExp == null)
			return null;
		Temp r = new Temp();
		return ESEQ(SEQ(SEQ(cond.unCx(t, f), SEQ(SEQ(LABEL(t), SEQ(MOVE(
				TEMP(r), aExp), JUMP(join))), SEQ(LABEL(f), SEQ(MOVE(TEMP(r),
				bExp), JUMP(join))))), LABEL(join)), TEMP(r));
	}

	Stm unNx() {
		// You must implement this function
		Stm aStm = a.unNx();
		if (aStm == null)
			t = join;
		else
			aStm = SEQ(SEQ(LABEL(t), aStm), JUMP(join));

		Stm bStm = b.unNx();
		if (bStm == null)
			f = join;
		else
			bStm = SEQ(SEQ(LABEL(f), bStm), JUMP(join));

		if (aStm == null && bStm == null)
			return cond.unNx();

		Stm condStm = cond.unCx(t, f);

		if (aStm == null)
			return SEQ(SEQ(condStm, bStm), LABEL(join));

		if (bStm == null)
			return SEQ(SEQ(condStm, aStm), LABEL(join));

		return SEQ(SEQ(condStm, SEQ(aStm, bStm)), LABEL(join));

	}
}
