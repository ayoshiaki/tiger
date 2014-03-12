// Generated from Tiger.g by ANTLR 4.2

package tiger.parser;


import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TigerLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, STRING=2, ARRAY=3, BREAK=4, FUNCTION=5, ELSE=6, DO=7, OF=8, 
		NIL=9, TYPE=10, FOR=11, TO=12, IN=13, END=14, DOT=15, IF=16, WHILE=17, 
		VAR=18, LET=19, THEN=20, INT=21, COMMA=22, GT=23, DIVIDE=24, COLON=25, 
		SEMICOLON=26, OR=27, GE=28, LT=29, MINUS=30, TIMES=31, LE=32, ASSIGN=33, 
		LPAREN=34, RPAREN=35, LBRACK=36, RBRACK=37, NEQ=38, AND=39, PLUS=40, LBRACE=41, 
		RBRACE=42, EQ=43, WS=44, ID=45;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"COMMENT", "STRING", "'array'", "'break'", "'function'", "'else'", "'do'", 
		"'of'", "'nil'", "'type'", "'for'", "'to'", "'in'", "'end'", "'.'", "'if'", 
		"'while'", "'var'", "'let'", "'then'", "INT", "','", "'>'", "'/'", "':'", 
		"';'", "'|'", "'>='", "'<'", "'-'", "'*'", "'<='", "':='", "'('", "')'", 
		"'['", "']'", "'<>'", "'&'", "'+'", "'{'", "'}'", "'='", "WS", "ID"
	};
	public static final String[] ruleNames = {
		"COMMENT", "STRING", "ARRAY", "BREAK", "FUNCTION", "ELSE", "DO", "OF", 
		"NIL", "TYPE", "FOR", "TO", "IN", "END", "DOT", "IF", "WHILE", "VAR", 
		"LET", "THEN", "INT", "COMMA", "GT", "DIVIDE", "COLON", "SEMICOLON", "OR", 
		"GE", "LT", "MINUS", "TIMES", "LE", "ASSIGN", "LPAREN", "RPAREN", "LBRACK", 
		"RBRACK", "NEQ", "AND", "PLUS", "LBRACE", "RBRACE", "EQ", "WS", "ID"
	};


	public TigerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Tiger.g"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2/\u010b\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\3\2\3\2\3\2\7\2b\n\2\f\2\16\2e\13\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\7\3n\n\3\f\3\16\3q\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\7\26\u00c7"+
		"\n\26\f\26\16\26\u00ca\13\26\5\26\u00cc\n\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37"+
		"\3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3\'\3(\3("+
		"\3)\3)\3*\3*\3+\3+\3,\3,\3-\6-\u00ff\n-\r-\16-\u0100\3-\3-\3.\3.\7.\u0107"+
		"\n.\f.\16.\u010a\13.\3c\2/\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/\3\2\6\5\2"+
		"\f\f\17\17$$\5\2\13\f\17\17\"\"\4\2C\\c|\6\2\62;C\\aac|\u0110\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3"+
		"\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2"+
		"\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\3]\3\2\2\2\5k\3\2\2\2\7t\3\2\2\2\t"+
		"z\3\2\2\2\13\u0080\3\2\2\2\r\u0089\3\2\2\2\17\u008e\3\2\2\2\21\u0091\3"+
		"\2\2\2\23\u0094\3\2\2\2\25\u0098\3\2\2\2\27\u009d\3\2\2\2\31\u00a1\3\2"+
		"\2\2\33\u00a4\3\2\2\2\35\u00a7\3\2\2\2\37\u00ab\3\2\2\2!\u00ad\3\2\2\2"+
		"#\u00b0\3\2\2\2%\u00b6\3\2\2\2\'\u00ba\3\2\2\2)\u00be\3\2\2\2+\u00cb\3"+
		"\2\2\2-\u00cd\3\2\2\2/\u00cf\3\2\2\2\61\u00d1\3\2\2\2\63\u00d3\3\2\2\2"+
		"\65\u00d5\3\2\2\2\67\u00d7\3\2\2\29\u00d9\3\2\2\2;\u00dc\3\2\2\2=\u00de"+
		"\3\2\2\2?\u00e0\3\2\2\2A\u00e2\3\2\2\2C\u00e5\3\2\2\2E\u00e8\3\2\2\2G"+
		"\u00ea\3\2\2\2I\u00ec\3\2\2\2K\u00ee\3\2\2\2M\u00f0\3\2\2\2O\u00f3\3\2"+
		"\2\2Q\u00f5\3\2\2\2S\u00f7\3\2\2\2U\u00f9\3\2\2\2W\u00fb\3\2\2\2Y\u00fe"+
		"\3\2\2\2[\u0104\3\2\2\2]^\7\61\2\2^_\7,\2\2_c\3\2\2\2`b\13\2\2\2a`\3\2"+
		"\2\2be\3\2\2\2cd\3\2\2\2ca\3\2\2\2df\3\2\2\2ec\3\2\2\2fg\7,\2\2gh\7\61"+
		"\2\2hi\3\2\2\2ij\b\2\2\2j\4\3\2\2\2ko\7$\2\2ln\n\2\2\2ml\3\2\2\2nq\3\2"+
		"\2\2om\3\2\2\2op\3\2\2\2pr\3\2\2\2qo\3\2\2\2rs\7$\2\2s\6\3\2\2\2tu\7c"+
		"\2\2uv\7t\2\2vw\7t\2\2wx\7c\2\2xy\7{\2\2y\b\3\2\2\2z{\7d\2\2{|\7t\2\2"+
		"|}\7g\2\2}~\7c\2\2~\177\7m\2\2\177\n\3\2\2\2\u0080\u0081\7h\2\2\u0081"+
		"\u0082\7w\2\2\u0082\u0083\7p\2\2\u0083\u0084\7e\2\2\u0084\u0085\7v\2\2"+
		"\u0085\u0086\7k\2\2\u0086\u0087\7q\2\2\u0087\u0088\7p\2\2\u0088\f\3\2"+
		"\2\2\u0089\u008a\7g\2\2\u008a\u008b\7n\2\2\u008b\u008c\7u\2\2\u008c\u008d"+
		"\7g\2\2\u008d\16\3\2\2\2\u008e\u008f\7f\2\2\u008f\u0090\7q\2\2\u0090\20"+
		"\3\2\2\2\u0091\u0092\7q\2\2\u0092\u0093\7h\2\2\u0093\22\3\2\2\2\u0094"+
		"\u0095\7p\2\2\u0095\u0096\7k\2\2\u0096\u0097\7n\2\2\u0097\24\3\2\2\2\u0098"+
		"\u0099\7v\2\2\u0099\u009a\7{\2\2\u009a\u009b\7r\2\2\u009b\u009c\7g\2\2"+
		"\u009c\26\3\2\2\2\u009d\u009e\7h\2\2\u009e\u009f\7q\2\2\u009f\u00a0\7"+
		"t\2\2\u00a0\30\3\2\2\2\u00a1\u00a2\7v\2\2\u00a2\u00a3\7q\2\2\u00a3\32"+
		"\3\2\2\2\u00a4\u00a5\7k\2\2\u00a5\u00a6\7p\2\2\u00a6\34\3\2\2\2\u00a7"+
		"\u00a8\7g\2\2\u00a8\u00a9\7p\2\2\u00a9\u00aa\7f\2\2\u00aa\36\3\2\2\2\u00ab"+
		"\u00ac\7\60\2\2\u00ac \3\2\2\2\u00ad\u00ae\7k\2\2\u00ae\u00af\7h\2\2\u00af"+
		"\"\3\2\2\2\u00b0\u00b1\7y\2\2\u00b1\u00b2\7j\2\2\u00b2\u00b3\7k\2\2\u00b3"+
		"\u00b4\7n\2\2\u00b4\u00b5\7g\2\2\u00b5$\3\2\2\2\u00b6\u00b7\7x\2\2\u00b7"+
		"\u00b8\7c\2\2\u00b8\u00b9\7t\2\2\u00b9&\3\2\2\2\u00ba\u00bb\7n\2\2\u00bb"+
		"\u00bc\7g\2\2\u00bc\u00bd\7v\2\2\u00bd(\3\2\2\2\u00be\u00bf\7v\2\2\u00bf"+
		"\u00c0\7j\2\2\u00c0\u00c1\7g\2\2\u00c1\u00c2\7p\2\2\u00c2*\3\2\2\2\u00c3"+
		"\u00cc\7\62\2\2\u00c4\u00c8\4\63;\2\u00c5\u00c7\4\62;\2\u00c6\u00c5\3"+
		"\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9"+
		"\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\u00c3\3\2\2\2\u00cb\u00c4\3\2"+
		"\2\2\u00cc,\3\2\2\2\u00cd\u00ce\7.\2\2\u00ce.\3\2\2\2\u00cf\u00d0\7@\2"+
		"\2\u00d0\60\3\2\2\2\u00d1\u00d2\7\61\2\2\u00d2\62\3\2\2\2\u00d3\u00d4"+
		"\7<\2\2\u00d4\64\3\2\2\2\u00d5\u00d6\7=\2\2\u00d6\66\3\2\2\2\u00d7\u00d8"+
		"\7~\2\2\u00d88\3\2\2\2\u00d9\u00da\7@\2\2\u00da\u00db\7?\2\2\u00db:\3"+
		"\2\2\2\u00dc\u00dd\7>\2\2\u00dd<\3\2\2\2\u00de\u00df\7/\2\2\u00df>\3\2"+
		"\2\2\u00e0\u00e1\7,\2\2\u00e1@\3\2\2\2\u00e2\u00e3\7>\2\2\u00e3\u00e4"+
		"\7?\2\2\u00e4B\3\2\2\2\u00e5\u00e6\7<\2\2\u00e6\u00e7\7?\2\2\u00e7D\3"+
		"\2\2\2\u00e8\u00e9\7*\2\2\u00e9F\3\2\2\2\u00ea\u00eb\7+\2\2\u00ebH\3\2"+
		"\2\2\u00ec\u00ed\7]\2\2\u00edJ\3\2\2\2\u00ee\u00ef\7_\2\2\u00efL\3\2\2"+
		"\2\u00f0\u00f1\7>\2\2\u00f1\u00f2\7@\2\2\u00f2N\3\2\2\2\u00f3\u00f4\7"+
		"(\2\2\u00f4P\3\2\2\2\u00f5\u00f6\7-\2\2\u00f6R\3\2\2\2\u00f7\u00f8\7}"+
		"\2\2\u00f8T\3\2\2\2\u00f9\u00fa\7\177\2\2\u00faV\3\2\2\2\u00fb\u00fc\7"+
		"?\2\2\u00fcX\3\2\2\2\u00fd\u00ff\t\3\2\2\u00fe\u00fd\3\2\2\2\u00ff\u0100"+
		"\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0102\3\2\2\2\u0102"+
		"\u0103\b-\2\2\u0103Z\3\2\2\2\u0104\u0108\t\4\2\2\u0105\u0107\t\5\2\2\u0106"+
		"\u0105\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2"+
		"\2\2\u0109\\\3\2\2\2\u010a\u0108\3\2\2\2\t\2co\u00c8\u00cb\u0100\u0108"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}