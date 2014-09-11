// Generated from Tiger.g by ANTLR 4.2

package tiger.parser;
import tiger.symbol.*;
import tiger.absyn.*;

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
		COMMENT=1, COMMENT_SL=2, STRING=3, ARRAY=4, BREAK=5, FUNCTION=6, ELSE=7, 
		DO=8, OF=9, NIL=10, TYPE=11, FOR=12, TO=13, IN=14, END=15, DOT=16, IF=17, 
		WHILE=18, VAR=19, LET=20, THEN=21, INT=22, COMMA=23, GT=24, DIVIDE=25, 
		COLON=26, SEMICOLON=27, OR=28, GE=29, LT=30, MINUS=31, TIMES=32, LE=33, 
		ASSIGN=34, LPAREN=35, RPAREN=36, LBRACK=37, RBRACK=38, NEQ=39, AND=40, 
		PLUS=41, LBRACE=42, RBRACE=43, EQ=44, WS=45, ID=46;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"COMMENT", "COMMENT_SL", "STRING", "'array'", "'break'", "'function'", 
		"'else'", "'do'", "'of'", "'nil'", "'type'", "'for'", "'to'", "'in'", 
		"'end'", "'.'", "'if'", "'while'", "'var'", "'let'", "'then'", "INT", 
		"','", "'>'", "'/'", "':'", "';'", "'|'", "'>='", "'<'", "'-'", "'*'", 
		"'<='", "':='", "'('", "')'", "'['", "']'", "'<>'", "'&'", "'+'", "'{'", 
		"'}'", "'='", "WS", "ID"
	};
	public static final String[] ruleNames = {
		"COMMENT", "COMMENT_SL", "STRING", "ARRAY", "BREAK", "FUNCTION", "ELSE", 
		"DO", "OF", "NIL", "TYPE", "FOR", "TO", "IN", "END", "DOT", "IF", "WHILE", 
		"VAR", "LET", "THEN", "INT", "COMMA", "GT", "DIVIDE", "COLON", "SEMICOLON", 
		"OR", "GE", "LT", "MINUS", "TIMES", "LE", "ASSIGN", "LPAREN", "RPAREN", 
		"LBRACK", "RBRACK", "NEQ", "AND", "PLUS", "LBRACE", "RBRACE", "EQ", "WS", 
		"ID"
	};


	Exp tree = null;


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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\60\u0121\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\2\3\2\7\2d\n\2\f\2\16\2g\13\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3r\n\3\f\3\16\3u\13\3\3\3\3\3\3\4\3"+
		"\4\3\4\3\4\7\4}\n\4\f\4\16\4\u0080\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\7"+
		"\27\u00d6\n\27\f\27\16\27\u00d9\13\27\5\27\u00db\n\27\3\30\3\30\3\31\3"+
		"\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3"+
		" \3 \3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3)"+
		"\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\6.\u010e\n.\r.\16.\u010f\3.\3.\3/\3/\7"+
		"/\u0116\n/\f/\16/\u0119\13/\3/\3/\3/\3/\3/\5/\u0120\n/\3e\2\60\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60\3\2\7\4\2\f\f\17\17\5\2\f\f\17\17$$\5\2"+
		"\13\f\17\17\"\"\4\2C\\c|\6\2\62;C\\aac|\u0129\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2"+
		"\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2"+
		"\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2"+
		"\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y"+
		"\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\3_\3\2\2\2\5m\3\2\2\2\7x\3\2\2\2\t\u0083"+
		"\3\2\2\2\13\u0089\3\2\2\2\r\u008f\3\2\2\2\17\u0098\3\2\2\2\21\u009d\3"+
		"\2\2\2\23\u00a0\3\2\2\2\25\u00a3\3\2\2\2\27\u00a7\3\2\2\2\31\u00ac\3\2"+
		"\2\2\33\u00b0\3\2\2\2\35\u00b3\3\2\2\2\37\u00b6\3\2\2\2!\u00ba\3\2\2\2"+
		"#\u00bc\3\2\2\2%\u00bf\3\2\2\2\'\u00c5\3\2\2\2)\u00c9\3\2\2\2+\u00cd\3"+
		"\2\2\2-\u00da\3\2\2\2/\u00dc\3\2\2\2\61\u00de\3\2\2\2\63\u00e0\3\2\2\2"+
		"\65\u00e2\3\2\2\2\67\u00e4\3\2\2\29\u00e6\3\2\2\2;\u00e8\3\2\2\2=\u00eb"+
		"\3\2\2\2?\u00ed\3\2\2\2A\u00ef\3\2\2\2C\u00f1\3\2\2\2E\u00f4\3\2\2\2G"+
		"\u00f7\3\2\2\2I\u00f9\3\2\2\2K\u00fb\3\2\2\2M\u00fd\3\2\2\2O\u00ff\3\2"+
		"\2\2Q\u0102\3\2\2\2S\u0104\3\2\2\2U\u0106\3\2\2\2W\u0108\3\2\2\2Y\u010a"+
		"\3\2\2\2[\u010d\3\2\2\2]\u011f\3\2\2\2_`\7\61\2\2`a\7,\2\2ae\3\2\2\2b"+
		"d\13\2\2\2cb\3\2\2\2dg\3\2\2\2ef\3\2\2\2ec\3\2\2\2fh\3\2\2\2ge\3\2\2\2"+
		"hi\7,\2\2ij\7\61\2\2jk\3\2\2\2kl\b\2\2\2l\4\3\2\2\2mn\7\61\2\2no\7\61"+
		"\2\2os\3\2\2\2pr\n\2\2\2qp\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tv\3\2"+
		"\2\2us\3\2\2\2vw\b\3\2\2w\6\3\2\2\2x~\7$\2\2yz\7^\2\2z}\7$\2\2{}\n\3\2"+
		"\2|y\3\2\2\2|{\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0081"+
		"\3\2\2\2\u0080~\3\2\2\2\u0081\u0082\7$\2\2\u0082\b\3\2\2\2\u0083\u0084"+
		"\7c\2\2\u0084\u0085\7t\2\2\u0085\u0086\7t\2\2\u0086\u0087\7c\2\2\u0087"+
		"\u0088\7{\2\2\u0088\n\3\2\2\2\u0089\u008a\7d\2\2\u008a\u008b\7t\2\2\u008b"+
		"\u008c\7g\2\2\u008c\u008d\7c\2\2\u008d\u008e\7m\2\2\u008e\f\3\2\2\2\u008f"+
		"\u0090\7h\2\2\u0090\u0091\7w\2\2\u0091\u0092\7p\2\2\u0092\u0093\7e\2\2"+
		"\u0093\u0094\7v\2\2\u0094\u0095\7k\2\2\u0095\u0096\7q\2\2\u0096\u0097"+
		"\7p\2\2\u0097\16\3\2\2\2\u0098\u0099\7g\2\2\u0099\u009a\7n\2\2\u009a\u009b"+
		"\7u\2\2\u009b\u009c\7g\2\2\u009c\20\3\2\2\2\u009d\u009e\7f\2\2\u009e\u009f"+
		"\7q\2\2\u009f\22\3\2\2\2\u00a0\u00a1\7q\2\2\u00a1\u00a2\7h\2\2\u00a2\24"+
		"\3\2\2\2\u00a3\u00a4\7p\2\2\u00a4\u00a5\7k\2\2\u00a5\u00a6\7n\2\2\u00a6"+
		"\26\3\2\2\2\u00a7\u00a8\7v\2\2\u00a8\u00a9\7{\2\2\u00a9\u00aa\7r\2\2\u00aa"+
		"\u00ab\7g\2\2\u00ab\30\3\2\2\2\u00ac\u00ad\7h\2\2\u00ad\u00ae\7q\2\2\u00ae"+
		"\u00af\7t\2\2\u00af\32\3\2\2\2\u00b0\u00b1\7v\2\2\u00b1\u00b2\7q\2\2\u00b2"+
		"\34\3\2\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7p\2\2\u00b5\36\3\2\2\2\u00b6"+
		"\u00b7\7g\2\2\u00b7\u00b8\7p\2\2\u00b8\u00b9\7f\2\2\u00b9 \3\2\2\2\u00ba"+
		"\u00bb\7\60\2\2\u00bb\"\3\2\2\2\u00bc\u00bd\7k\2\2\u00bd\u00be\7h\2\2"+
		"\u00be$\3\2\2\2\u00bf\u00c0\7y\2\2\u00c0\u00c1\7j\2\2\u00c1\u00c2\7k\2"+
		"\2\u00c2\u00c3\7n\2\2\u00c3\u00c4\7g\2\2\u00c4&\3\2\2\2\u00c5\u00c6\7"+
		"x\2\2\u00c6\u00c7\7c\2\2\u00c7\u00c8\7t\2\2\u00c8(\3\2\2\2\u00c9\u00ca"+
		"\7n\2\2\u00ca\u00cb\7g\2\2\u00cb\u00cc\7v\2\2\u00cc*\3\2\2\2\u00cd\u00ce"+
		"\7v\2\2\u00ce\u00cf\7j\2\2\u00cf\u00d0\7g\2\2\u00d0\u00d1\7p\2\2\u00d1"+
		",\3\2\2\2\u00d2\u00db\7\62\2\2\u00d3\u00d7\4\63;\2\u00d4\u00d6\4\62;\2"+
		"\u00d5\u00d4\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8"+
		"\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00d2\3\2\2\2\u00da"+
		"\u00d3\3\2\2\2\u00db.\3\2\2\2\u00dc\u00dd\7.\2\2\u00dd\60\3\2\2\2\u00de"+
		"\u00df\7@\2\2\u00df\62\3\2\2\2\u00e0\u00e1\7\61\2\2\u00e1\64\3\2\2\2\u00e2"+
		"\u00e3\7<\2\2\u00e3\66\3\2\2\2\u00e4\u00e5\7=\2\2\u00e58\3\2\2\2\u00e6"+
		"\u00e7\7~\2\2\u00e7:\3\2\2\2\u00e8\u00e9\7@\2\2\u00e9\u00ea\7?\2\2\u00ea"+
		"<\3\2\2\2\u00eb\u00ec\7>\2\2\u00ec>\3\2\2\2\u00ed\u00ee\7/\2\2\u00ee@"+
		"\3\2\2\2\u00ef\u00f0\7,\2\2\u00f0B\3\2\2\2\u00f1\u00f2\7>\2\2\u00f2\u00f3"+
		"\7?\2\2\u00f3D\3\2\2\2\u00f4\u00f5\7<\2\2\u00f5\u00f6\7?\2\2\u00f6F\3"+
		"\2\2\2\u00f7\u00f8\7*\2\2\u00f8H\3\2\2\2\u00f9\u00fa\7+\2\2\u00faJ\3\2"+
		"\2\2\u00fb\u00fc\7]\2\2\u00fcL\3\2\2\2\u00fd\u00fe\7_\2\2\u00feN\3\2\2"+
		"\2\u00ff\u0100\7>\2\2\u0100\u0101\7@\2\2\u0101P\3\2\2\2\u0102\u0103\7"+
		"(\2\2\u0103R\3\2\2\2\u0104\u0105\7-\2\2\u0105T\3\2\2\2\u0106\u0107\7}"+
		"\2\2\u0107V\3\2\2\2\u0108\u0109\7\177\2\2\u0109X\3\2\2\2\u010a\u010b\7"+
		"?\2\2\u010bZ\3\2\2\2\u010c\u010e\t\4\2\2\u010d\u010c\3\2\2\2\u010e\u010f"+
		"\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111\3\2\2\2\u0111"+
		"\u0112\b.\2\2\u0112\\\3\2\2\2\u0113\u0117\t\5\2\2\u0114\u0116\t\6\2\2"+
		"\u0115\u0114\3\2\2\2\u0116\u0119\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118"+
		"\3\2\2\2\u0118\u0120\3\2\2\2\u0119\u0117\3\2\2\2\u011a\u011b\7a\2\2\u011b"+
		"\u011c\7o\2\2\u011c\u011d\7c\2\2\u011d\u011e\7k\2\2\u011e\u0120\7p\2\2"+
		"\u011f\u0113\3\2\2\2\u011f\u011a\3\2\2\2\u0120^\3\2\2\2\f\2es|~\u00d7"+
		"\u00da\u010f\u0117\u011f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}