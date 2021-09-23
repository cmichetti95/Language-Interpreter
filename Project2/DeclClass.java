import java.util.*;

public class DeclClass {

	IDList idl;
	

	void parse() {

		Parser.errorChecker(Core.CLASS);
		Parser.scanner.nextToken();
		
		idl = new IDList();
		idl.parse();
		
		Parser.errorChecker(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}

	void semantic() {
		idl.semantic(Core.CLASS);
	}

	void print(int indent) {
		System.out.print(" class");
		idl.print(indent);
		System.out.print(";");
		System.out.println();
	}
}
