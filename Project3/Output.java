class Output implements Stmt {
	Expr expr;
	
	public void parse() {
		Parser.scanner.nextToken();
		expr = new Expr();
		expr.parse();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
	public void semantic() {
		expr.semantic();
	}
	
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.print("output ");
		expr.print();
		System.out.println(";");
	}
	
	//simply print out the expr execute.
	public void execute() {
		System.out.print(expr.execute());
	}
}