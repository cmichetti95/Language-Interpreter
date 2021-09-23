class Input implements Stmt {
	Id id;
	
	public void parse() {
		Parser.scanner.nextToken();
		id = new Id();
		id.parse();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
	public void semantic() {
		id.semantic();
	}
	
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.print("input ");
		id.print();
		System.out.println(";");
	}
	
	//get the input file's current constant and assign it to the id we stored.
	//Then, call next token.
	public void execute() {
		if(Executor.input.currentToken() == Core.EOF) {
			System.out.println("ERROR: Could not read further input!");
			System.exit(0);
		}
		Executor.assignValue(id.identifier, Executor.input.getCONST());
		Executor.input.nextToken();
	}
}