Name: Connor Michetti

Files: (Note, any in this initial list were NOT edited from the previous project(s), so I will not redescribe them).

Assign, Cmpr, Cond, Decl, DeclClass, DeclInt, Expr, Factor, Id, IdList, If, Input, Loop, Main, Output, Parser, Scanner, Stmt, Term (all .java).

Core.java: Edited by adding in new enum Core.ENDFUNC to deal with a end of function token.

DeclSeq.java: Logic was changed in the parser function similar to what it was before. It now accounts for if a func-decl needs parsed or func-decl and decl-seq. The execute function is also edited to match this. There's no special logic in this file, it is basically the same as it was last project, just with the added if logic to check if func-decls are involved.

Executor.java: Mostly the same as well, adjusted helper functions to account for stackSpace now being a stack of stacks of hashmaps instead of just a stack of hashmaps (will explain more below). Also added a new data member to hold functions by name.

Formals.java: This file parses and executes "Formals" objects, and when executed returns an array list of all of the names of variables used in a function.

FuncCall.java: Deals with parsing and executing FuncCalls similar to all other parse functions. Will describe more of how this executes below

FuncDecl.java: Deals with parsing and executing FuncDecls, similar to all other parse functions. Will describe more of how this works below.

Program.java: Slightly adjusted to now deal with the new way the stackSpace is set up being a stack of stack of hashmaps.

StmtSeq.java: Added logic for func-calls

Special Features/Comments: None other than the professor *did* say that my error messages for the error cases were fine as they were. I am also positive I covered all bases in the description below for the "overall design of the interpreter and how the call stack is implemented".

Description: To start off, I'll describe what data structures were changed or added. In executor, the stackSpace was adjusted so it is now a stack of stacks of hashmaps. This allows for the possibility of recursion because you can push a new "frame" onto this structure by pushing a new stack of hashmaps. As such, helper functions needed to be adjusted for the change, just by adding in ".peek()" for anything that used the stackSpace. Then, also in executor, a new datastructure - a hashmap from string to funcdecl was created. This allowed for function to be stored by name, and their values could easily be accessed as they were the "value" of the hashmap. As such, in funcDecl, we add the name and instance onto this data structure. In other words, we now have the name of the function and the name of it's formal parameters, and later on this will also give us access to the statement sequence that is the body of the function. The class Formal.java allows us to get the list of formal parameters, and when the functions are called in FuncCall, we grab all the stored info - function name, formal params - and we pass in our values by copying the references; and since we have the statement sequence associated with this function, we can execute it here with the correct values. As such, since we're call by sharing, we're adjusting values appropriately based on the references we received earlier. And that's pretty much all there is to this project: adding recursion capabilities as described, and storing/executing function/function calls as described.

Testing: I mainly just used the provided test functions and found no errors.