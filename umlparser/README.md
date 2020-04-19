# Kanban Link : https://github.com/nguyensjsu/cmpe202-aditi2610/projects
# GitHub Link: https://github.com/nguyensjsu/cmpe202-aditi2610/tree/master/umlparser
# ReadMe: https://github.com/nguyensjsu/cmpe202-aditi2610/edit/master/umlparser/README.md

# Uml Parser

This application will take the Java code and generate UML diagram for the classes. It would be able to show the relationship like Association, Aggregation etc between the classes. We will be using the below mentioned tools and libraries for it.

# Tools and Libraries Used:

1. Antlr
(ANother Tool for Language Recognition) is a powerful parser generator for reading, processing, executing, or translating structured text or binary files. It's widely used to build languages, tools, and frameworks. From a grammar, ANTLR generates a parser that can build and walk parse trees.

2. Maven
Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information.

3. yUML
yUML is an online tool for creating and publishing simple UML diagrams. It can be used to create class and other UML diagrams. 

Note: This is a live document and would be updated from time to time.


# My Journal Notes:

SETUP COMMANDS USED:

  export CLASSPATH=".:/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH"
 $ alias antlr4='java -Xmx500M -cp "/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool'

$ alias grun='java -Xmx500M -cp "/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'

Commands used For Antlr :

1. tokens prints out the token stream.
2. -tree prints out the parse tree in LISP form.
3. -gui displays the parse tree visually in a dialog box.
4. -ps file.ps generates a visual representation of the parse tree in PostScript and stores it in file.ps. The parse tree figures in this chapter were generated with -ps.
5. -encoding encodingname specifies the test rig input file encoding if the current locale would not read the input properly. 
6. -trace prints the rule name and current token upon rule entry and exit. -diagnostics turns on diagnostic messages during parsing. This generates messages only for unusual situations such as ambiguous input phrases.
7. -SLL uses a faster but slightly weaker parsing strategy.

JAVA LEXER: The process of grouping characters into words or symbols (tokens) is called lexical analysis or simply tokenizing. We call a program that tokenizes the input a lexer. The lexer can group related tokens into token classes, or token types, such as INT (integers), ID (identifiers), FLOAT (floating-point numbers), and so on. The lexer groups vocabulary symbols into types when the parser cares only about the type, not the individual symbols. Tokens consist of at least two pieces of information: the token type (identifying the lexical structure) and the text matched for that token by the lexer.

JAVA PARSER: The ANTLR tool generates recursive-descent parsers from grammar rules  Recursive-descent parsers are really just a collection of recursive methods, one per rule. The descent term refers to the fact that parsing begins at the root of a parse tree and proceeds toward the leaves. Parser takes the tokens to recognize the sentence structure.Once we have a parsed a tree, we can use ParseTreeWalker to visit all of the nodes, triggering enter and exit methods.


                     LEXER                                    PARSER
          (Lexical Analyisis/ Tokenizing)           (By Default ANTLR generates a tree sturucture)
Input ===> Group into Characters/Tokens          ===>           takes tokens to recongnize the structure


PARSE TREE LISTENER: ANTLR provides support for two tree-walking mechanisms in its runtime library. By default, ANTLR generates a parse-tree listener interface that responds to events triggered by the built-in tree walker. To walk a tree and trigger calls into a listener, ANTLR’s runtime provides class ParseTreeWalker. ANTLR’s ParseTreeWalker triggers enter and exit methods for each rule subtree as it discovers and finishes nodes, respectively.

I created a CustomJavaParserLoader, TryLoader which extends the JavaParserListener and implements its methods. Using this class i was able to generate a string understable by the YUML for basic classes.


Output of Parser Tree:


(compilationUnit 
	(importDeclaration import 
		(qualifiedName java . util . Collection) 
	;) 
	(typeDeclaration 
		(classOrInterfaceModifier public) 
		(classDeclaration class A 
			(classBody { 
				(classBodyDeclaration 
					(modifier 
						(classOrInterfaceModifier private)
					) 
					(memberDeclaration 
						(fieldDeclaration 
							(typeType 
								(primitiveType int)
							) 
							(variableDeclarators 
								(variableDeclarator 
									(variableDeclaratorId x)
								)
							) ;
						)
					)
				) 
				(classBodyDeclaration 
					(modifier 
						(classOrInterfaceModifier private)
					) 
					(memberDeclaration 
						(fieldDeclaration 
							(typeType 
								(primitiveType int) [ ]
							) 
							(variableDeclarators 
								(variableDeclarator 
									(variableDeclaratorId y)
								)
							) ;
						)
					)
				) 
				(classBodyDeclaration 
					(modifier 
						(classOrInterfaceModifier private)
					)
					(memberDeclaration 
						(fieldDeclaration
							(typeType
							 	(classOrInterfaceType Collection 
							 	 	(typeArguments < 
							 	 		(typeArgument 
							 	 			(typeType 
							 	 				(classOrInterfaceType B)
							 	 			)
							 	 		) >
							 	 	)
							 	)
							) 
							(variableDeclarators 
								(variableDeclarator 
									(variableDeclaratorId b)
								)
							) ;
						)
					)
				) 
				(classBodyDeclaration 
					(modifier 
						(classOrInterfaceModifier private)
					) 
					(memberDeclaration 
						(fieldDeclaration 
							(typeType 
								(classOrInterfaceType C)
							) 
							(variableDeclarators 
								(variableDeclarator 
									(variableDeclaratorId c)
								)
							) ;
						)
					)
				) 
				(classBodyDeclaration 
					(modifier 
						(classOrInterfaceModifier private)
					) 
					(memberDeclaration 
						(fieldDeclaration 
							(typeType 
								(classOrInterfaceType Collection 
									(typeArguments < 
										(typeArgument 
											(typeType 
												(classOrInterfaceType D)
											)
										) >
									)
								)
							) 
							(variableDeclarators 
								(variableDeclarator 
									(variableDeclaratorId d)
								)
							) ;
						)
					)
				) }

			)
		)
	) <EOF>
)

By end of this week, i was able to generate above String. the next step should be to convert the String into yuml understable format for all the classes so to generate the diagram. 


I was working on Test 1 and Test 2 this week and was able to generate token understandable by YUML successfully. Now working on test 3. 

Multiple Extended classes and implemented Interfaces can now be processed to generate required String

Also, Antlr4 doesnt differentiate between interfaces and classes used by a class. This needs to be fixed seperately at the end.







