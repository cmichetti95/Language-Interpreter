import java.util.*;

public class Term {

	Factor f;
	Term t;
	
	void parse() {
		f = new Factor();
		f.parse();
		
		if(Parser.scanner.currentToken() == Core.MULT) {
			Parser.scanner.nextToken();
			t = new Term();
			t.parse();
		}
	}
	
	void semantic() {
		f.semantic();
		if(t != null) {
			t.semantic();
		}
	}
	
	void print(int indent) {
		f.print(indent);
		if(t != null) {
			System.out.print(" * ");
			t.print(indent);
		}
	}
}
