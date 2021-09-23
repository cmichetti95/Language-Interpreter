import java.util.*;

public class IDList {

	IDList idl;
	String id;
	
	void parse() {
		Parser.errorChecker(Core.ID);
		id = Parser.scanner.getID();
		Parser.scanner.nextToken();
		
		if(Parser.scanner.currentToken() == Core.COMMA) {
			Parser.scanner.nextToken();
			idl = new IDList();
			idl.parse();
		}
		
		
	}
	
	void semantic(Core obj) {
		if(Parser.checkScope(id)) {
			System.out.println("ERROR: Duplicate variable declaration");
			System.exit(0);
		}
		Parser.inScope.peek().put(id, obj);
		if(idl != null) {
			idl.semantic(obj);
		}
	}
	
	void print(int indent) {
		System.out.print(" " + id);
		if(idl != null) {
			System.out.print(",");
			idl.print(indent);
		}
	}
}
