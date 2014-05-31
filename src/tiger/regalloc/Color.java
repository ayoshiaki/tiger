package tiger.regalloc;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import tiger.graph.Node;
import tiger.graph.NodeList;
import tiger.temp.Temp;
import tiger.temp.TempMap;



public class Color implements TempMap {
	private regCompare regCom = new regCompare();

	boolean verbose;

	PrintStream oStream;

	InterferenceGraph ig;

	// precolored: the set of precolored nodes (frame's registers)
	Set<Temp> precolored;

	Set<Node> virtualRegs = new HashSet<Node>();

	// colors: the set of nodes available for coloring
	Set<Temp> colors;

	// the node info structure
	private Map<Node, NodeInfo> infoMap = new HashMap<Node, NodeInfo>();

	private NodeInfo nodeInfo(Node n) {
		if (infoMap.get(n) == null) {
			throw new Error("Node " + n + " not mapped to any info!");
		}
		return infoMap.get(n);
	}

	// the stack
	LinkedList<Node> selectStack = new LinkedList<Node>();

	private void push(Node n) {
		selectStack.addFirst(n);
	}

	private Node pop() {
		return selectStack.removeFirst();
	}

	// the simplify worklist
	LinkedList<Node> simplifyWorkList = new LinkedList<Node>();

	private void addSimplifyCandidate(Node n) {
		simplifyWorkList.add(n);
		nodeInfo(n).setSimplifyMember();
	}

	private Node removeSimplifyCandidate() {
		Node r = simplifyWorkList.removeFirst();
		nodeInfo(r).setStackMember();
		return r;
	}

	// the spilled temps
	List<Temp> spills = new ArrayList<Temp>();

	// the spill worklist
	LinkedList<Node> spillWorkList = new LinkedList<Node>();

	private void addSpillCandidate(Node n) {
		spillWorkList.add(n);
		nodeInfo(n).setSpillMember();
	}

	private Node removeSpillCandidate() {
		Node r = spillWorkList.removeFirst();
		addSimplifyCandidate(r);
		return r;
	}

	// the move worklist
	MoveList workListMoves;

	// map each temp to a machine register; for precoloreds, it's the identity
	// mapping

	private TempMap initialColoring;

	private Map<Temp, Temp> coloring = new HashMap<Temp, Temp>();

	private int numColors;

	public String tempMap(Temp t) {
		if (t == null) {
			throw new Error("Attempted to color map a null");
		}
		if (!coloring.containsKey(t)) {
			throw new Error("No color for the temp " + t);
		}
		return initialColoring.tempMap(coloring.get(t));
	}

	private void initialize(TempMap initial) {
		initialColoring = initial;
		numColors = colors.size();

		for (Temp register : precolored) {
			coloring.put(register, register);
		}
		// Idea: don't modify the interference graph structure at all. Instead,
		// keep the following data:
		// adjacent: mapping of each node to its "remaining" neighbors, i.e.
		// those in graph not on stack (or coalesced, later)
		// virtualRegs: set of all temps (nodes?) used in the program that are
		// not precolored
		// simplifyWorklist: list of low-degree (non-move) nodes
		// ( no freezeWorklist, spillWorklist, spilledNodes, coalescedNodes )
		// Additionally: a mapping from nodes to enum value indicating which of
		// these disjoint sets they are in
		// Later, initialize also: movelist, alias
		// Put the insignificant-degree nodes into simplifyWorklist
		// Now we should have the invariants:
		// 1.
		// u in simplifyWorklist ( U freezeWorklist U spillWorklist )
		// implies
		// degree(u) = cardinality of
		// adjList(u) intersect
		// (precolored U simplifyWorklist ( U freezeWorklist U spillWorklist ) )

		// 2. Either u has been selected for spilling, or
		// u in simplifyWorklist
		// implies
		// degree(u) < K & moveList[u] intersect (activeMoves U worklistMoves) =
		// {}

		// 3.
		// u in freezeWorkList
		// implies
		// degree(u) < K & moveList[u] intersect (activeMoves U worklistMoves)
		// != {}

		// 4.
		// u in spillWorklist implies degree(u) >= K
		for (NodeList nodes = ig.nodes(); nodes != null; nodes = nodes.tail) {			
			Node node = nodes.head;
			NodeInfo info = new NodeInfo();
			infoMap.put(node, info);
			info.setNotOnStack();
			info.setNeighbors(node.adj());
			if (!precolored.contains(ig.gtemp(node))) { // a "virtual" register
				virtualRegs.add(node);
				info.setVirtual();
				info.setDegree(info.adj.size());
				if (info.degree < numColors) {
					addSimplifyCandidate(node);
				} else {
					addSpillCandidate(node);
				}
			} else { // a machine register
				info.setPrecolored();
				info.setColor(ig.gtemp(node));
				info.setDegree(Integer.MAX_VALUE);
			}
		}
		// set up the move worklist and mark the nodes move-related
		for (MoveList ms = ig.moves(); ms != null; ms = ms.tail) {
			nodeInfo(ms.src).addMove(ms.src, ms.dst);
			nodeInfo(ms.dst).addMove(ms.src, ms.dst);
		}
		workListMoves = ig.moves();
	}

