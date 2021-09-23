import java.util.*;

public class Assign {

	Expr e;
	String id;
	int number;
	String idTwo;
	boolean isNew;

	void parse() {
		Parser.errorChecker(Core.ID);
		id = Parser.scanner.getID();
		Parser.scanner.nextToken();

		Parser.errorChecker(Core.ASSIGN);
		Parser.scanner.nextToken();

		if (Parser.scanner.currentToken() == Core.ID || Parser.scanner.currentToken() == Core.CONST
				|| Parser.scanner.currentToken() == Core.LPAREN) {
			
			e = new Expr();
			e.parse();
			//Parser.scanner.nextToken();
		} else if (Parser.scanner.currentToken() == Core.NEW) {
			Parser.scanner.nextToken();
			isNew = true;
		} else if (Parser.scanner.currentToken() == Core.CLASS) {
			Parser.scanner.nextToken();
			Parser.errorChecker(Core.ID);
			idTwo = Parser.scanner.getID();
			Parser.scanner.nextToken();
			isNew = false;

		} else {
			System.out.println("ERROR: invalid assign");
			System.exit(0);
		}

		Parser.errorChecker(Core.SEMICOLON);
		Parser.scanner.nextToken();

	}

	void semantic() {

		if (!Parser.checkNestedScope(id)) {
			System.out.println("ERROR: ID " + id + " not declared");
			System.exit(0);
		}
		if (e != null) {
			e.semantic();
		}else if(!isNew){
			Parser.isProperCore(id);
			if(!Parser.isClass) {
				System.out.println("ERROR: ID " + id + " is not declared as class!");
				System.exit(0);
			}
			Parser.isProperCore(idTwo);
			if(!Parser.isClass) {
				System.out.println("ERROR: ID " + idTwo + " is not declared as class!");
				System.exit(0);
			}
		}else {
			Parser.isProperCore(id);
			if(!Parser.isClass) {
				System.out.println("ERROR: ID " + id + " is not declared as class!");
				System.exit(0);
			}
		}
		
	}

	void print(int indent) {
		System.out.print(" " + id + " =");
		if(e != null) {
			e.print(indent);
		}else if(idTwo != null) {
			System.out.print(" class " + idTwo);
		}else {
			System.out.print(" new");
		}
		
		System.out.print(";");
		System.out.println();
		
	}
}
