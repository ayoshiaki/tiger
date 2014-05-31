package tiger.regalloc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import tiger.flowgraph.AssemFlowGraph;
import tiger.graph.Graph;
import tiger.graph.Node;
import tiger.graph.NodeList;
import tiger.temp.Temp;
import tiger.temp.TempList;

/**
 * Liveness analysis following Appel - Compiler Implementation
 * 
 * @author unknow. Modified by Ray Wisman for Appel text
 * @version 1.0
 * 
 */

public class Liveness extends InterferenceGraph {
	boolean debug = false;

	private Map liveIntervals;

	MoveList movelist = null;

	Hashtable temp2node = new Hashtable<Temp, TempNode>();

	Hashtable nodeuses = new Hashtable();

	// Utility class for describing sets of register temporary operands.
	static class TempSet {
		HashSet set; // contains Temp

		TempSet() {
			set = new HashSet();
		}

		TempSet(TempList t) {
			set = new HashSet<Temp>();
			if (t == null)
				return;
			for (TempList temp = t; temp != null; temp = temp.tail)
				add(temp.head);
		}

		void add(Temp t) { // only add operands that are Temp's
			set.add(t);
		}

		public boolean equals(Object os) {
			return (os instanceof TempSet) && (((TempSet) os).set.equals(set)); // expensive!
		}

		void diff(TempSet os) {
			for (Iterator it = os.set.iterator(); it.hasNext();)
				set.remove(it.next());
		}

		void union(TempSet os) {
			for (Iterator it = os.set.iterator(); it.hasNext();)
				set.add(it.next());
		}

		TempSet copy() {
			TempSet s = new TempSet();
			s.set = (HashSet) set.clone();
			return s;
		}

		public String toString() {
			String r = "{ ";
			for (Iterator it = set.iterator(); it.hasNext();)
				r += it.next() + " ";
			r += "}";
			return r;
		}

		Iterator iterator() {
			return set.iterator();
		}
	}

	// Utility class for describing lists of integers
	static class IndexList {
		List list;

		IndexList() {
			list = new ArrayList();
		}

		void add(int i) {
			list.add(new Integer(i));
		}

		int get(int p) {
			return ((Integer) list.get(p)).intValue();
		}

		int size() {
			return list.size();
		}

		public String toString() {
			String r = "[";
			if (size() > 0) {
				r += get(0);
				for (int i = 1; i < size(); i++)
					r += "," + get(i);
			}
			r += "]";
			return r;
		}

		public void print() {
			System.out.println(toString());
		}
	}

	private Node[] l2a(NodeList nl) {
		int n = 0;
		for (NodeList p = nl; p != null; p = p.tail)
			n++;

		Node[] a = new Node[n];
		int i = 0;
		for (NodeList p = nl; p != null; p = p.tail, i++)
			a[i] = p.head;
		return a;
	}

	private int indexOf(Node n, Node[] nodes) {
		for (int i = 0; i < nodes.length; i++)
			if (n == nodes[i])
				return i;
		return -1;
	}

	public Liveness(AssemFlowGraph afg) {
		Node[] nodes = l2a(afg.nodes());
		TempSet[] liveIn = new TempSet[nodes.length];
		TempSet[] liveOut = new TempSet[nodes.length];
		TempSet[] def = new TempSet[nodes.length];
		TempSet[] use = new TempSet[nodes.length];

		for (int i = 0; i < nodes.length; i++) {
			liveIn[i] = new TempSet();
			liveOut[i] = new TempSet();
			def[i] = new TempSet(afg.def(nodes[i]));
			use[i] = new TempSet(afg.use(nodes[i]));
		}

		boolean changed = true;
		int it = 1;
		if (debug) {
			print("use", use);
			print("def", def);
		}
		while (changed) {
			changed = false;
			if (debug) {
				System.out.println("----------------" + it + "------------");
				print("in", liveIn);
				print("out", liveOut);
			}
			for (int i = nodes.length - 1; i >= 0; i--) {
				TempSet newLiveIn = liveOut[i].copy();
				newLiveIn.diff(def[i]);
				newLiveIn.union(use[i]);
				liveIn[i] = newLiveIn;
				TempSet newLiveOut = new TempSet();
				Node[] allSuccs = l2a(nodes[i].succ());
				for (int n = 0; n < allSuccs.length; n++)
					newLiveOut.union(liveIn[indexOf(allSuccs[n], nodes)]);

				if (!liveOut[i].equals(newLiveOut)) {
					liveOut[i] = newLiveOut;
					changed = true;
				}
			}
		}
		// for each node in the flow graph
		for (int n = 0; n < nodes.length; n++)
			// for each definition in that node
			for (Iterator d = def[n].iterator(); d.hasNext();) {
				Temp dt = (Temp) d.next();
				// for each live temporary at that node
				for (Iterator t = liveOut[n].iterator(); t.hasNext();)
					// add interference edges, conditionally
					addInterferenceEdges(afg, nodes[n], dt, (Temp) t.next());
			}
		calculateLiveIntervals(liveOut);
	}

