Name: Connor Michetti

Files Submitted:

Folder 'Correct': A folder containing test cases that are correctly formatted so the parser should output these properly.

Folder 'Error': A folder containing test cases that are correctly formatted so the parser should not output these properly.

tester: Script to run the tests.

Assign.java: Parses the Assign portion of the language.
Cmpr.java: Parses the Compare portion of the language.
Cond.java: Parses the Condition portion of the language.
Decl.java: Parses the Declare portion of the language.
DeclClass.java: Parses the Declare Class portion of the language.
DeclInt.java: Parses the Declare Int portion of the language.
DeclSeq.java: Parses the Declare Sequence portion of the language.
Expr.java: Parses the Expression portion of the language.
Factor.java: Parses the Factor portion of the language.
IDList.java: Parses the ID List portion of the language.
If.java: Parses the If portion of the language.
In.java: Parses the Input portion of the language.
Loop.java: Parses the While Loop portion of the language.
Main.java: Initialized the scanner and loads the scanner into the parser.
Out.java: Parses the Output portion of the language.
Parser.java: Contains many static variables and helper functions to aid in parsing.
Prog.java: The top of the tree, parses the Program portion of the language. This is the "start" of the parsing.
Scanner.java: Professor's provided scanner, which reads the input file and returns a list of tokens.
Stmt.java: Parses the Statement portion of the language.
StmtSeq.java: Parses the Statement Sequence portion of the language.
Term.java: Parses the Term portion of the language.

Description of the overall design:
This is designed in a way such that the scanner provides the parser a list of tokens. With this list, the parser uses each class as a node of the tree and it's children are the other classes thats parse functions get called by the parent class. This recursive structure allows the parser to start at the head of the tree, program, and recursively parse each of it's children, checking semantics and printing as it goes from the bottom up.

How it was tested: For bug testing/resolving I mostly relied on tracing errors through the parser and inserting print statements to catch where bugs were occurring. Other than this, I used test code, both properly and improperly formatted to ensure the parser was printing properly for proper files and throwing errors for semantic issues.

Known bugs: None, however as an aside, during an office hours I asked the professor "Can I just put "invalid Factor" for these error checks" and was told it was fine, so I hope I don't get docked for this as my Parser *is* detecting that there is an error and not just seg faulting or something.