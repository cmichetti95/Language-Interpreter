import java.util.*;

public class Factor {

	String id;
	int constant = -1;
	Expr e;
	
	void parse() {
		if(Parser.scanner.currentToken() == Core.ID) {
			id = Parser.scanner.getID();
			Parser.scanner.nextToken();
			
		}else if(Parser.scanner.currentToken() == Core.CONST) {
			constant = Parser.scanner.getCONST();
			Parser.scanner.nextToken();
			
		}else if(Parser.scanner.currentToken() == Core.LPAREN) {
			Parser.scanner.nextToken();
			e = new Expr();
			e.parse();
			
			Parser.errorChecker(Core.RPAREN);
			Parser.scanner.nextToken();
		}else {
			System.out.println("ERROR: Invalid Factor");
			System.exit(0);
		}
	}
	
	void semantic() {
		if(id != null) {
			
			if(!Parser.checkNestedScope(id)) {
				System.out.println("ERROR: ID " + id +  " not declared");
				System.exit(0);
			}
		}else if(e != null) {
			e.semantic();
		}
	}
	
	void print(int indent) {
		if(id != null) {
			System.out.print(" " + id);
		}else if (constant != -1) {
			System.out.print(" " + constant);
		}else {
			System.out.print(" (");
			e.print(indent);
			System.out.print(" )");
		}
	}
}
