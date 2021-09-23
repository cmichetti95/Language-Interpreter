class DeclInt {
	IdList list;
	
	public void parse() {
		Parser.expectedToken(Core.INT);
		Parser.scanner.nextToken();
		list = new IdList();
		list.parse();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
	public void semantic() {
		list.semanticIntVars();
	}
	
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.print("int ");
		list.print();
		System.out.println(";");
	}
	
	//function for executing declInt.
	public void execute() {
		
		//idlist will have two execute functions. We want to make sure we call it's
		//function that will execute ints as opposed to classes.
		list.executeInt();
	}
}