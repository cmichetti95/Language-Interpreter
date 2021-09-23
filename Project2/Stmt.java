import java.util.*;

public class Stmt {

	Assign a;
	If i;
	Loop l;
	In in;
	Out o;
	Decl d;
	
	void parse() {
		if(Parser.scanner.currentToken() == Core.ID) {
			a = new Assign();
			a.parse();
		}else if(Parser.scanner.currentToken() == Core.IF) {
			i = new If();
			i.parse();
		}else if(Parser.scanner.currentToken() == Core.WHILE) {
			l = new Loop();
			l.parse();
		}else if(Parser.scanner.currentToken() == Core.INPUT) {
			in = new In();
			in.parse();
		}else if(Parser.scanner.currentToken() == Core.OUTPUT) {
			o = new Out();
			o.parse();
		}else if(Parser.scanner.currentToken() == Core.INT || Parser.scanner.currentToken() == Core.CLASS) {
			d = new Decl();
			d.parse();
		}
	}
	
	void semantic() {
		if(a != null) {
			a.semantic();
		}else if(i != null) {
			i.semantic();
		}else if(l != null) {
			l.semantic();
		}else if(in != null) {
			in.semantic();
		}else if(o != null) {
			o.semantic();
		}else if(d != null) {
			d.semantic();
		}
	}
	
	void print(int indent) {
		for(int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		if(a != null) {
			a.print(indent);
		}else if(i != null) {
			i.print(indent);
		}else if(l != null) {
			l.print(indent);
		}else if(in != null) {
			in.print(indent);
		}else if(o != null) {
			o.print(indent);
		}else if(d != null) {
			d.print(indent);
		}
	}
}
