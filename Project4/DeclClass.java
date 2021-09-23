class DeclClass {
	IdList list;
	
	void parse() {
		Parser.expectedToken(Core.CLASS);
		Parser.scanner.nextToken();
		list = new IdList();
		list.parse();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
	void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.print("class ");
		list.print();
		System.out.println(";");
	}
	
	void execute() {
		list.executeClassIdList();
	}		
}