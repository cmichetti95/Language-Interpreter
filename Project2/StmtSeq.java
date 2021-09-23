import java.util.*;

public class StmtSeq {

	Stmt s;
	StmtSeq ss;

	void parse() {
		s = new Stmt();
		s.parse();
		if (Parser.scanner.currentToken() != Core.END && Parser.scanner.currentToken() != Core.ENDIF 
				&& Parser.scanner.currentToken() != Core.ENDWHILE && Parser.scanner.currentToken() != Core.ELSE) {
			ss = new StmtSeq();
			ss.parse();
		}
	}

	void semantic() {
		s.semantic();
		if (ss != null) {
			ss.semantic();
		}

	}

	void print(int indent) {
		s.print(indent);
		if (ss != null) {
			ss.print(indent);
		}
	}
}
