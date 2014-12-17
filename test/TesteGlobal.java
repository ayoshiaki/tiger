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
 * Autores: Gabriel [teste20, testeSintatico]
 *          William Renan Debrino [jUnitTest01-jUnitTest10]
 * @
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


    @Test
    public void ErrosTestes20_semantico(){
        TigerLexer lex;
      
        try {
            lex = new TigerLexer(new ANTLRFileStream("test/ErrosTestes/test20.tig","UTF8"));
            CommonTokenStream tokens= new CommonTokenStream(lex);
            TigerParser parser= new TigerParser(tokens);
            parser.prog();
            SemantVisitor visitor = new SemantVisitor ();
            Frag frag = visitor.transProg(parser.tree);
            //Deveria possuir erros semânticos.
            assertTrue(visitor.getNumberOfSemanticErrors()>=1);
        } catch (IOException ex) {
            Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void ErrosTestes20_sintatico(){
        TigerLexer lex;
      
        try {
            lex = new TigerLexer(new ANTLRFileStream("test/ErrosTestes/test20.tig","UTF8"));
            CommonTokenStream tokens= new CommonTokenStream(lex);
            TigerParser parser= new TigerParser(tokens);
            parser.prog();
            //Não deveria possuir erros sintáticos
            assertTrue(parser.getNumberOfSyntaxErrors()==0);
        } catch (IOException ex) {
            Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //TESTES SINTÁTICOS
    @Test
    public void ErrosTestesSintatico(){
        TigerLexer lex;
        
        try {
            lex = new TigerLexer(new ANTLRFileStream("test/ErrosTestes/sintatico.tig","UTF8"));
            CommonTokenStream tokens= new CommonTokenStream(lex);
            TigerParser parser= new TigerParser(tokens);
            parser.prog();
            //System.err.println("Quantidade de erros sintaticos no teste: "+ parser.getNumberOfSyntaxErrors());
            assertTrue(parser.getNumberOfSyntaxErrors()>=1);
        } catch (IOException ex) {
            Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /* Teste para o tipo primitivo int */
    @Test
    public void jUnitTest01(){
         TigerLexer lex;
         try {
             lex = new TigerLexer(new ANTLRFileStream("test/jUnitTests/jUnitTest01.tig", "UTF8"));
             CommonTokenStream tokens = new CommonTokenStream(lex);
             TigerParser parser = new TigerParser(tokens);
             SemantVisitor visitor = new SemantVisitor();
             parser.prog();
             boolean resp = (parser.getNumberOfSyntaxErrors() == 0 && visitor.getNumberOfSemanticErrors() == 0);
             assertTrue(resp);
         } catch (IOException ex) {
             Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    /* Teste para o tipo primitivo float */
    @Test
    public void jUnitTest02(){
         TigerLexer lex;
         try {
             lex = new TigerLexer(new ANTLRFileStream("test/jUnitTests/jUnitTest02.tig", "UTF8"));
             CommonTokenStream tokens = new CommonTokenStream(lex);
             TigerParser parser = new TigerParser(tokens);
             SemantVisitor visitor = new SemantVisitor();
             parser.prog();
             boolean resp = (parser.getNumberOfSyntaxErrors() == 0 && visitor.getNumberOfSemanticErrors() == 0);
             assertTrue(resp);
         } catch (IOException ex) {
             Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    /* Teste o metedo printi */
    @Test
    public void jUnitTest03(){
         TigerLexer lex;
         try {
             lex = new TigerLexer(new ANTLRFileStream("test/jUnitTests/jUnitTest03.tig", "UTF8"));
             CommonTokenStream tokens = new CommonTokenStream(lex);
             TigerParser parser = new TigerParser(tokens);
             SemantVisitor visitor = new SemantVisitor();
             parser.prog();
             boolean resp = (parser.getNumberOfSyntaxErrors() == 0 && visitor.getNumberOfSemanticErrors() == 0);
             assertTrue(resp);
         } catch (IOException ex) {
             Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
   
    /* Teste de um escopo simples */
    @Test
    public void jUnitTest04(){
         TigerLexer lex;
         try {
             lex = new TigerLexer(new ANTLRFileStream("test/jUnitTests/jUnitTest04.tig", "UTF8"));
             CommonTokenStream tokens = new CommonTokenStream(lex);
             TigerParser parser = new TigerParser(tokens);
             SemantVisitor visitor = new SemantVisitor();
             parser.prog();
             boolean resp = (parser.getNumberOfSyntaxErrors() == 0 && visitor.getNumberOfSemanticErrors() == 0);
             assertTrue(resp);
         } catch (IOException ex) {
             Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    /* Teste da estrutura de selecao if usando constantes */
    @Test
    public void jUnitTest05(){
         TigerLexer lex;
         try {
             lex = new TigerLexer(new ANTLRFileStream("test/jUnitTests/jUnitTest05.tig", "UTF8"));
             CommonTokenStream tokens = new CommonTokenStream(lex);
             TigerParser parser = new TigerParser(tokens);
             SemantVisitor visitor = new SemantVisitor();
             parser.prog();
             boolean resp = (parser.getNumberOfSyntaxErrors() == 0 && visitor.getNumberOfSemanticErrors() == 0);
             assertTrue(resp);
         } catch (IOException ex) {
             Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    /* Teste da estrutura de selecao if usando variaveis */
    @Test
    public void jUnitTest06(){
         TigerLexer lex;
         try {
             lex = new TigerLexer(new ANTLRFileStream("test/jUnitTests/jUnitTest06.tig", "UTF8"));
             CommonTokenStream tokens = new CommonTokenStream(lex);
             TigerParser parser = new TigerParser(tokens);
             SemantVisitor visitor = new SemantVisitor();
             parser.prog();
             boolean resp = (parser.getNumberOfSyntaxErrors() == 0 && visitor.getNumberOfSemanticErrors() == 0);
             assertTrue(resp);
         } catch (IOException ex) {
             Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    /* Teste da estrutura de repeticao FOR */
    @Test
    public void jUnitTest07(){
         TigerLexer lex;
         try {
             lex = new TigerLexer(new ANTLRFileStream("test/jUnitTests/jUnitTest07.tig", "UTF8"));
             CommonTokenStream tokens = new CommonTokenStream(lex);
             TigerParser parser = new TigerParser(tokens);
             SemantVisitor visitor = new SemantVisitor();
             parser.prog();
             boolean resp = (parser.getNumberOfSyntaxErrors() == 0 && visitor.getNumberOfSemanticErrors() == 0);
             assertTrue(resp);
         } catch (IOException ex) {
             Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    @Test
    public void jUnitTest08(){
         TigerLexer lex;
         try {
             lex = new TigerLexer(new ANTLRFileStream("test/jUnitTests/jUnitTest08.tig", "UTF8"));
             CommonTokenStream tokens = new CommonTokenStream(lex);
             TigerParser parser = new TigerParser(tokens);
             SemantVisitor visitor = new SemantVisitor();
             parser.prog();
             boolean resp = (parser.getNumberOfSyntaxErrors() == 0 && visitor.getNumberOfSemanticErrors() == 0);
             assertTrue(resp);
         } catch (IOException ex) {
             Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    /* variavel global assumindo um valor em um ambiente local */
     @Test
    public void jUnitTest09(){
         TigerLexer lex;
         try {
             lex = new TigerLexer(new ANTLRFileStream("test/jUnitTests/jUnitTest09.tig", "UTF8"));
             CommonTokenStream tokens = new CommonTokenStream(lex);
             TigerParser parser = new TigerParser(tokens);
             SemantVisitor visitor = new SemantVisitor();
             parser.prog();
             boolean resp = (parser.getNumberOfSyntaxErrors() == 0 && visitor.getNumberOfSemanticErrors() == 0);
             assertTrue(resp);
         } catch (IOException ex) {
             Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    /* Teste da estrutura de selecao IF com a condicao maior(>) */
     @Test
    public void jUnitTest10(){
         TigerLexer lex;
         try {
             lex = new TigerLexer(new ANTLRFileStream("test/jUnitTests/jUnitTest10.tig", "UTF8"));
             CommonTokenStream tokens = new CommonTokenStream(lex);
             TigerParser parser = new TigerParser(tokens);
             SemantVisitor visitor = new SemantVisitor();
             parser.prog();
             boolean resp = (parser.getNumberOfSyntaxErrors() == 0 && visitor.getNumberOfSemanticErrors() == 0);
             assertTrue(resp);
         } catch (IOException ex) {
             Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
         }
                            }
         
         /* Teste automático do arquivo test16, o qual possui um erro semantico.
            Teste para erros sintáticos*/
     @Test
    public void ErrosTestes16_sintatico(){
         TigerLexer lex;
         try {
             lex = new TigerLexer(new ANTLRFileStream("test/ErrosTestes/test16.tig", "UTF8"));
             CommonTokenStream tokens = new CommonTokenStream(lex);
             TigerParser parser = new TigerParser(tokens);
             SemantVisitor visitor = new SemantVisitor();
             parser.prog();
             
             //Testa para os erros sintáticos(não deveria acusar erros).
             boolean resp = (parser.getNumberOfSyntaxErrors() == 0);
             assertFalse(resp);
             
         } catch (IOException ex) {
             Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
         }
                            }
            /* Teste automático do arquivo test16, o qual possui um erro semântico.
            Teste para erros semânticos */
    @Test
    public void ErrosTestes16_semantico(){
         TigerLexer lex;
         try {
             lex = new TigerLexer(new ANTLRFileStream("test/ErrosTestes/test16.tig", "UTF8"));
             CommonTokenStream tokens = new CommonTokenStream(lex);
             TigerParser parser = new TigerParser(tokens);
             parser.prog();
             SemantVisitor visitor = new SemantVisitor ();
             Frag frag = visitor.transProg(parser.tree);
             //Testa para os erros semânticos(deveria acusar erro).
             assertTrue(visitor.getNumberOfSemanticErrors()>=1);
             
         } catch (IOException ex) {
             Logger.getLogger(TesteGlobal.class.getName()).log(Level.SEVERE, null, ex);
         }
                            }
    
}
