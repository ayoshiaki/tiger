grammar Tiger;


@header {
package tiger.parser;

}

prog: exp
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

	
typeid	:	ID;

tyfields:	ID COLON typeid (COMMA ID COLON typeid)* 
	|	/*epsilon */
	;

decs	:	dec+;
    
exp: andexp (OR andexp)*;
   
 	
andexp	:	compexp (AND compexp)*;
compexp	:	sumexp ((GE|LE|EQ|NEQ|LT|GT) sumexp)*;
sumexp	:	mulexp ((PLUS|MINUS) mulexp)*;
mulexp	:	atom ((TIMES|DIVIDE) atom)*;

atom	:      
    /* literals */
    NIL
   |INT
   |STRING
   |ID (ASSIGN exp)?
     /* function call */
   | ID LPAREN (exp (COMMA exp)*)? RPAREN 
   
   /* array and record creations */
   |typeid LBRACE (ID EQ exp (COMMA ID EQ exp)*)? RBRACE 
  
   /* variable, field, elements of an array, array creation *//
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
