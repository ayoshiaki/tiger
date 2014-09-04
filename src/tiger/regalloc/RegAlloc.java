package tiger.regalloc;


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import tiger.assem.InstrList;
import tiger.flowgraph.AssemFlowGraph;
import tiger.flowgraph.FlowGraph;
import tiger.frame.Frame;
import tiger.graph.Node;
import tiger.temp.Temp;
import tiger.temp.TempList;
import tiger.temp.TempMap;



// why should they both implement TempMap??????

public class RegAlloc implements TempMap {

	private regCompare regCom = new regCompare(); 
		
	private java.io.PrintStream oStream;

	private boolean verbose;

	private Frame frame;

	private InstrList instrs;

	private InterferenceGraph ig;

	private Color color;

	private List<Temp> regs = new ArrayList<Temp>(32);

	public String tempMap(Temp temp) {
		return color.tempMap(temp);
	}

	public String getResult() {
		return null;
	}

	public RegAlloc(Frame f, InstrList il, PrintStream oStream,
			boolean verbose) {
		// I hope this frame is a procedure frame, because I'm
		// assuming register allocation is done per procedure.
		frame = f;
		instrs = il;
		TempList tlist = f.registers();
		for (; tlist != null; tlist = tlist.tail) {
			regs.add(tlist.head);
		}
		Set<Temp> colors = new TreeSet<Temp>(regCom);
		 
		tlist = f.colors();
		for (; tlist != null; tlist = tlist.tail) {
			colors.add(tlist.head);
		}
		this.oStream = oStream;
		this.verbose = verbose;
		while (true) {
			build();
			color = new Color(ig, f, regs, colors, verbose, oStream);
			// After coloring, check the list of spills.
			// If it's empty, we're done.
			if (color.spills.isEmpty()) {
				break;
			}
			// Otherwise
			// (fail in the simple version)
			// patch save/restore code into the il and start again.
			rewrite(color.spills);
		}
	}

	private void rewrite(List<Temp> spills) {
		throw new Error("Register allocation failed: too many interferences.");
	}

	private void build() {

		// Pass instrs to liveness analysis, and get the resulting interference
		// graph
		FlowGraph flow = new AssemFlowGraph(instrs);
		if (verbose)
			flow.show(oStream);
		ig = new Liveness((AssemFlowGraph) flow);
                
               // Add all the registers to ig if not already there (these are the
		// precolored nodes)
		// Each one should interfere with all other register nodes
		List<Node> regNodes = new LinkedList<Node>();
		for (Temp reg : regs) {		
			regNodes.add(ig.tnode(reg)); // this might be a brand new node
		}
		// Now we have a list of all the register nodes; make sure each has an
		// edge to all other register nodes
		for (Node r1 : regNodes) {	
			for (Node r2 : regNodes) {
				if (r1 != r2) {
					((Liveness) ig).addEdge(r1, r2);
					((Liveness) ig).addEdge(r2, r1);
				}
			}
		}
	
			//ig.show(oStream);
	}
}