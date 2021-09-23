import java.util.*;

public class Expr {

	Term t;
	Expr e;
	String plusOrMin;
	
	void parse() {
	
		t = new Term();
		t.parse();
		
		if(Parser.scanner.currentToken() == Core.ADD || Parser.scanner.currentToken() == Core.SUB) {
			if(Parser.scanner.currentToken() == Core.ADD) {
				plusOrMin = "+";
			}else {
				plusOrMin = "-";
			}
			Parser.scanner.nextToken();
			e = new Expr();
			e.parse();
		}
	}
	
	void semantic() {
		t.semantic();
		if(e != null) {
			e.semantic();
		}
	}
	
	void print(int indent) {
		t.print(indent);
		if(e != null) {
			System.out.print(" " + plusOrMin + " ");
			e.print(indent);
		}
	}
}
