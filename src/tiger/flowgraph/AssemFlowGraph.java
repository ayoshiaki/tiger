package tiger.flowgraph;

import tiger.assem.Instr;
import tiger.assem.InstrList;
import tiger.assem.Targets;
import tiger.graph.Node;
import tiger.graph.NodeList;
import tiger.temp.LabelList;
import tiger.temp.TempList;
import tiger.assem.LABEL;
import tiger.assem.MOVE;

public class AssemFlowGraph extends FlowGraph {
	java.util.HashMap ht;

	public Instr instr(Node n) {
		return (Instr) ht.get(n);
	}

	public TempList def(Node node) {
		return instr(node).def();
	}

	public TempList use(Node node) {
		return instr(node).use();
	}

	public boolean isMove(Node node) {
		return instr(node) instanceof MOVE;
	}

	public AssemFlowGraph(InstrList instrs) {
		ht = new java.util.HashMap();
		java.util.HashMap l2n = new java.util.HashMap();

		// Add all instructions as nodes
		for (InstrList p = instrs; p != null; p = p.tail) {
			Node n = new Node(this);
			ht.put(n, p.head);
			if (p.head instanceof LABEL)
				l2n.put(((LABEL) p.head).label, n);
		}
		// Add edges from each branch instruction node to target node(s)
		for (NodeList p = nodes(); p != null; p = p.tail) {
			Node n = p.head;
			Targets jumps = (Targets) instr(n).jumps();
			if (jumps == null && p.tail != null) {
				addEdge(n, p.tail.head); // Fall through with edge to next
				// instruction
			} else if (jumps != null) { // Jumps - Edge to target label node
				LabelList l = jumps.labels;
				while (l != null) {
					addEdge(n, (Node) l2n.get(l.head));
					l = l.tail;
				}
			}
		}
	}
}