	private Set<Node> adjacent(Node n) {
		Set<Node> result = new HashSet<Node>();
		for (Node m : nodeInfo(n).adj) {
			if (!nodeInfo(m).isOnStack() && !nodeInfo(m).isCoalesced()) {
				result.add(m);
			}
		}
		return result;
	}

	private void decrementDegree(Node n) {
		NodeInfo info = nodeInfo(n);
		int d = info.degree;
		info.setDegree(d - 1);
		if (d == numColors) {
			spillWorkList.remove(n);
			addSimplifyCandidate(n);
		}
	}

	private void simplify() {
		Node n = removeSimplifyCandidate();
		push(n);
		for (Node neighbor : adjacent(n)) {
			decrementDegree(neighbor);
		}
	}

	private void selectSpill() {
		removeSpillCandidate();
	}

	// For coalescing
	private Node getAlias(Node n) {
		return n;
	}

	private void assignColors() {

		while (!selectStack.isEmpty()) {
			Node n = pop();
			if (verbose) {
				oStream.println("Coloring node " + n + " for temp "
						+ ig.gtemp(n));
			}
			TreeSet<Temp> okColors = new TreeSet<Temp>(regCom);
			okColors.addAll(colors);
			Temp color;
			for (NodeList neighbors = n.adj(); neighbors != null; neighbors = neighbors.tail) {
				Node trueName = getAlias(neighbors.head);
				if ((color = nodeInfo(trueName).color) != null) {
					okColors.remove(color);
					if (verbose) {
						oStream.println("Neighbor has color " + color);
					}
				}
			}
			if (okColors.isEmpty()) {
				if (verbose) {
					oStream.println("Spilling node " + n + " for temp "
							+ ig.gtemp(n));
				}
				spills.add(ig.gtemp(n));
			} else {
				color = okColors.first();
				nodeInfo(n).setColor(color);
				coloring.put(ig.gtemp(n), color);
				if (verbose) {
					oStream.println("Coloring node " + n + " for temp "
							+ ig.gtemp(n) + " with color " + color);
				}
			}
			// if coalescing, color all the coalesced nodes too
		}
		if (verbose) {
			oStream.println("Coloring complete");
			// for ( Node n : virtualRegs ) {
			// oStream.println("Virtual register: "+ig.gtemp(n)+", machine
			// register: "+coloring.get(ig.gtemp(n)));
			// }
		}
	}

	private String wlToString(List<Node> wl) {
		String result = "[ ";
		for (Node n : wl) {
			result += ig.gtemp(n) + " ";
		}
		return result + "]";
	}

	private void coalesce() {
		throw new Error("Coalescing not implemented");
	}

	public Color() {
	};

	public Color(InterferenceGraph ig, TempMap initial, List<Temp> registers,
			Set<Temp> colors, boolean verbose, PrintStream oStream) {
		this.verbose = verbose;
		this.oStream = oStream;
		this.ig = ig;
		precolored = new TreeSet<Temp>(regCom);
		precolored.addAll(registers);
		if (verbose)
			System.out.println(precolored.toString());
		this.colors = colors;

		initialize(initial);
		do {
			if (verbose) {
				oStream.println("Simplify iteration: ");
				oStream.println(" simplify worklist = "
						+ wlToString(simplifyWorkList));
				oStream.println(" spill worklist = "
						+ wlToString(spillWorkList));
			}
			if (!simplifyWorkList.isEmpty())
				simplify();
			// else if ( ! worklistMoves.isEmpty() )
			// coalesce();
			// else if ( ! freezeWorklist.isEmpty() )
			// freeze();
			else if (!spillWorkList.isEmpty())
				selectSpill();
		} while ((!simplifyWorkList.isEmpty()) // || (!
												// workListmoves.isEmpty())
				// || (! freezeWorkList.isEmpty())
				|| (!spillWorkList.isEmpty()));
		assignColors();
	}
}