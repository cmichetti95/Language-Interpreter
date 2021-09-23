import java.util.*;

class Loop implements Stmt {
	Cond cond;
	StmtSeq ss;
	
	public void parse() {
		Parser.scanner.nextToken();
		cond = new Cond();;
		cond.parse();
		Parser.expectedToken(Core.BEGIN);
		Parser.scanner.nextToken();
		ss = new StmtSeq();
		ss.parse();
		Parser.expectedToken(Core.ENDWHILE);
		Parser.scanner.nextToken();
	}
	
	public void semantic() {
		cond.semantic();
		Parser.scopes.push(new HashMap<String, Core>());
		ss.semantic();
		Parser.scopes.pop();
	}
	
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.print("while ");
		cond.print();
		System.out.println(" begin");
		ss.print(indent+1);
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.println("endwhile");
	}
	
	//execute the while, while the condition is true. Be sure to push and pop scopes
	public void execute() {
		while(cond.execute()) {
			Executor.pushLocalScope();
			ss.execute();
			Executor.popLocalScope();
		}
	}
}