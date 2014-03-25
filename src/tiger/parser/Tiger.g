grammar Tiger;

@header {
package tiger.parser;
import tiger.symbol.*;
import tiger.absyn.*;
}

@members{
Exp tree = null;
}

prog: exp  {tree = $exp.e;}
    ;
    
dec
returns 
[
Dec d = null;
]
locals
[
NameTy nt = null;
]
: typedec {$d = $typedec.d;}
| functiondec {$d = $functiondec.d;}
| VAR ID (COLON typeid{$nt = new NameTy($COLON.pos, Symbol.symbol($typeid.text));})? ASSIGN exp {$d = new VarDec($VAR.pos,Symbol.symbol($ID.text), $nt, $exp.e);}
;

typedec
returns [
Dec d = null;
]
locals [
TypeDec l = null;
]
: TYPE ID EQ ty {$d = $l = new TypeDec($ID.pos, Symbol.symbol($ID.text), $ty.t, null);}
 (TYPE ID EQ ty {$l.next= new TypeDec($ID.pos, Symbol.symbol($ID.text), $ty.t, null);$l = $l.next;})*
;

functiondec
returns [
Dec d;
]
locals [
FunctionDec l;
]
:    {NameTy nt = null;}
     (FUNCTION ID LPAREN tyfields RPAREN (COLON typeid { nt = new NameTy($COLON.pos, Symbol.symbol($typeid.text));})? EQ exp 
        {$d =$l= new FunctionDec($FUNCTION.pos, Symbol.symbol($ID.text), $tyfields.first, nt, $exp.e, null);} ) 
     (FUNCTION ID LPAREN tyfields RPAREN (COLON typeid { nt = new NameTy($COLON.pos, Symbol.symbol($typeid.text));})? EQ exp 
        {$l.next = new FunctionDec($FUNCTION.pos, Symbol.symbol($ID.text), $tyfields.first, nt, $exp.e, null); $l = $l.next;} )*

;


ty
returns
[Ty t = null;]
: typeid {$t = new NameTy($start.getCharPositionInLine(),Symbol.symbol($typeid.text));}
  | LBRACE tyfields RBRACE {$t = new RecordTy($start.getCharPositionInLine(), $tyfields.first);}	
  | ARRAY OF typeid {$t = new ArrayTy($ARRAY.pos, Symbol.symbol($typeid.text));}
  ;

	
typeid	
:	ID;

tyfields
returns
[FieldList first = null;]
:	ID COLON typeid {FieldList fields =$first = new FieldList($ID.pos,Symbol.symbol($ID.text),Symbol.symbol($typeid.text),null);}   (COMMA ID COLON typeid {fields.tail = new FieldList($ID.pos,Symbol.symbol($ID.text),Symbol.symbol($typeid.text),null); fields = fields.tail;})* 
	|	/*epsilon */
	;

decs	
returns [
DecList d = null;
]
: {DecList l = null;} dec {$d=l = new DecList($dec.d, null);}	(dec {l.tail = new DecList($dec.d, null); l=l.tail;})*;
    
exp
returns 
[Exp e;]
: andexp {$e = $andexp.e;} (OR ar=andexp  {$e = new IfExp($start.getCharPositionInLine(),$e,new IntExp($start.getCharPositionInLine(),1), $ar.e);} )* 
;
   
 	
andexp	
returns 
[ Exp e;]
:	cl=compexp {$e = $compexp.e;} (AND cr=compexp {$e = new IfExp($start.getCharPositionInLine(),$e,$cr.e,new IntExp($start.getCharPositionInLine(),0));}  )*;
compexp	
returns 
[Exp e;]
locals
[int op;]
:	sumexp {$e = $sumexp.e;}((GE {$op = OpExp.GE;}|LE{$op=OpExp.LE;}|EQ{$op=OpExp.EQ;}|NEQ{$op=OpExp.NE;}|LT{$op=OpExp.LT;}|GT{$op=OpExp.GT;}) r=sumexp {$e = new OpExp($start.getCharPositionInLine(), $e, $op, $r.e);})*;
sumexp	
returns
[Exp e;]
locals
[int op;]
:	mulexp {$e = $mulexp.e;} ((PLUS {$op = OpExp.PLUS;}|MINUS {$op= OpExp.MINUS;} ) r=mulexp {$e = new OpExp($start.getCharPositionInLine(), $e, $op, $r.e);})*
;
mulexp 
returns
[Exp e;]
locals
[int op;]
 :  atom {$e = $atom.e;} ((TIMES{$op = OpExp.MUL;}|DIVIDE{$op = OpExp.DIV;}) r=atom {$e = new OpExp($start.getCharPositionInLine(), $e, $op, $r.e);})*;


atom	

returns [
Exp e;
]   

locals [
ExpList expList = null;
]

