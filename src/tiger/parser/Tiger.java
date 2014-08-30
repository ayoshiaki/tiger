package tiger.parser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import tiger.absyn.Exp;
import tiger.absyn.Print;
import tiger.assem.InstrList;
import tiger.canon.BasicBlocks;
import tiger.canon.Canon;
import tiger.canon.StmListList;
import tiger.canon.TraceSchedule;
import tiger.frame.Frame;
import tiger.regalloc.RegAlloc;
import tiger.semant.SemantVisitor;
import tiger.temp.CombineMap;
import tiger.temp.DefaultMap;
import tiger.temp.TempMap;
import tiger.translate.DataFrag;
import tiger.translate.Frag;
import tiger.translate.ProcFrag;
import tiger.tree.LABEL;
import tiger.tree.StmList;

/**
 *
 *
 * @author yoshiaki
 */
public class Tiger {

    static InstrList codegen(Frame f, StmList stms) {
        InstrList first = null, last = null;
        for (StmList s = stms; s != null; s = s.tail) {
            InstrList i = f.codegen(s.head);
            if (last == null) {
                if (first != null) {
                    throw new Error("Main.codegen");
                }
                first = last = i;
            } else {
                while (last.tail != null) {
                    last = last.tail;
                }
                last = last.tail = i;
            }
        }
        return first;
    }

    static void emitProc(java.io.PrintWriter out, ProcFrag f) {

        java.io.PrintWriter debug =
                new java.io.PrintWriter(System.err);
        //out;
        TempMap tempmap = new CombineMap(f.frame,
                new DefaultMap());
        tiger.tree.Print print = new tiger.tree.Print(debug, tempmap);
        debug.println("PROCEDURE " + f.frame.name);
        InstrList instrs = null;
        if (f.body != null) {
            debug.println("# Before canonicalization: ");
            print.prStm(f.body);
            debug.println("# After canonicalization: ");
            StmList stms = Canon.linearize(f.body);
            print.prStmList(stms);
            debug.println("# Basic Blocks: ");
            BasicBlocks b = new BasicBlocks(stms);
            for (StmListList l = b.blocks; l != null; l = l.tail) {
                debug.println("#");
                print.prStmList(l.head);
            }
            print.prStm(new LABEL(b.done));
            debug.println("# Trace Scheduled: ");
            StmList traced = (new TraceSchedule(b)).stms;
            print.prStmList(traced);
            instrs = codegen(f.frame, traced);
        }
     
        debug.println("# Instructions: ");
        for (InstrList p = instrs; p != null; p = p.tail) {
            debug.println(p.head.assem);
            debug.flush();
        }
        debug.flush();
        
        
      RegAlloc reg = new RegAlloc(f.frame, instrs,
                System.err, false);
        
        out.println(f.frame.pre());
        for (InstrList p = instrs; p != null; p = p.tail) {
            out.println(p.head.format(reg));
            out.flush();
        }
         out.println(f.frame.post());
          out.println(".end " + f.frame.name);
      
        out.flush();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String src = args[0];
            if (src.endsWith(".tig")) {
                if (args.length > 1) {
                    System.out.println("***Compiling: " + src);
                }
                String dst = src.substring(0, src.lastIndexOf(".tig")) + ".s";
                TigerLexer lex = new TigerLexer(new ANTLRFileStream(args[0], "UTF8"));

                CommonTokenStream tokens = new CommonTokenStream(lex);
                TigerParser parser = new TigerParser(tokens);
                parser.prog();
                
                SemantVisitor semantic = new SemantVisitor();
                Frag frags = semantic.transProg(parser.tree);
                Exp tree = parser.tree;
                
                Print p = new Print(System.err);
                p.prExp(tree, 0);
                
                
                java.io.PrintWriter out = new java.io.PrintWriter(
                        new java.io.FileOutputStream(dst));
                for (Frag f = frags; f != null; f = f.next) {
                    if (f instanceof ProcFrag) {
                  
                        emitProc(out, (ProcFrag) f);
                    } else if (f instanceof DataFrag) {
                        out.println(((DataFrag) f).data);
                    }
                }
                out.close();
                
            } else {
                System.err.println("File extension is not \".tig\": ignoring "
                        + src);
            }
        } catch (IOException ex) {
            Logger.getLogger(Tiger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
