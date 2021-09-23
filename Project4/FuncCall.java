import java.util.*;

class FuncCall implements Stmt {
	Id id;
	Formals f;
	
	public void parse() {
		
		//check for tokens, parse formals.
		//begin
		Parser.expectedToken(Core.BEGIN);
		Parser.scanner.nextToken();
		
		//parse id
		id = new Id();
		id.parse();
		
		//lparen
		Parser.expectedToken(Core.LPAREN);
		Parser.scanner.nextToken();
		
		//parse formals
		f = new Formals();
		f.parse();
		
		//rparen
		Parser.expectedToken(Core.RPAREN);
		Parser.scanner.nextToken();
		
		//semicolon
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
	public void print(int indent) {
		
	}
	
	public void execute() {
		
		//Frame in case of recursion. Make sure we
		//push a hashmap onto this frame
		Stack<HashMap<String, CoreVar>> temp = new Stack<HashMap<String, CoreVar>>();
		temp.push(new HashMap<String, CoreVar>());
			
		//Grabbing formal parameters
		ArrayList<String> formals = Executor.functions.get(id.getString()).f.execute();
		ArrayList<String> otherVars = f.execute();
		
		//What we're doing here is copying the parameters for what is going to be used
		//to execute a function. In other words, for each of the formal params, get the
		//actual values that we're using in the function body.
		for(int i = 0; i < otherVars.size(); i++) {
			CoreVar variableForRef = Executor.getStackOrStatic(otherVars.get(i));
			CoreVar copy = new CoreVar(Core.CLASS);
			copy.value = variableForRef.value;
			temp.peek().put(formals.get(i), copy);
		}
		
		//push our stackspace, execute the function, pop the stackspace.
		Executor.stackSpace.push(temp);
		Executor.functions.get(id.getString()).ss.execute();
		
		Executor.stackSpace.pop();
	}

}
