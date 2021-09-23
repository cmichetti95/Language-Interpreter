
public class FuncDecl {
	Id id;
	Formals f;
	StmtSeq ss;
	
	void parse() {
		//Here we'll check for expected tokens and parse when needed
		//parse id
		id = new Id();
		id.parse();
		
		//lparen
		Parser.expectedToken(Core.LPAREN);
		Parser.scanner.nextToken();
		
		//class
		Parser.expectedToken(Core.CLASS);
		Parser.scanner.nextToken();
		
		//parse formals
		f = new Formals();
		f.parse();
		
		//rparen
		Parser.expectedToken(Core.RPAREN);
		Parser.scanner.nextToken();
		
		//begin
		Parser.expectedToken(Core.BEGIN);
		Parser.scanner.nextToken();
		
		//parse ss
		ss = new StmtSeq();
		ss.parse();
		
		//endfunc
		Parser.expectedToken(Core.ENDFUNC);
		Parser.scanner.nextToken();
		
	}
	
	void print() {
		
	}
	
	void execute() {
		
		//here we're grabbing the id, because it's the name of the function,
		//then we're putting it onto our function hashmap.
		String temp = id.getString();
		Executor.functions.put(temp, this);
	}

}
