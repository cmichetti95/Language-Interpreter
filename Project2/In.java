import java.util.*;

public class In {
	
	String id;
	void parse() {
		
		Parser.errorChecker(Core.INPUT);
		Parser.scanner.nextToken();
		
		Parser.errorChecker(Core.ID);
		id = Parser.scanner.getID();
		Parser.scanner.nextToken();
		
		Parser.errorChecker(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
	void semantic() {
		if(!Parser.checkNestedScope(id)) {
			System.out.println("ERROR: ID " + id + " is undeclared!");
			System.exit(0);
		}
	}
	
	void print(int indent) {
		System.out.print(" input " + id + ";" );
		System.out.println();
	}

}
