class Assign implements Stmt {
	int type;
	Id assignTo;
	Id assignFrom;
	Expr expr;
	
	public void parse() {
		assignTo = new Id();
		assignTo.parse();
		Parser.expectedToken(Core.ASSIGN);
		Parser.scanner.nextToken();
		if (Parser.scanner.currentToken() == Core.NEW) {
			type = 1;
			Parser.scanner.nextToken();
		} else if (Parser.scanner.currentToken() == Core.CLASS) {
			type = 2;
			Parser.scanner.nextToken();
			assignFrom = new Id();
			assignFrom.parse();
		} else {
			type = 3;
			expr = new Expr();
			expr.parse();
		}
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
	public void semantic() {
		assignTo.semantic();
		if (type == 1) {
			if (assignTo.checkType() != Core.CLASS) {
				System.out.println("ERROR: int variable used in new assignment");
				System.exit(0);
			}
		} else if (type == 2) {
			if (assignTo.checkType() != Core.CLASS) {
				System.out.println("ERROR: int variable used in class assignment");
				System.exit(0);
			}
			if (assignFrom.checkType() != Core.CLASS) {
				System.out.println("ERROR: int variable used in class assignment");
				System.exit(0);
			}
		} else {
			expr.semantic();
		}
	}
	
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		assignTo.print();
		System.out.print("=");
		if (type == 1) {
			System.out.print("new");
		} else if (type == 2) {
			System.out.print("class ");
			assignFrom.print();
		} else {
			expr.print();
		}
		System.out.println(";");
	}
	
	//different possible assigns
	public void execute() {
		if (type == 1) {
			//In this case, we're taking the identifier, and we're now giving it a heap space.
			Executor.assignClass(assignTo.identifier);
		} else if (type == 2) {
			//In this case, we're assigning a reference
			Executor.assignRef(assignFrom.identifier, assignTo.identifier);
		} else {
			Executor.assignValue(assignTo.identifier, expr.execute());
		}
	}
}

