import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Parser {

	public static Scanner scanner;

	// taking the same approach as shown in class with Stack of ArrayLists.
	public static Stack<HashMap<String, Core>> inScope;
	
	public static boolean isClass;

	// checks nested scope
	static boolean checkNestedScope(String tempStr) {
		boolean varInScope = false;

		if (!inScope.empty()) {
			HashMap<String, Core> temp = inScope.pop();
			varInScope = temp.containsKey(tempStr);
			if (!varInScope) {
				varInScope = checkNestedScope(tempStr);
			}
			inScope.push(temp);
		}
		return varInScope;
	}

	static boolean isProperCore(String tempStr) {
		boolean varInScope = false;
		Core obj;

		if (!inScope.empty()) {
			HashMap<String, Core> temp = inScope.pop();
			varInScope = temp.containsKey(tempStr);
			if (!varInScope) {
				varInScope = checkNestedScope(tempStr);
			} else if (varInScope) {
				obj = temp.get(tempStr);
				if (obj == Core.CLASS) {
					isClass = true;
				} else {
					isClass = false;
				}
			}
			inScope.push(temp);
		}
		return varInScope;
	}

	static boolean checkScope(String tempStr) {
		boolean varInScope = false;
		if (!inScope.empty()) {
			varInScope = inScope.peek().containsKey(tempStr);
		}
		return varInScope;
	}

	static void errorChecker(Core token) {
		if (scanner.currentToken() != token) {
			System.out.println("ERROR: Expected the token '" + token + "', but was instead " + scanner.currentToken());
			System.exit(0);
		}
	}
}
