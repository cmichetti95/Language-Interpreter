class DeclSeq {
	Decl decl;
	DeclSeq ds;
	FuncDecl fd;
	Id id;
	
	void parse() {
		
		//check if it's a func decl
		if(Parser.scanner.currentToken() == Core.ID) {
			fd = new FuncDecl();
			fd.parse();
			
			//check if there's also a declseq to parse
			if(Parser.scanner.currentToken() != Core.BEGIN) {
				ds = new DeclSeq();
				ds.parse();
			}
			
		//otherwise it's a decl	
		} else {
			decl = new Decl();
			decl.parse();
			
			//check if there's a declseq to parse
			if (Parser.scanner.currentToken() != Core.BEGIN) {
				ds = new DeclSeq();
				ds.parse();
			}
		}
	}
	
	void print(int indent) {
		decl.print(indent);
		if (ds != null) {
			ds.print(indent);
		}
	}
	
	void execute() {
		
		//if fd isn't null, we know that we need to execute that
		if(fd != null) {
			fd.execute();
			
			//check if declseq is null, if not - execute
			if(ds != null) {
				ds.execute();
			}
		
		//otherwise it's decl possibly followed by ds
		} else {
			decl.execute();
			
			//check for the declseq
			if (ds != null) {
				ds.execute();
			}
		}
	}
}