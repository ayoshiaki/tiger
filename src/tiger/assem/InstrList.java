package tiger.assem;

public class InstrList {
	public Instr head;

	public InstrList tail;

	public InstrList(Instr h, InstrList t) {
		head = h;
		tail = t;
	}

	public InstrList reverse() {
		InstrList temp = null;
		InstrList now = this;
		while (now != null) {
			temp = new InstrList(now.head, temp);
			now = now.tail;
		}
		return temp;
	}

	public InstrList(InstrList h, InstrList t) {

		InstrList temp = null;
		while (h != null) {
			temp = new InstrList(h.head, temp);
			h = h.tail;
		}
		tail = t;

		while (temp != null) {
			tail = new InstrList(temp.head, tail);
			temp = temp.tail;
		}
		
		temp=tail;
		head=tail.head;
		tail=tail.tail;
	}

	public void print() {
		InstrList temp = this;
		while (temp != null){
			if (temp.head==null) throw new Error("1000");
			System.out.println(temp.head.assem);
			temp = temp.tail;
		}
	}
}
