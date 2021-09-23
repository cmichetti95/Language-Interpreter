Connor Michetti

Files Contained:
Assign, Cmpr, Cond, Core, Decl, DeclClass, DeclInt, DeclSeq, Expr, Factor, Formals, FuncCall, FuncDecl, Id, IdList, If, Input, Loop, Main, Output, Parser, Scanner, Stmt, StmtSeq, Term (all .java)

All files except for two (which are separated) are the exact same from Project 4 so I will not redescribe them.

Executor.java: Added several helper functions and a new data member called refCounts to keep track of how many references were made to variables. 

Program.java: Added a call to one of the Executor class helper functions.

Special Features: N/A

Description:
The way my Garbage Collector works is when a new heap spot is being allocated, I add a new spot at the end of the arraylist and set it to 1, because there now exists a reference there. Additionally, in the referenceCopy function I had to add logic in to check for a new reference count to a variable. Then, I added extra logic to the pushFrame function because this was called when a function call was made, so when the formals are used, a new reference needed to be made. Also, I added helper functions that would check for class variables being popped (when they go out of scope). This simply looped through the variables in one of the scopes and checked if they were classes and decremented their reference count if so. There's also a printGC function now which will print out the state of the GC whenever an increment happens or a reference count hits 0. Finally, I added a clearStatic method which clears out any remaining global variables when the program ends.

Testing: Used the provided test cases, no known bugs.
