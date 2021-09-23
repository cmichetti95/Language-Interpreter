import java.util.*;

public class DeclSeq {
	Decl d;
	DeclSeq ds;

	void parse() {
		d = new Decl();
		d.parse();
		if (Parser.scanner.currentToken() != Core.BEGIN) {
			ds = new DeclSeq();
			ds.parse();
		}
	}

	void semantic() {
		d.semantic();
		if (ds != null) {
			ds.semantic();
		}
	}

	void print(int indent) {
		d.print(indent);
		if (ds != null) {
			ds.print(indent);
		}
	}
}
