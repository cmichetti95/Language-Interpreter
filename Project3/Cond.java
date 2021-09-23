class Cond {
	Cmpr cmpr;
	Cond cond;
	int type = 0;
	
	void parse() {
		if (Parser.scanner.currentToken() == Core.NEGATION){
			Parser.scanner.nextToken();
			Parser.expectedToken(Core.LPAREN);
			Parser.scanner.nextToken();
			cond = new Cond();
			cond.parse();
			Parser.expectedToken(Core.RPAREN);
			Parser.scanner.nextToken();
		} else {
			cmpr = new Cmpr();
			cmpr.parse();
			if (Parser.scanner.currentToken() == Core.OR) {
				Parser.scanner.nextToken();
				cond = new Cond();
				cond.parse();
				type = 1;
			}
		}
	}
	
	void semantic() {
		if (cmpr == null) {
			cond.semantic();
		} else {
			cmpr.semantic();
			if (cond != null) {
				cond.semantic();
			}
		}
	}
	
	void print() {
		if (cmpr == null) {
			System.out.print("!(");
			cond.print();
			System.out.print(")");
		} else {
			cmpr.print();
			if (cond != null) {
				System.out.print(" or ");
				cond.print();
			}
		}
	}
	
	//will return a boolean for our if or while statements. Need to evaluate based on
	//what kind of condition, hence why check will be one of:
	// cmpr, !cond, cmpr || cond
	boolean execute() {
		boolean check;
		if(cmpr == null) {
			check = !(cond.execute());
		} else {
			if(type == 0) {
				check = cmpr.execute();
			}else {
				check = (cmpr.execute() || cond.execute());
			}
		}
		
		return check;
	}
}