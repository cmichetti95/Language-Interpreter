import java.util.*;

public class Cmpr {

	Expr e;
	Expr eTwo;
	String comparison;
	
	void parse() {
		e = new Expr();
		e.parse();
		
		if(Parser.scanner.currentToken() != Core.EQUAL && Parser.scanner.currentToken() != Core.LESS 
				&& Parser.scanner.currentToken() != Core.LESSEQUAL) {
			System.out.println("ERROR: Invalid comparison.");
			System.exit(0);
		}
		if(Parser.scanner.currentToken() == Core.EQUAL) {
			comparison = "==";
		}else if(Parser.scanner.currentToken() == Core.LESS) {
			comparison = "<";
		}else {
			comparison = "<=";
		}
		
		Parser.scanner.nextToken();
		eTwo = new Expr();
		eTwo.parse();
		
	}
	
	void semantic() {
		e.semantic();
		eTwo.semantic();
	}
	
	void print(int indent) {
		e.print(indent);
		System.out.print(" " + comparison + " ");
		eTwo.print(indent);
	}
}
