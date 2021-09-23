import java.util.*;

public class Cond {
	
	Cmpr cmp;
	Cond cond;
	
	void parse() {
		if(Parser.scanner.currentToken() == Core.NEGATION) {
			Parser.scanner.nextToken();
			Parser.errorChecker(Core.LPAREN);
			Parser.scanner.nextToken();
			cond = new Cond();
			cond.parse();
			
			
			Parser.errorChecker(Core.RPAREN);
			Parser.scanner.nextToken();
		}else {
			cmp = new Cmpr();
			cmp.parse();
			
			if(Parser.scanner.currentToken() == Core.OR) {
				Parser.scanner.nextToken();
				cond = new Cond();
				cond.parse();
			}
		}
			
			/*
		}else {
			System.out.println("ERROR: Invalid COND");
			System.exit(0);
		}
		*/
	}
	
	void semantic() {
		if(cmp != null && cond != null) {
			cmp.semantic();
			cond.semantic();
		}else if(cmp != null && cond == null) {
			cmp.semantic();
		}else {
			cond.semantic();
		}
	}
	
	void print(int indent) {
		if(cmp != null && cond != null) {
			cmp.print(indent);
			System.out.print(" or ");
			cond.print(indent);
		}else if(cmp != null && cond == null) {
			cmp.print(indent);
		}else {
			System.out.print("!(");
			cond.print(indent);
			System.out.print(")");
		}
	}

}
