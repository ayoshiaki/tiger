package tiger.parser;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
import tiger.temp.Temp;
import tiger.temp.TempMap;
import tiger.translate.DataFrag;
import tiger.translate.Frag;
import tiger.translate.ProcFrag;
import tiger.tree.LABEL;
import tiger.tree.StmList;

/**
 *
 *
 *
 * @author yoshiaki
 */
public class Tiger {

    private static boolean sintaticerror = false;
    private static boolean semanticerror = false;

    protected static void setSintaticerror() {
        sintaticerror = true;
    }

    protected static void resetSintaticerror() {
        sintaticerror = false;
    }

    protected static void setSemanticerror() {
        semanticerror = true;
    }

    protected static void resetSemanticerror() {
        semanticerror = false;
    }

    public static boolean hasSintaticerror() {
        return sintaticerror;
    }

    public static boolean hasSemanticerror() {
        return semanticerror;
    }

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

    // classe emitproc alterada, redirecionei a saida do print para a printwriter, dps eh soh jogar no frame de texto
    static void emitProc(java.io.PrintWriter out, ProcFrag f) {

        java.io.PrintWriter debug = new java.io.PrintWriter(System.err);

        //out;
        //intermediária antes canonização
        StringWriter inter_b = new StringWriter();
        java.io.PrintWriter p_inter_b = new java.io.PrintWriter(inter_b);
        StringWriter instruc = new StringWriter();
        java.io.PrintWriter p_instruc = new java.io.PrintWriter(instruc);
        //interface depois da canonização
        StringWriter inter_a = new StringWriter();
        java.io.PrintWriter p_inter_a = new java.io.PrintWriter(inter_a);
        //basic blocks
        StringWriter basic_b = new StringWriter();
        java.io.PrintWriter p_basic_b = new java.io.PrintWriter(basic_b);

        TempMap tempmap = new CombineMap(f.frame,
                new DefaultMap());
        tiger.tree.Print print = new tiger.tree.Print(debug, tempmap);
        debug.println("PROCEDURE " + f.frame.name);
        InstrList instrs = null;
        if (f.body != null) {
            p_inter_b.println("# Before canonicalization: ");
            print.printOut(p_inter_b);
            print.prStm(f.body);
            //System.out.println(inter_b);
            tiger.parser.FormPrincipal.setAi1(inter_b.toString());
            print.printOut(debug);
            p_inter_a.println("# After canonicalization: ");
            print.printOut(p_inter_a);
            StmList stms = Canon.linearize(f.body);
            print.prStm(f.body);
            tiger.parser.FormPrincipal.setAi2(inter_a.toString());
            // print.printOut(debug);
            p_basic_b.println("# Basic Blocks: ");
            BasicBlocks b = new BasicBlocks(stms);
            p_basic_b.println("# Trace Scheduled: ");
            print.printOut(p_basic_b);
            StmList traced = (new TraceSchedule(b)).stms;
            print.prStmList(traced);
            instrs = codegen(f.frame, traced);

            tiger.parser.FormPrincipal.setBb(basic_b.toString());
        }

        p_instruc.println("# Instructions: ");
        for (InstrList p = instrs; p != null; p = p.tail) {
            p_instruc.println(p.head.assem);
            p_instruc.flush();
        }
        tiger.parser.FormPrincipal.setIn(instruc.toString());

        RegAlloc reg = new RegAlloc(f.frame, instrs, System.err, false);

        out.println(f.frame.pre());
        for (InstrList p = instrs; p != null; p = p.tail) {
            out.println(p.head.format(reg));
            out.flush();
        }
        out.println(f.frame.post());
        out.println(".end " + f.frame.name);

        out.flush();

    }

    private static String indicateError(String s) {
        String begin, end;
        begin = s.substring(0, s.indexOf(".s"));
        end = s.substring(s.indexOf(".s"));
        s = begin + "ERROR" + end;
        return s;
    }

    /**
     * @param args the command line arguments
     */
    public static void compileCode(String fileTig) {

        try {
            String src = fileTig;
            if (src.endsWith(".tig")) {
                if (fileTig.length() > 1) {
                    System.out.println("***Compiling: " + src);
                }
                String dst = src.substring(0, src.lastIndexOf(".tig")) + ".s";
                TigerLexer lex = new TigerLexer(new ANTLRFileStream(fileTig, "UTF8"));

                CommonTokenStream tokens = new CommonTokenStream(lex);
                TigerParser parser = new TigerParser(tokens);
                parser.prog();

                Temp.setCount(0);
                
                SemantVisitor semantic = new SemantVisitor();
                Frag frags = semantic.transProg(parser.tree);
                if (semantic.getNumberOfSemanticErrors() > 0 || parser.getNumberOfSyntaxErrors() > 0) {
                    if (semantic.getNumberOfSemanticErrors() > 0) {
                        setSemanticerror();
                    }
                    if (parser.getNumberOfSyntaxErrors() > 0) {
                        setSintaticerror();
                    }
                    dst = indicateError(dst);
                }
                Exp tree = parser.tree;
                StringWriter arvabs = new StringWriter();
                PrintWriter p_arvabs = new PrintWriter(arvabs);
                p_arvabs.println("# Árvore Abstrata Sintática: ");
                Print p = new Print(p_arvabs);
                p.prExp(tree);
                System.err.println(arvabs);
                tiger.parser.FormPrincipal.astTxt(arvabs.toString());
                // System.err.println("\n\n");
                //System.err.println("------------------");
                //System.err.println("Árvore Sintática Abstrata");
                //Print p = new Print(System.err);
                //p.prExp(tree);
                //System.err.println("\n------------------");
                //System.err.println("\n\n");

                java.io.PrintWriter out = new java.io.PrintWriter(
                        new java.io.FileOutputStream(dst));
                for (Frag f = frags; f != null; f = f.next) {
                    if (f instanceof ProcFrag) {

                        emitProc(out, (ProcFrag) f);
                    } else if (f instanceof DataFrag) {
                        out.println(((DataFrag) f).data);
                    }
                }
                JOptionPane.showMessageDialog(null, "Aquivo compilado com sucesso!!!", "Criação do Arquivo .s", JOptionPane.INFORMATION_MESSAGE);
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
