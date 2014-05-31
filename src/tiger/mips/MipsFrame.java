package tiger.mips;

import java.util.HashMap;
import tiger.assem.InstrList;
import tiger.assem.OPER;
import tiger.frame.Frame;
import tiger.frame.Proc;
import tiger.symbol.Symbol;
import tiger.temp.Label;
import tiger.temp.Temp;
import tiger.temp.TempList;
import tiger.tree.CALL;
import tiger.tree.MOVE;
import tiger.tree.SEQ;
import tiger.tree.Stm;
import tiger.tree.TEMP;
import tiger.util.BoolList;



public class MipsFrame extends tiger.frame.Frame {

	private int count = 0;

	public Frame newFrame(Symbol name, BoolList formals) {
		Label label;
		if (name == null)
			label = new Label();
		else if (this.name != null)
			label = new Label(this.name + "." + name + "." + count++);
		else
			label = new Label(name);
		return new MipsFrame(label, formals);
	}

	public MipsFrame() {
		// labels in Appel's runtime.s
	}

	public MipsFrame(Label n, BoolList f) {
		name = n;
		formals = allocFormals(0, f);

	}

	private static final int wordSize = 4;

	public int wordSize() {
		return wordSize;
	}

	private static HashMap<Symbol, Integer> functions = new HashMap<Symbol, Integer>();

	private int offset = 0;

	public tiger.frame.Access allocLocal(boolean escape) {
		if (escape) {
			offset -= wordSize;
			return new InFrame(offset);
		} else
			return new InReg(new Temp());
	}

	private tiger.frame.AccessList allocFormals(int offset, BoolList formals) {
		if (formals == null)
			return null;
		tiger.frame.Access a;
		if (formals.head)
			a = new InFrame(offset);
		else
			a = new InReg(new Temp());
		return new tiger.frame.AccessList(a, allocFormals(offset + wordSize, formals.tail));
	}

	static final Temp ZERO = new Temp(); // zero reg

	static final Temp AT = new Temp(); // reserved for assembler

	static final Temp V0 = new Temp(); // function result

	static final Temp V1 = new Temp(); // second function result

	static final Temp A0 = new Temp(); // argument1

	static final Temp A1 = new Temp(); // argument2

	static final Temp A2 = new Temp(); // argument3

	static final Temp A3 = new Temp(); // argument4

	static final Temp T0 = new Temp(); // caller-saved

	static final Temp T1 = new Temp();

	static final Temp T2 = new Temp();

	static final Temp T3 = new Temp();

	static final Temp T4 = new Temp();

	static final Temp T5 = new Temp();

	static final Temp T6 = new Temp();

	static final Temp T7 = new Temp();

	static final Temp S0 = new Temp(); // callee-saved

	static final Temp S1 = new Temp();

	static final Temp S2 = new Temp();

	static final Temp S3 = new Temp();

	static final Temp S4 = new Temp();

	static final Temp S5 = new Temp();

	static final Temp S6 = new Temp();

	static final Temp S7 = new Temp();

	static final Temp T8 = new Temp(); // caller-saved

	static final Temp T9 = new Temp();

	static final Temp K0 = new Temp(); // reserved for OS kernel

	static final Temp K1 = new Temp(); // reserved for OS kernel

	static final Temp GP = new Temp(); // pointer to global area

	static final Temp SP = new Temp(); // stack pointer

	static final Temp FP = new Temp(); // virtual frame pointer (eliminated)

	static final Temp S8 = new Temp(); // actual frame pointer

	static final Temp RA = new Temp(); // return address

	public Temp FP() {
		return FP;
	}

	public Temp RV() {
		return V0;
	}

	private static HashMap labels = new HashMap();

	public tiger.tree.Exp externalCall(String func, tiger.tree.ExpList args) {
		String u = func.intern();
		Label l = (Label) labels.get(u);
		if (l == null) {
			l = new Label(u);
			labels.put(u, l);
		}
		return new CALL(new tiger.tree.NAME(l), args);
	}
	
	public Stm procEntryExit1(Stm body) {
		tiger.frame.AccessList accesses=formals;
		Stm tempBody=body;
		TempList regs=argRegs;
		while (accesses!=null){
			//System.out.println(name);
			tempBody=new SEQ(new MOVE((accesses.head.exp(new TEMP(FP))),new TEMP(regs.head)),tempBody);
			accesses=accesses.tail;
			regs=regs.tail;
		}
		return tempBody;
	}	

