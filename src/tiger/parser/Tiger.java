package tiger.parser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import tiger.absyn.Exp;
import tiger.absyn.Print;
import tiger.semant.SemantVisitor;

/**
 *
 *
 * @author yoshiaki
 */
public class Tiger {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println(args[0]);
            
            TigerLexer lex = new TigerLexer(new ANTLRFileStream(args[0], "UTF8"));
            
            CommonTokenStream tokens = new CommonTokenStream(lex);
            
            TigerParser parser = new TigerParser(tokens);
            parser.prog();
            
            Exp tree = parser.tree;
            
            Print p = new Print(System.out);
            p.prExp(tree, 0);
            
            SemantVisitor semantic = new SemantVisitor();
            tree.accept(semantic);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Tiger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
