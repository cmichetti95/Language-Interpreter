import java.util.*;

public class Prog {
	DeclSeq ds;
	StmtSeq ss;
	
	void parse() {
		
		Parser.errorChecker(Core.PROGRAM);
		Parser.scanner.nextToken();
		if(Parser.scanner.currentToken() != Core.BEGIN) {
			ds = new DeclSeq();
			ds.parse();
		}
		
		Parser.errorChecker(Core.BEGIN);
		Parser.scanner.nextToken();
		ss = new StmtSeq();
		ss.parse();
		Parser.errorChecker(Core.END);
		Parser.scanner.nextToken();
		Parser.errorChecker(Core.EOF);
	}
	
	void semantic() {
		Parser.inScope = new Stack<HashMap<String, Core>>();
		HashMap<String, Core> global = new HashMap<String, Core>();
		Parser.inScope.push(global);
		ds.semantic();
		ss.semantic();
	}
	
	void print() {
		System.out.println("program");
		if(ds != null) {
			ds.print(1);
		}
		System.out.println("begin");
		ss.print(1);
		System.out.println("end");
	}

}