	public String string(Label lab, String string) {
		int length = string.length();
		String lit = "";
		for (int i = 0; i < length; i++) {
			char c = string.charAt(i);
			switch (c) {
			case '\b':
				lit += "\\b";
				break;
			case '\t':
				lit += "\\t";
				break;
			case '\n':
				lit += "\\n";
				break;
			case '\f':
				lit += "\\f";
				break;
			case '\r':
				lit += "\\r";
				break;
			case '\"':
				lit += "\\\"";
				break;
			case '\\':
				lit += "\\\\";
				break;
			default:
				if (c < ' ' || c > '~') {
					int v = (int) c;
					lit += "\\" + ((v >> 6) & 7) + ((v >> 3) & 7) + (v & 7);
				} else
					lit += c;
				break;
			}
		}
		return ".data\n" + lab.toString() + ":.word " + length + "\n"
				+ "\t.asciiz\t\"" + lit + "\"";
	}

	private static final Label badPtr = new Label("_BADPTR");

	public Label badPtr() {
		return badPtr;
	}

	private static final Label badSub = new Label("_BADSUB");

	public Label badSub() {
		return badSub;
	}

	private static final HashMap tempMap = new HashMap();
	static {
		tempMap.put(ZERO, "$0");
		tempMap.put(AT, "$at");
		tempMap.put(V0, "$v0");
		tempMap.put(V1, "$v1");
		tempMap.put(A0, "$a0");
		tempMap.put(A1, "$a1");
		tempMap.put(A2, "$a2");
		tempMap.put(A3, "$a3");
		tempMap.put(T0, "$t0");
		tempMap.put(T1, "$t1");
		tempMap.put(T2, "$t2");
		tempMap.put(T3, "$t3");
		tempMap.put(T4, "$t4");
		tempMap.put(T5, "$t5");
		tempMap.put(T6, "$t6");
		tempMap.put(T7, "$t7");
		tempMap.put(S0, "$s0");
		tempMap.put(S1, "$s1");
		tempMap.put(S2, "$s2");
		tempMap.put(S3, "$s3");
		tempMap.put(S4, "$s4");
		tempMap.put(S5, "$s5");
		tempMap.put(S6, "$s6");
		tempMap.put(S7, "$s7");
		tempMap.put(T8, "$t8");
		tempMap.put(T9, "$t9");
		tempMap.put(K0, "$k0");
		tempMap.put(K1, "$k1");
		tempMap.put(GP, "$gp");
		tempMap.put(SP, "$sp");
		tempMap.put(FP, "$fp"); // should be virtual
		tempMap.put(S8, "$s8"); // $s8 is alias for $fp (when virtual)
		tempMap.put(RA, "$ra");
	}

	public String tempMap(Temp temp) {
		if (temp == null) {
			System.out.print("haha");
		}
		if (tempMap.containsKey(temp)) {
			return (String) tempMap.get(temp);
		} else {
			return temp.toString();
		}
	}

	static TempList L(Temp h, TempList t) {
		return new TempList(h, t);
	}

	static TempList L(Temp h) {
		return new TempList(h, null);
	}

	static TempList L(TempList a, TempList b) {
		return new TempList(a, b);
	}

	static final int callerSavesOffset = 0;

	static final int calleeSavesOffset = 0;

	// Register lists: must not overlap and must include every register that
	// might show up in Assem instructions
	static TempList specialRegs, argRegs, tempSaves, callerSaves, calleeSaves;
	{
		// registers dedicated to special purposes
		specialRegs = L(ZERO, L(AT, L(K0, L(K1, L(GP, L(FP, L(SP, L(RA))))))));
		// registers in which to pass outgoing arguments (including static link)
		argRegs = L(A0, L(A1, L(A2, L(A3))));
		// registers that the called procedure (callee) must preserve for caller
		calleeSaves = L(S0, L(S1, L(S2,
				L(S3, L(S4, L(S5, L(S6, L(S7, L(S8)))))))));
		// registers that the callee may trash
		tempSaves = L(T0, L(T1, L(T2, L(T3, L(T4, L(T5, L(T6, L(T7,
				L(T8, L(T9))))))))));
		callerSaves = L(V0, L(V1, tempSaves));
	}

	static InstrList append(InstrList a, InstrList b) {
		return new InstrList(a, b);
	}

	static TempList append(TempList a, TempList b) {
		return new TempList(a, b);
	}

	static TempList calldefs, returnSink;
	{
		// registers defined by a call
		calldefs = L(RA, L(argRegs, callerSaves));
		// registers live on return
		returnSink = L(V0, L(specialRegs, calleeSaves));
	}

