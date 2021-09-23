class Term {
	Factor factor;
	Term term;
	
	void parse() {
		factor = new Factor();
		factor.parse();
		if (Parser.scanner.currentToken() == Core.MULT) {
			Parser.scanner.nextToken();
			term = new Term();
			term.parse();
		}				
	}
	
	void semantic() {
		factor.semantic();
		if (term != null) {
			term.semantic();
		}
	}
	
	void print() {
		factor.print();
		if (term != null) {
			System.out.print("*");
			term.print();
		}
	}
	
	//term execute function. We will want to return a value here to be passed back up.
	int execute() {
		int value = factor.execute();
		
		//make sure to check if term is declared
		if(term != null) {
			value = value * term.execute();
		}
		
		return value;
	}
}