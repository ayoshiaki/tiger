// Generated from Tiger.g by ANTLR 4.4

package tiger.parser;
import tiger.symbol.*;
import tiger.absyn.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TigerParser}.
 */
public interface TigerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TigerParser#tyfields}.
	 * @param ctx the parse tree
	 */
	void enterTyfields(@NotNull TigerParser.TyfieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#tyfields}.
	 * @param ctx the parse tree
	 */
	void exitTyfields(@NotNull TigerParser.TyfieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterDec(@NotNull TigerParser.DecContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitDec(@NotNull TigerParser.DecContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#ty}.
	 * @param ctx the parse tree
	 */
	void enterTy(@NotNull TigerParser.TyContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#ty}.
	 * @param ctx the parse tree
	 */
	void exitTy(@NotNull TigerParser.TyContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#andexp}.
	 * @param ctx the parse tree
	 */
	void enterAndexp(@NotNull TigerParser.AndexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#andexp}.
	 * @param ctx the parse tree
	 */
	void exitAndexp(@NotNull TigerParser.AndexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#compexp}.
	 * @param ctx the parse tree
	 */
	void enterCompexp(@NotNull TigerParser.CompexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#compexp}.
	 * @param ctx the parse tree
	 */
	void exitCompexp(@NotNull TigerParser.CompexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(@NotNull TigerParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(@NotNull TigerParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#typedec}.
	 * @param ctx the parse tree
	 */
	void enterTypedec(@NotNull TigerParser.TypedecContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#typedec}.
	 * @param ctx the parse tree
	 */
	void exitTypedec(@NotNull TigerParser.TypedecContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#functiondec}.
	 * @param ctx the parse tree
	 */
	void enterFunctiondec(@NotNull TigerParser.FunctiondecContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#functiondec}.
	 * @param ctx the parse tree
	 */
	void exitFunctiondec(@NotNull TigerParser.FunctiondecContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#sumexp}.
	 * @param ctx the parse tree
	 */
	void enterSumexp(@NotNull TigerParser.SumexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#sumexp}.
	 * @param ctx the parse tree
	 */
	void exitSumexp(@NotNull TigerParser.SumexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#exps}.
	 * @param ctx the parse tree
	 */
	void enterExps(@NotNull TigerParser.ExpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#exps}.
	 * @param ctx the parse tree
	 */
	void exitExps(@NotNull TigerParser.ExpsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#typeid}.
	 * @param ctx the parse tree
	 */
	void enterTypeid(@NotNull TigerParser.TypeidContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#typeid}.
	 * @param ctx the parse tree
	 */
	void exitTypeid(@NotNull TigerParser.TypeidContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#mulexp}.
	 * @param ctx the parse tree
	 */
	void enterMulexp(@NotNull TigerParser.MulexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#mulexp}.
	 * @param ctx the parse tree
	 */
	void exitMulexp(@NotNull TigerParser.MulexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(@NotNull TigerParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(@NotNull TigerParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull TigerParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull TigerParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#decs}.
	 * @param ctx the parse tree
	 */
	void enterDecs(@NotNull TigerParser.DecsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#decs}.
	 * @param ctx the parse tree
	 */
	void exitDecs(@NotNull TigerParser.DecsContext ctx);
}