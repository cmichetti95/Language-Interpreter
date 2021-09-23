import java.util.*;

public class Decl {

	DeclInt di;
	DeclClass dc;
	
	void parse() {
		if(Parser.scanner.currentToken() == Core.INT) {
			di = new DeclInt();
			di.parse();
		}else if(Parser.scanner.currentToken() == Core.CLASS) {
			dc = new DeclClass();
			dc.parse();
		}
	}
	
	void semantic() {
		if(di != null) {
			di.semantic();
		}else if(dc != null) {
			dc.semantic();
		}
	}
	
	void print(int indent) {
		for(int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		if(di != null) {
			di.print(indent);
		}else if(dc != null) {
			dc.print(indent);
		}
	}
}
