/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiger.parser.TigerLexer;
import tiger.parser.TigerParser;
import tiger.semant.SemantVisitor;
import tiger.translate.Frag;

/**
 *
 * @author Gabriel
 */
public class TesteGlobal {
    private String path;
    
    public TesteGlobal() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    //Testes semanticos 
    @Test
    public void test20(){
        TigerLexer lex;
      
        try {
            lex = new TigerLexer(new ANTLRFileStream("test/test20.tig","UTF8"));
            CommonTokenStream tokens= new CommonTokenStream(lex);
            TigerParser parser= new TigerParser(tokens);
            parser.prog();
            SemantVisitor visitor = new SemantVisitor ();
            Frag frag = visitor.transProg(parser.tree);
            System.err.println("Quantidade de erros semanticos no test20: "+ visitor.getNumberOfSemanticErrors());
            assertTrue(visitor.getNumberOfSemanticErrors()>=1);
        } catch (IOException ex) {
            Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //testes sintaticos
    @Test
    public void testeSintatico(){
        TigerLexer lex;
        
        try {
            lex = new TigerLexer(new ANTLRFileStream("test/sintatico.tig","UTF8"));
            CommonTokenStream tokens= new CommonTokenStream(lex);
            TigerParser parser= new TigerParser(tokens);
            parser.prog();
            
        } catch (IOException ex) {
            Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
