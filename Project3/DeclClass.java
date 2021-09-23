class DeclClass {
	IdList list;
	
	public void parse() {
		Parser.expectedToken(Core.CLASS);
		Parser.scanner.nextToken();
		list = new IdList();
		list.parse();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
	public void semantic() {
		list.semanticClassVars();
	}
	
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.print("class ");
		list.print();
		System.out.println(";");
	}
	
	//function for executing declClass.
		public void execute() {
			
			//idlist will have two execute functions. We want to make sure we call it's
			//function that will execute classes as opposed to ints.
			list.executeClass();
		}
}