:      
    /* literals */

    NIL {$e = new NilExp($NIL.pos);}
   |INT {$e = new IntExp($INT.pos, Integer.parseInt($INT.text)); }
   |STRING {$e = new StringExp($STRING.pos, $STRING.text); }
   |ID          {$e = new VarExp($ID.pos, new SimpleVar($ID.pos, Symbol.symbol($ID.text)));}  
    (ASSIGN exp {$e = new AssignExp($ID.pos, new SimpleVar($ID.pos, Symbol.symbol($ID.text)), $exp.e); })?
   

   /* function call */
   | ID LPAREN (exp {ExpList last = $expList = new ExpList($exp.e, null);} (COMMA exp {last.tail = new ExpList($exp.e, null); last = last.tail;})*)?  RPAREN {$e = new CallExp($ID.pos, Symbol.symbol($ID.text),$expList);}

   /* record creations */
   |typeid  LBRACE {FieldExpList list = null, first=null;} (id1=ID EQ exp1=exp {first=list = new FieldExpList($id1.pos, Symbol.symbol($id1.text), $exp1.e, null);} (COMMA id2=ID EQ exp2=exp {list.tail = new FieldExpList($id2.pos, Symbol.symbol($id2.text), $exp2.e, null);list=list.tail;})*)? RBRACE  {$e = new RecordExp($start.getCharPositionInLine(), Symbol.symbol($typeid.text), first);}


   /* variable, field, elements of an array, array creation, and assignment */
   | id1=ID DOT id2=ID {Var x = null; $e = new VarExp($id1.pos, x=new FieldVar($id1.pos, new SimpleVar($id1.pos, Symbol.symbol($id1.text)),Symbol.symbol($id2.text)));}  (DOT id3=ID  {$e = new VarExp($id1.pos,x= new FieldVar($id1.pos, x,Symbol.symbol($id3.text)));}  |LBRACK exp RBRACK {$e = new VarExp($id1.pos, x=new SubscriptVar($id3.pos, x, $exp.e));} )* (ASSIGN exp {$e = new AssignExp($id1.pos,x,$exp.e);})? // TODO
   | ID LBRACK e1=exp RBRACK (OF e2=exp {$e = new ArrayExp($ID.pos, Symbol.symbol($ID.text), $e1.e, $e2.e);} |{Var x = null; $e = new VarExp($ID.pos, x=new SubscriptVar($ID.pos, new SimpleVar($ID.pos, Symbol.symbol($ID.text)), $exp.e));} (ASSIGN e3=exp {$e = new AssignExp($ID.pos,x,$exp.e);})?)   //TODO
   
 
   /* operation */
   | MINUS exp {$e = new OpExp($MINUS.pos, new IntExp($MINUS.pos, 0), OpExp.MINUS, $exp.e);}
   | LPAREN exps RPAREN {$e = $exps.e;}
  
   /* control flow */
   | IF e1=exp THEN e2=exp {$e = new IfExp($IF.pos,$e1.e, $e2.e);} (ELSE e3=exp {$e = new IfExp($IF.pos,$e1.e, $e2.e, $e3.e );})? 
   | WHILE e1=exp DO e2=exp {$e = new WhileExp($WHILE.pos, $e1.e, $e2.e);}
   | FOR ID ASSIGN e1=exp TO e2=exp DO e3=exp {$e = new ForExp($FOR.pos, new VarDec($ID.pos, Symbol.symbol($ID.text), null, $e1.e), $e2.e, $e3.e);}
   | BREAK {$e = new BreakExp($BREAK.pos);}
   | LET decs IN exps END  {$e = new LetExp ($LET.pos, $decs.d, $exps.e);}
   ;  
    	
exps 	
returns [
SeqExp e = null;
]
locals [
ExpList f;
]
: 	(exp { ExpList l = new ExpList($exp.e, null); $f = l;} (SEMICOLON exp {l.tail = new ExpList($exp.e, null); l = l.tail;})*)? {$e = new SeqExp($start.getCharPositionInLine(), $f);} 
	; 

	
// START:tokens
COMMENT 
    :  '/*' .*? '*/'  -> skip;
COMMENT_SL: '//' ~[\r\n]* -> skip;

STRING :   '"' ('\\"'|~('"'|'\r'|'\n'))* '"';
ARRAY: 'array' ;
BREAK: 'break' ;
FUNCTION: 'function'; 
ELSE: 'else';
DO: 'do';
OF: 'of';
NIL: 'nil';
TYPE: 'type';
FOR: 'for';
TO: 'to';
IN: 'in';
END: 'end';
DOT: '.';
IF: 'if';
WHILE: 'while';
VAR: 'var';
LET: 'let';
THEN: 'then';
INT: '0' | ('1'..'9')('0'..'9')*;
COMMA: ',';
GT:  '>';
DIVIDE: '/';
COLON: ':';
SEMICOLON: ';';
OR: '|';
GE: '>=';
LT: '<';
MINUS: '-';
TIMES: '*';
LE: '<=';
ASSIGN: ':=';
LPAREN: '(';
RPAREN: ')';
LBRACK: '[';
RBRACK: ']';
NEQ: '<>';
AND: '&';
PLUS: '+';
LBRACE: '{';
RBRACE: '}';
EQ: '=';
WS : (' '| '\t'|'\n'|'\r')+ -> skip;
ID : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*|'_main';

// END:tokens
