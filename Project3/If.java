import java.util.*;

class If implements Stmt {
	Cond cond;
	StmtSeq ss1;
	StmtSeq ss2;

	public void parse() {
		Parser.scanner.nextToken();
		cond = new Cond();
		;
		cond.parse();
		Parser.expectedToken(Core.THEN);
		Parser.scanner.nextToken();
		ss1 = new StmtSeq();
		ss1.parse();
		if (Parser.scanner.currentToken() == Core.ELSE) {
			Parser.scanner.nextToken();
			ss2 = new StmtSeq();
			ss2.parse();
		}
		Parser.expectedToken(Core.ENDIF);
		Parser.scanner.nextToken();
	}

	public void semantic() {
		cond.semantic();
		Parser.scopes.push(new HashMap<String, Core>());
		ss1.semantic();
		Parser.scopes.pop();
		if (ss2 != null) {
			Parser.scopes.push(new HashMap<String, Core>());
			ss2.semantic();
			Parser.scopes.pop();
		}
	}

	public void print(int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		System.out.print("if ");
		cond.print();
		System.out.println(" then");
		ss1.print(indent + 1);
		if (ss2 != null) {
			for (int i = 0; i < indent; i++) {
				System.out.print("	");
			}
			System.out.println("else");
			ss2.print(indent + 1);
		}
		for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		System.out.println("endif");
	}

	public void execute() {
		// Here we check if it's just a normal if statement. If so we execute it, being sure to push and pop new
		// scopes. If it's an if/else, we execute both portions, being sure once again to push and pop new scopes.
		if (ss2 == null) {
			if (cond.execute()) {
				Executor.pushLocalScope();
				ss1.execute();
				Executor.popLocalScope();
			}
		} else {
			if(cond.execute()) {
				Executor.pushLocalScope();
				ss1.execute();
				Executor.popLocalScope();
			} else {
				Executor.pushLocalScope();
				ss2.execute();
				Executor.popLocalScope();
			}
		}
	}
}