	private void print(String heading, TempSet[] t) {
		System.out.print(heading);
		for (int i = 0; i < t.length; i++)
			System.out.println("\t" + i + ":" + t[i].toString());
	}

	static class Interval {
		int start;

		int end;

		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	// calculate live interval for each temporary in body
	public Map calculateLiveIntervals(TempSet[] liveOut) {
		liveIntervals = new HashMap(); // keys are Temp; values are Interval
		for (int i = 0; i < liveOut.length; i++) {
			for (Iterator it = liveOut[i].iterator(); it.hasNext();) {
				Temp t = (Temp) (it.next());
				Interval n = (Interval) (liveIntervals.get(t));
				if (n == null) {
					n = new Interval(i, i);
					liveIntervals.put(t, n);
				} else
					n.end = i;
			}
		}
		return liveIntervals;
	}

	private void addInterferenceEdges(AssemFlowGraph flow,
			Node flownode, Temp deftemp, Temp livetemp) {
		TempList s = flow.use(flownode);
		// if this is a move instruction, maybe we don't want to add this edge
		if (flow.isMove(flownode))
			// see if this livetemp is part of the source of this move
			for (TempList t = s; t != null; t = t.tail)
				// if so, don't add an edge
				if (t.head == livetemp)
					return;

		// tests okay, add the edge.
		addEdge(tnode(deftemp), tnode(livetemp));
		addEdge(tnode(livetemp), tnode(deftemp));
	}

	public String toString() {
		String result = "";
		Set lis = liveIntervals.entrySet();
		for (Iterator it = lis.iterator(); it.hasNext();) {
			Map.Entry me = (Map.Entry) (it.next());
			Temp t = (Temp) (me.getKey());
			Liveness.Interval n = (Liveness.Interval) (me.getValue());
			result = result + "" + t + "\t[" + n.start + "," + n.end + "]\n";
		}
		return result;
	}

	public Node newNode(Temp t) {
		TempNode n = new TempNode(this, t);
		temp2node.put(t, n);
		return n;
	}

	/**
	 * @param temp
	 *            a temporary used in the flow graph
	 * @return the interference graph node corresponding to the temporary.
	 */
	public Node tnode(Temp temp) {
		Node n = (Node) temp2node.get(temp);
		if (n == null)
			// make new nodes on demand.
			return newNode(temp);
		else
			return n;
	}

	/**
	 * @param node
	 *            a node in the interference graph.
	 * @return the temporary corresponding to the node.
	 */
	public Temp gtemp(Node node) {
		if (!(node instanceof TempNode))
			throw new Error("Node " + node.toString()
					+ " not a member of graph.");
		else
			return ((TempNode) node).temp;
	}

	public MoveList moves() {
		return movelist;
	}
}

/**
 * Sub-class to associate a temporary with a Node.
 */
class TempNode extends Node {
	Temp temp;

	TempNode(Graph g, Temp t) {
		super(g);
		temp = t;
	}

	public String toString() {
		return temp.toString(); // +"("+super.toString()+")";
	}

	public int hashCode() {
		return temp.hashCode();
	}
}
