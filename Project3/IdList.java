class IdList {
	Id id;
	IdList list;
	
	void parse() {
		id = new Id();
		id.parse();
		if (Parser.scanner.currentToken() == Core.COMMA) {
			Parser.scanner.nextToken();
			list = new IdList();
			list.parse();
		} 
	}
	
	void semanticIntVars() {
		id.doublyDeclared();
		id.addToScopes(Core.INT);
		if (list != null) {
			list.semanticIntVars();
		}
	}
	
	void semanticClassVars() {
		id.doublyDeclared();
		id.addToScopes(Core.CLASS);
		if (list != null) {
			list.semanticClassVars();
		}
	}
	
	void print() {
		id.print();
		if (list != null) {
			System.out.print(",");
			list.print();
		}
	}
	
	//execute function for IDlists when it's an int.
	void executeInt() {
		//check if stackSpace is empty. If it is, we know that we're in the 
		//global declarations, so we want to be sure to add them to the global
		//space.
		if(Executor.stackSpace.size() == 0) {
			
			//first we'll want a CoreVar. Then we'll call a helper function to put it
			//onto the global hashmap
			CoreVar newInt = new CoreVar(Core.INT);
			id.addToGlobal(newInt);
		} else {
			
			//now, we handle the case where the stackSpace isn't 0. This means that we're not
			//dealing with global variables.
			CoreVar newInt = new CoreVar(Core.INT);
			id.addToCurrentScope(newInt);
		}
		
		//last we'll want to check if there are more variables, and if so,
		//we want to execute again.
		if(list != null) {
			list.executeInt();
		}
	}
	
	//The same as executeInt with the exception of declaring the CoreVar object with Core.Class
	//instead. I won't repeat comments to save space.
	void executeClass() {
				if(Executor.stackSpace.size() == 0) {
					CoreVar newClass = new CoreVar(Core.CLASS);
					id.addToGlobal(newClass);
				} else {
					CoreVar newClass = new CoreVar(Core.CLASS);
					id.addToCurrentScope(newClass);
				}

				if(list != null) {
					list.executeInt();
				}
		
	}
}