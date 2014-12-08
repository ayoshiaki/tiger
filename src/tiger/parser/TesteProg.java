/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiger.parser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import tiger.semant.SemantVisitor;
import tiger.translate.Frag;

/**
 *
 * @author Guilherme
 */
public class TesteProg {
    
    private int numberOfSyntaxError, numberOfSemanticError;
    
    
    
    public TesteProg(String path, boolean arquivo){
        
        try {
            TigerLexer lex;
            if(arquivo){
                lex = new TigerLexer(new ANTLRFileStream(path,"UTF8"));
            }else{
                lex = new TigerLexer(new ANTLRInputStream(path));
            }
            CommonTokenStream tokens= new CommonTokenStream(lex);
            TigerParser parser= new TigerParser(tokens);
            parser.prog();
            SemantVisitor visitor = new SemantVisitor ();
            Frag frag = visitor.transProg(parser.tree);
            numberOfSyntaxError = parser.getNumberOfSyntaxErrors();
            numberOfSemanticError = visitor.getNumberOfSemanticErrors();
        } catch (IOException ex) {
            Logger.getLogger(TesteProg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNumberOfSyntaxError() {
        return numberOfSyntaxError;
    }

    public int getNumberOfSemanticError() {
        return numberOfSemanticError;
    }
}
