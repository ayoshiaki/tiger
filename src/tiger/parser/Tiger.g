grammar Tiger;

@header {
package tiger.parser;

import tiger.absyn.Exp;
import tiger.absyn.IntExp;
import tiger.absyn.NilExp;
import tiger.absyn.StringExp;
import tiger.absyn.Print;
import tiger.absyn.AssignExp;
import tiger.absyn.SimpleVar;
import tiger.absyn.VarExp;
import tiger.symbol.Symbol;
import tiger.absyn.Absyn;
import tiger.absyn.CallExp;
import tiger.absyn.ExpList;
import tiger.absyn.FieldExpList;
import tiger.absyn.RecordExp;
}


prog: exp  {Print p = new Print(System.out); p.prExp($exp.e, 2);}
    | decs 
    ;
    
dec: TYPE ID EQ ty
   | FUNCTION ID LPAREN tyfields RPAREN (COLON typeid)? EQ exp
   | vardec
   ;
vardec	:	VAR ID (COLON typeid)? ASSIGN exp
	;
ty: typeid
  | LBRACE tyfields RBRACE	
  | ARRAY OF typeid
  ;

	
typeid	
:	ID;

tyfields:	ID COLON typeid (COMMA ID COLON typeid)* 
	|	/*epsilon */
	;

decs	:	dec+;
    
exp
returns 
[Exp e;]
: andexp {$e = $andexp.e;} (OR andexp)* 
   ;
   
 	
andexp	
returns 
[ Exp e;]
:	compexp {$e = $compexp.e;} (AND compexp)*;
compexp	
returns 
[Exp e;]
:	sumexp {$e = $sumexp.e;}((GE|LE|EQ|NEQ|LT|GT) sumexp)*;
sumexp	
returns
[Exp e;]
:	mulexp {$e = $mulexp.e;} ((PLUS|MINUS) mulexp)*;

mulexp 
returns
[Exp e;]
 :  atom {$e = $atom.e;} ((TIMES|DIVIDE) atom)*;


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
   | ID LPAREN (exp {$expList = new ExpList($exp.e, null);} (COMMA exp {$expList.tail = new ExpList($exp.e, null);})*)?  RPAREN {$e = new CallExp($ID.pos, Symbol.symbol($ID.text),$expList);}

   /* record creations */
   |typeid  LBRACE {FieldExpList list = null, first=null;} (id1=ID EQ exp1=exp {first=list = new FieldExpList($id1.pos, Symbol.symbol($id1.text), $exp1.e, null);} (COMMA id2=ID EQ exp2=exp {list.tail = new FieldExpList($id2.pos, Symbol.symbol($id2.text), $exp2.e, null);list=list.tail;})*)? RBRACE  {$e = new RecordExp($start.getCharPositionInLine(), Symbol.symbol($typeid.text), first);}


   /* variable, field, elements of an array, array creation */


   | ID DOT ID lvaluep (ASSIGN exp)?
   | ID LBRACK exp RBRACK oforlvaluep 
   
 
   /* operation */
   | MINUS exp
   | LPAREN exps RPAREN
  
   /* control flow */
   | IF exp THEN exp (ELSE exp)?
   | WHILE exp DO exp
   | FOR ID ASSIGN exp TO exp DO exp
   | BREAK
   | LET decs IN exps END
   ;  
    	
exps 	:	(exp (SEMICOLON exp)*)?
	; 
oforlvaluep
	: OF exp
	| lvaluep (ASSIGN exp)?
	;
lvaluep	: DOT ID lvaluep
	| LBRACK exp RBRACK lvaluep
	| /* epsilon */
	;
	
// START:tokens
COMMENT 
    :  '/*' .*? '*/'  -> skip;
    

STRING :   '"' (~('"'|'\r'|'\n'))* '"';
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
ID : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// END:tokens
