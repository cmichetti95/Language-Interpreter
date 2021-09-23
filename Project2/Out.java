import java.util.*;

public class Out {
	
	Expr e;

	void parse() {
		Parser.errorChecker(Core.OUTPUT);
		Parser.scanner.nextToken();
		
		e = new Expr();
		e.parse();
		
		Parser.errorChecker(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
	void semantic() {
		e.semantic();
	}
	
	void print(int indent) {
		System.out.print(" output");
		e.print(indent);
		System.out.print(";");
		System.out.println();
	}
}
