import java.util.ArrayList;

public class Formals {
	Id id;
	Formals f;

	void parse() {

		// Grab the id, then check if another formal needs parsed.
		id = new Id();
		id.parse();

		// we'll check for more formals by seeing if the next token is a comma
		// if it is, we consume the comma, initialize f and parse
		if (Parser.scanner.currentToken() == Core.COMMA) {
			Parser.scanner.nextToken();
			f = new Formals();
			f.parse();
		}
	}

	void print() {

	}

	public ArrayList<String> execute() {

		//List of formals will hold the entire list of
		//ids declared. If f is null, we start the array list, then recursively
		//return it, each time adding the formal parameter (in order) to the array list.
		ArrayList<String> listOfFormals;
		
		if (f != null) {
			listOfFormals = f.execute();
			
		}else{
			listOfFormals = new ArrayList<String>();
		}
		
		listOfFormals.add(0, id.getString());
		
		return listOfFormals;
	}
}
