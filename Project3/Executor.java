import java.util.*;

//Class that will be used for the stack and global space. Stores the core
// type and the initial value of a variable
class CoreVar{
	Core type;
	Integer value;
	
	public CoreVar(Core varType) {
		type = varType;
		if(type == Core.INT) {
			value = 0;
		}else {
			value = null;
		}
	}
}

public class Executor {

	//Our three memory spaces plus a scanner for input files.
	static HashMap<String, CoreVar> globalSpace;
	static Stack<HashMap<String, CoreVar>> stackSpace;
	static ArrayList<Integer> heapSpace;
	
	static Scanner input;
	
	//method to initialize our memory spaces
	static void initialize(String file) {
		globalSpace = new HashMap<String, CoreVar>();
		stackSpace = new Stack<HashMap<String, CoreVar>>();
		heapSpace = new ArrayList<Integer>();
		input = new Scanner(file);
	}
	
	//helper methods to add and remove new scopes
	static void pushLocalScope() {
		HashMap<String, CoreVar> temp = new HashMap<String, CoreVar>();
		stackSpace.push(temp);
	}
	
	static void popLocalScope() {
		stackSpace.pop();
	}
	
	//this is the function that assigns a value to an int or a class. It also updates the heapspace value if it's a class.
	static void assignValue(String id, int value) {
		CoreVar temp = getStackOrStatic(id);
		if(temp.type == Core.INT) {
			temp.value = value;
		}else {
			heapSpace.set(temp.value, value);
		}
		
	}
	
	//assigns a heap space to a new class
	static void assignClass(String id) {
		CoreVar temp = getStackOrStatic(id);
		heapSpace.add(heapSpace.size());
		temp.value = heapSpace.size() - 1;
	}
	
	static void assignRef(String id1, String id2) {
		CoreVar temp = getStackOrStatic(id1);
		int value = temp.value;
		CoreVar temp2 = getStackOrStatic(id2);
		temp2.value = value;
	}
	
	//provided by professor, loops through stackspace to find a variable.
	static CoreVar getStackOrStatic(String identifier) {
		CoreVar record = null;
		for (int i=stackSpace.size() - 1; i>=0; i--) {
			if (stackSpace.get(i).containsKey(identifier)) {
				record = stackSpace.get(i).get(identifier);
				break;
			}
		}
		if (record == null) {
			record = globalSpace.get(identifier);
		}
		return record;
	}

	//gets the value for assigning.
	static int getValue(String id) {
		int value;
		CoreVar temp = getStackOrStatic(id);
		if(temp.type == Core.INT) {
			value = temp.value;
		}else {
			value = heapSpace.get(temp.value);
		}
		
		return value;
	}
}
