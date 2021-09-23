import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** Connor Michetti **/

class Scanner {

	Core currentToken;
	BufferedReader in;
	StringBuilder sb;
	int currentConst;
	String currentID;

	// Constructor should open the file and find the first token
	Scanner(String filename) {
		File file = new File(filename);
		try {
			this.in = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		nextToken();
	}

	// nextToken should advance the scanner to the next token
	public void nextToken() {
        try {

            //First make sure we aren't at end of file
            int check = this.in.read();
            if (check == -1) {
                this.currentToken = Core.EOF;
            } else {
                char temp = (char) check;

                //Make sure we're skipping past whitespace to get to the first actual
                //char of the token.
                int check2 = 0;
                
                while (Character.isWhitespace(temp)) {
                	check2 = this.in.read();
                    temp = (char)check2;
                }
                
                
                if(check2 == -1) {
                	this.currentToken = Core.EOF;
                	
                //check if it's a special case first.
                }else if (temp == ';' || temp == ',' || temp == '!' || temp == '+'
                        || temp == '-' || temp == '*' || temp == '('
                        || temp == ')') {
                    if (temp == ';') {
                        this.currentToken = Core.SEMICOLON;
                    } else if (temp == ',') {
                        this.currentToken = Core.COMMA;
                    } else if (temp == '!') {
                        this.currentToken = Core.NEGATION;
                    } else if (temp == '+') {
                        this.currentToken = Core.ADD;
                    } else if (temp == '-') {
                        this.currentToken = Core.SUB;
                    } else if (temp == '*') {
                        this.currentToken = Core.MULT;
                    } else if (temp == '(') {
                        this.currentToken = Core.LPAREN;
                    } else if (temp == ')') {
                        this.currentToken = Core.RPAREN;
                    }

                    //another special case, check if it's assing or equality.
                } else if (temp == '=') {
                    this.in.mark(2);
                    char temp2 = (char) this.in.read();
                    if (temp2 == '=') {
                        this.currentToken = Core.EQUAL;
                        
                    } else {
                        this.currentToken = Core.ASSIGN;
                        this.in.reset();
                    }
                    

                    //another special case, check if it's < or <=
                } else if (temp == '<') {
                    this.in.mark(2);
                    char temp2 = (char) this.in.read();
                    if (temp2 == '=') {
                        this.currentToken = Core.LESSEQUAL;
                    } else {
                        this.currentToken = Core.LESS;
                        this.in.reset();
                    }


                    //if it's a character, we first build the string
                } else if (Character.isLetter(temp)) {
                    this.sb = new StringBuilder();
                    char temp2 = 'a';

                    while (Character.isLetter(temp2)
                            || Character.isDigit(temp2)) {
                        this.sb.append(temp);
                        this.in.mark(1);
                        temp2 = (char) this.in.read();
                        if (!Character.isLetter(temp2)
                                && !Character.isDigit(temp2)) {
                            this.in.reset();
                        } else {
                            temp = temp2;
                        }

                    }

                    //store into global variable
                    this.currentID = this.sb.toString();

                    //Check if keyword or identifier. If it matches all lowercase
                    //it's a keyword, otherwise it's an identifier.
                    switch (this.currentID) {
                        case "program":
                            this.currentToken = Core.PROGRAM;
                            break;
                        case "begin":
                            this.currentToken = Core.BEGIN;
                            break;
                        case "end":
                            this.currentToken = Core.END;
                            break;
                        case "new":
                            this.currentToken = Core.NEW;
                            break;
                        case "int":
                            this.currentToken = Core.INT;
                            break;
                        case "define":
                            this.currentToken = Core.DEFINE;
                            break;
                        case "endfunc":
                            this.currentToken = Core.ENDFUNC;
                            break;
                        case "class":
                            this.currentToken = Core.CLASS;
                            break;
                        case "extends":
                            this.currentToken = Core.EXTENDS;
                            break;
                        case "endclass":
                            this.currentToken = Core.ENDCLASS;
                            break;
                        case "if":
                            this.currentToken = Core.IF;
                            break;
                        case "then":
                            this.currentToken = Core.THEN;
                            break;
                        case "else":
                            this.currentToken = Core.ELSE;
                            break;
                        case "while":
                            this.currentToken = Core.WHILE;
                            break;
                        case "endwhile":
                            this.currentToken = Core.ENDWHILE;
                            break;
                        case "endif":
                            this.currentToken = Core.ENDIF;
                            break;
                        case "or":
                            this.currentToken = Core.OR;
                            break;
                        case "input":
                            this.currentToken = Core.INPUT;
                            break;
                        case "output":
                            this.currentToken = Core.OUTPUT;
                            break;
                        default:
                            this.currentToken = Core.ID;
                            break;
                    }

                    //last case, if it's a digit, build the const. If it's
                    //greater than 1023, then it's an error.
                } else if (Character.isDigit(temp)) {
                    this.sb = new StringBuilder();
                    char temp2 = '0';

                    while (Character.isDigit(temp2)) {
                        this.sb.append(temp);
                        this.in.mark(1);
                        temp2 = (char) this.in.read();
                        if (!Character.isDigit(temp2)) {
                            this.in.reset();
                        } else {
                            temp = temp2;
                        }

                    }
                    String tempStr = this.sb.toString();

                    //if strlen > 4 then there's no point checking it.
                    if (tempStr.length() > 4) {
                        this.currentToken = Core.ERROR;
                        System.out.println("Error! CONST too large!");

                    } else {
                        //first, parse to int. Then check if it's past our int limit.
                        this.currentConst = Integer.parseInt(tempStr);
                        if (this.currentConst > 1023 || this.currentConst < 0) {
                            this.currentToken = Core.ERROR;
                            System.out.println("Error! CONST too large!");
                        } else {
                            this.currentToken = Core.CONST;
                        }
                    }

                } else {
                    this.currentToken = Core.ERROR;
                    System.out.println(
                            "Error! " + temp + " is an invalid token.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error trying to read.");
        }

    }

	// currentToken should return the current token
	public Core currentToken() {

		return this.currentToken;
	}

	// If the current token is ID, return the string value of the identifier
	// Otherwise, return value does not matter
	public String getID() {
		return this.currentID;
	}

	// If the current token is CONST, return the numerical value of the constant
	// Otherwise, return value does not matter
	public int getCONST() {
		return this.currentConst;
	}

}