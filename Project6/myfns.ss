;main function
(define (myinterpreter x) 
	(evalExpr(cadr x) '())
)

;want to handle each possible "expr" case. We're passing in a list of bindings
;in case there are any function calls OR stored variables used. evalCall handles
;actual function calls, my function just stores our function definition
(define (evalExpr x L)
	(cond
		((symbol? x) (lookUp x L))
		((integer? x) x)
		((equal? 'myadd (car x)) (evalMyAdd x L))
		((equal? 'mymul (car x)) (evalMyMult x L))
		((equal? 'myif (car x)) (evalMyIf x L))
		((equal? 'mysub (car x)) (evalMySub x L))
		((equal? 'mylet (car x)) (evalMyLet x L))
		((equal? 'myfunction (car x)) x)
		(else (evalCall x L))
	)
)

;evalMyIf/Add/Sub/Mult are all self explanatory, I'm basically just checking the values
;and returning the proper result after evaluating.

(define (evalMyIf x L)
	(cond
		(
			(equal? 0 (evalExpr (cadr x) L))
			(evalExpr (cadddr x) L)
		)
		(else (evalExpr (caddr x) L))
	)
)

(define (evalMyAdd x L)
	(+ (evalExpr (cadr x) L) (evalExpr (caddr x) L)
	)
)

(define (evalMySub x L)
	(- (evalExpr (cadr x) L) (evalExpr (caddr x) L)
	)
)

(define (evalMyMult x L)
	(* (evalExpr (cadr x) L) (evalExpr (caddr x) L)
	)
)

;this handles both cases of myLet
(define (evalMyLet x L)
	(evalExpr (cadddr x)
		(cons (cons (cadr x)(evalExpr (caddr x) L)) L
		)
	)
)

;looks up a binding between a "variable" and it's value OR a function name
;and its body
(define (lookUp x L)
	(cond
		((equal? x (caar L))
		(cdar L))
		(else (lookUp x (cdr L)))
	)
)

;here we're getting the function body which we previously stores and creating 
;a new list to evaluate the function call
(define (evalCall x L)
	(evalExpr
		(caddr (lookUp (car x) L))
		(cons (cons (cadr (lookUp (car x) L)) (evalExpr (cadr x) L)) L)
		
	)
)
