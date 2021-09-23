import java.util.*;

public class If {

	Cond c;
	StmtSeq ss;
	StmtSeq ssTwo;
	
	void parse() {
	
		Parser.errorChecker(Core.IF);
		Parser.scanner.nextToken();
		
		c = new Cond();
		c.parse();
		
		Parser.errorChecker(Core.THEN);
		Parser.scanner.nextToken();
		
		ss = new StmtSeq();
		ss.parse();
		
		if(Parser.scanner.currentToken() == Core.ENDIF) {
			Parser.scanner.nextToken();
			
		}else if(Parser.scanner.currentToken() == Core.ELSE) {
			Parser.scanner.nextToken();
			ssTwo = new StmtSeq();
			ssTwo.parse();
			
			Parser.errorChecker(Core.ENDIF);
			Parser.scanner.nextToken();
		}else {
			System.out.println("ERROR: Expected ELSE or ENDIF");
			System.exit(0);
		}
	}
	
	void semantic() {
		HashMap<String, Core> newScope = new HashMap<String,Core>();
		Parser.inScope.push(newScope);
		
		c.semantic();
		ss.semantic();
		Parser.inScope.pop();
		
		if(ssTwo != null) {
			HashMap<String, Core> newScopeTwo = new HashMap<String,Core>();
			Parser.inScope.push(newScopeTwo);
			ssTwo.semantic();
			Parser.inScope.pop();
		}
	}
	
	void print(int indent) {
		System.out.print("if ");
		c.print(indent);
		System.out.print(" then");
		System.out.println();
		ss.print(indent + 1);
		
		if(ssTwo != null) {
			System.out.print("else");
			System.out.println();
			ssTwo.print(indent + 1);
			System.out.print("endif");
			System.out.println();
		}
	}
}
