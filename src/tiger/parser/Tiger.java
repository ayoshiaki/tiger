/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiger.parser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import tiger.absyn.Exp;
import tiger.semant.Semant;

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
            /*
             tokens.fill();
             ArrayList<Token> tokenlist = (ArrayList<Token>) tokens.getTokens();
             System.out.println("Tokens qtd: " + tokenlist.size());

             for (Token t : tokenlist) {
             if (t.getType() != TigerLexer.EOF) {
             System.out.println("<" + TigerLexer.tokenNames[t.getType()] + "," + t.getText() + ">");
             }
             }
            
             */
            TigerParser parser = new TigerParser(tokens);
            parser.prog();
            Exp tree = parser.tree;
            Semant semantic = new Semant();
            semantic.transProg(tree);
        } catch (IOException ex) {
            Logger.getLogger(Tiger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
