import java.util.*;

class CoreVar {
	Core type;
	Integer value;
	
	public CoreVar(Core varType) {
		type = varType;
		if (type == Core.INT) {
			value = 0;
		} else {
			value = null;
		}
	}
}

class Executor {
	
	static HashMap<String, CoreVar> globalSpace;
	static Stack<Stack<HashMap<String, CoreVar>>> stackSpace;
	static ArrayList<Integer> heapSpace;

	//new data structure that holds our functions
	static HashMap<String, FuncDecl> functions;
	
	
	static Scanner dataFile;
	
	static void initialize(String dataFileName) {
		globalSpace = new HashMap<String, CoreVar>();
		stackSpace = new Stack<Stack<HashMap<String, CoreVar>>>();
		heapSpace = new ArrayList<Integer>();
		dataFile = new Scanner(dataFileName);
		
		//initialize the functions
		functions = new HashMap<String, FuncDecl>();

	}
	
	static void pushLocalScope() {
		stackSpace.peek().push(new HashMap<String, CoreVar>());
	}
	
	static void popLocalScope() {
		stackSpace.peek().pop();
	}
	
	static int getNextData() {
		int data = 0;
		if (dataFile.currentToken() == Core.EOF) {
			System.out.println("ERROR: data file is out of values!");
			System.exit(0);
		} else {
			data = dataFile.getCONST();
			dataFile.nextToken();
		}
		return data;
	}
	
	static void allocate(String identifier, Core varType) {
		CoreVar record = new CoreVar(varType);
		// If we are in the DeclSeq, the local scope will not be created yet
		if (stackSpace.size()==0) {
			globalSpace.put(identifier, record);
		} else {
			stackSpace.peek().peek().put(identifier, record);
		}
	}
	
	static CoreVar getStackOrStatic(String identifier) {
		CoreVar record = null;
		for (int i=stackSpace.peek().size() - 1; i>=0; i--) {
			if (stackSpace.peek().get(i).containsKey(identifier)) {
				record = stackSpace.peek().get(i).get(identifier);
				break;
			}
		}
		if (record == null) {
			record = globalSpace.get(identifier);
		}
		return record;
	}
	
	static void heapAllocate(String identifier) {
		CoreVar x = getStackOrStatic(identifier);
		if (x.type != Core.CLASS) {
			System.out.println("ERROR: " + identifier + " is not of type class, cannot perform \"new\"-assign!");
			System.exit(0);
		}
		x.value = heapSpace.size();
		heapSpace.add(null);
	}
	
	static Core getType(String identifier) {
		CoreVar x = getStackOrStatic(identifier);
		return x.type;
	}
	
	static Integer getValue(String identifier) {
		CoreVar x = getStackOrStatic(identifier);
		Integer value = x.value;
		if (x.type == Core.CLASS) {
			try {
				value = heapSpace.get(value);
			} catch (Exception e) {
				System.out.println("ERROR: invalid heap read attempted!");
				System.exit(0);
			}
		}
		return value;
	}
	
	static void storeValue(String identifier, int value) {
		CoreVar x = getStackOrStatic(identifier);
		if (x.type == Core.CLASS) {
			try {
				heapSpace.set(x.value, value);
			} catch (Exception e) {
				System.out.println("ERROR: invalid heap write attempted!");
				System.exit(0);
			}
		} else {
			x.value = value;
		}
	}
	
	static void referenceCopy(String var1, String var2) {
		CoreVar x = getStackOrStatic(var1);
		CoreVar y = getStackOrStatic(var2);
		x.value = y.value;
	}

}