	private InstrList unbumpSP() {
		return new InstrList(new OPER(
				"addu `d0, `s0, " + (-offset), L(SP, null), L(SP, null)), null);
	}

	private InstrList saveCalleeSaves() {
		InstrList result = null;
		int saveOffset = calleeSavesOffset;
		TempList t = calleeSaves;
		for (; t != null; t = t.tail) {
			result = new InstrList(new OPER("sw `s0, " + saveOffset
					+ " (`s1)", null, L(t.head, L(SP, null))), result);
			saveOffset -= wordSize;
		}
		return result.tail;
	}

	private InstrList restoreCalleeSaves() {
		InstrList result = null;
		int restoreOffset = calleeSavesOffset;
		TempList t = calleeSaves;
		for (; t != null; t = t.tail) {
			result = new InstrList(new OPER("sw `s0, "
					+ restoreOffset + "(`s1)", L(t.head, null), L(SP, null)),
					result);
			restoreOffset -= wordSize;
		}
		return result.tail;
	}

	private InstrList bumpSP() {
		return new InstrList(new tiger.assem.MOVE("move `d0, `s0", FP, SP),
				new InstrList(new OPER("subu `d0, `s0, "
						+ (-offset), L(SP, null), L(SP, null)), null));
	}

	public InstrList procEntryExit2(InstrList body) {
		tiger.assem.Instr entry = body.head;
		body = body.tail;
		body = append(bumpSP(), body);
		body = append(saveCalleeSaves(), body);
		body = append(body, unbumpSP());
		body = append(body, restoreCalleeSaves());
		body = append(body, new InstrList(new OPER("jr `s0", null,
				L(RA, returnSink)), null));
		return new InstrList(entry, body);
	}

	int maxArgs = 0;

	public Proc procEntryExit3(InstrList body) {
		int frameSize = maxArgs * wordSize - offset;
		String pre = "\t.text\n" + name + ":\n" + name + "_framesize="
				+ frameSize;
		String post = "\tj $ra";
		if (frameSize != 0) {
			pre += "\n\tsubu $sp " + name + "_framesize";
			post = "\taddu $sp " + name + "_framesize\n" + post;
		}
		return new Proc(pre, body, post);
	}

	public String pre() {
		int frameSize = maxArgs * wordSize - offset + 80;
		int i;

		String pr = ".text\n" + name + ":\n" + name + "_framesize=" + frameSize;
		if (frameSize != 0) {
			pr += "\nsub $sp," + name + "_framesize";
		}
		TempList callee = calleeSaves;
		int offs = offset;
		offs -= wordSize;
		pr = pr + "\nsw " + "$ra" + "," + offs + "+" + name + "_framesize($sp)";
		while (callee != null) {
			offs -= wordSize;
			pr = pr + "\nsw " + tempMap(callee.head) + "," + offs + "+" + name
					+ "_framesize($sp)";
			callee = callee.tail;
		}
		pr = pr + "\n";
		return pr;
	}

	public String post() {
		int frameSize = maxArgs * wordSize - offset + 80;
		String po = "";
		TempList callee = calleeSaves;
		int offs = offset;
		offs -= wordSize;
		po = po + "\nlw " + "$ra" + "," + offs + "+" + name + "_framesize($sp)";
		while (callee != null) {
			offs -= wordSize;
			po = po + "\nlw " + tempMap(callee.head) + "," + offs + "+" + name
					+ "_framesize($sp)";
			callee = callee.tail;
		}
		if (frameSize != 0) {
			po = po + "\naddu $sp," + name + "_framesize\n";
		}
		po = po + "\njr $ra\n";
		return po;
	}

	public InstrList codegen(Stm stm) {
		return (new Codegen(this)).codegen(stm);
	}

	@Override
	public Temp SP() {
		// TODO Auto-generated method stub
		return SP;
	}

	// Return an array of registers available for register allocation
	public TempList colors() {
		TempList colors = null;
		// colors=append(colors,callerSaves);
		colors = append(colors, tempSaves);
		// colors=append(colors,argRegs);
		return colors;
	}

	@Override
	public Frame newFrame(Label name, BoolList formals) {
		// TODO Auto-generated method stub
		return null;
	}

	public TempList registers() {
		TempList registers = null;
		registers = append(registers, callerSaves);
		registers = append(registers, calleeSaves);
		registers = append(registers, argRegs);
		registers = append(registers, specialRegs);
		return registers;
	}

 

}
