class Id {
	String identifier;
	
	void parse() {
		Parser.expectedToken(Core.ID);
		identifier = Parser.scanner.getID();
		Parser.scanner.nextToken();
	}
	
	void semantic() {
		if (Parser.nestedScopeCheck(identifier)==Core.ERROR) {
			System.out.println("ERROR: No matching declaration found: " + identifier);
			System.exit(0);
		}
	}
	
	//Called by IdList semantic functions to check for doubly declared variables
	void doublyDeclared() {
		if (Parser.currentScopeCheck(identifier)!=Core.ERROR) {
			System.out.println("ERROR: Doubly declared variable detected: " + identifier);
			System.exit(0);
		}
	}
	
	//Called by IdList semantic functions to add the variable to the scopes data structure in Parser
	void addToScopes(Core type) {
		Parser.scopes.peek().put(identifier, type);
	}
	
	//Called by Assign semantic function to check the declared type of the variable
	Core checkType() {
		return Parser.nestedScopeCheck(identifier);
	}
	
	void print() {
		System.out.print(identifier);
	}
	
	//adds the global variable to our global space
	void addToGlobal(CoreVar var) {
		Executor.globalSpace.put(identifier, var);
	}
	
	//adds an int or class to the stack space.
	void addToCurrentScope(CoreVar var) {
		Executor.stackSpace.peek().put(identifier, var);
	}
	
	//simply returns the current value of the given identifier.
	int execute() {
		return Executor.getValue(identifier);
	}
	
	
	
	
}