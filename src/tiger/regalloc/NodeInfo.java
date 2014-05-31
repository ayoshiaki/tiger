package tiger.regalloc;

import java.util.HashSet;
import java.util.Set;
import tiger.graph.Node;
import tiger.graph.NodeList;
import tiger.temp.Temp;


public class NodeInfo {
	int degree;

	boolean precolored;

	Temp color;

	Set<Node> adj = new HashSet<Node>();

	MoveList moves;

	static enum Membership {
		NONE, SIMPLIFY, FREEZE, SPILL, STACK
	};

	Membership worklist = Membership.NONE;

	void setDegree(int d) {
		degree = d;
	}

	void setPrecolored() {
		precolored = true;
	}

	void setVirtual() {
		precolored = false;
	}

	void setNeighbors(NodeList ns) {
		for (; ns != null; ns = ns.tail)
			adj.add(ns.head);
	}

	void setNotOnStack() {
		worklist = Membership.NONE;
	}

	void setSimplifyMember() {
		worklist = Membership.SIMPLIFY;
	}

	void setStackMember() {
		worklist = Membership.STACK;
	}

	void setSpillMember() {
		worklist = Membership.SPILL;
	}

	void setColor(Temp c) {
		color = c;
	}

	void addMove(Node src, Node dst) {
		moves = new MoveList(src, dst, moves);
	}

	boolean isOnStack() {
		return worklist == Membership.STACK;
	}

	boolean isCoalesced() {
		return false;
	}
}
