class DeclSeq {
	Decl decl;
	DeclSeq ds;
	
	void parse() {
		decl = new Decl();
		decl.parse();
		if (Parser.scanner.currentToken() != Core.BEGIN) {
			ds = new DeclSeq();
			ds.parse();
		}
	}
	
	void semantic() {
		decl.semantic();
		if (ds != null) {
			ds.semantic();
		}
	}
	
	void print(int indent) {
		decl.print(indent);
		if (ds != null) {
			ds.print(indent);
		}
	}
	
	//function for executing declseq
	void execute() {
		
		//always execute the decl, check if ds is null and if not, we need to execute
		//that as well
		decl.execute();
		if(ds != null) {
			ds.execute();
		}
	}
}