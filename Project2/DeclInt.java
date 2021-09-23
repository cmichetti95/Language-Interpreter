import java.util.*;

public class DeclInt {

	IDList idl;
	String id;
	
	void parse() {
		
		Parser.errorChecker(Core.INT);
		Parser.scanner.nextToken();
		
		idl = new IDList();
		idl.parse();
		
		Parser.errorChecker(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
	void semantic() {
		
		idl.semantic(Core.INT);
	}
	
	void print(int indent) {
		System.out.print(" int");
		idl.print(indent);
		System.out.print(";");
		System.out.println();
	}
}
