Connor Michetti

Files:
Assign, Cmpr, Cond, Core, Decl, DeclClass, DeclInt, DeclSeq, Expr, Factor, Id, IdList, If, Input, Loop, Main, Output, Parser, Program, Scanner, Stmt, StmtSeq, Term (all .java):

These are all the same as the previous project, just added execute functions.

Executor: Only new file, class containing many helper functions as well as my memory space.

Overall design: Same as previous. Now with execute functions added in each class that traverse the tree built in a similar way to how project 2 was done. The executor holds a hashmap, a stack of hashmaps, and an arraylist to act as the different memory spaces where variables are stored and assigned values. The execute functions compute the logic on these variables.

Testing: I ran out of time to test, so I just tested using the files the professor provided