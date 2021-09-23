import java.util.*;

public class Loop {

	Cond c;
	StmtSeq ss;
	
	void parse() {
		Parser.errorChecker(Core.WHILE);
		Parser.scanner.nextToken();
		
		c = new Cond();
		c.parse();
		
		Parser.errorChecker(Core.BEGIN);
		Parser.scanner.nextToken();
		
		ss = new StmtSeq();
		ss.parse();
		
		Parser.errorChecker(Core.ENDWHILE);
		Parser.scanner.nextToken();
	}
	
	void semantic() {
		HashMap<String, Core> newScope = new HashMap<String,Core>();
		Parser.inScope.push(newScope);
		
		c.semantic();
		ss.semantic();
		
		Parser.inScope.pop();
	}
	
	void print(int indent) {
		System.out.print("while ");
		c.print(indent);
		System.out.print(" begin");
		System.out.println();
		ss.print(indent + 1);
		System.out.print("endwhile");
		System.out.println();
	}
}
