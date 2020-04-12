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

The process of grouping characters into words or symbols (tokens) is called lexical analysis or simply tokenizing. We call a program that tokenizes the input a lexer. The lexer can group related tokens into token classes, or token types, such as INT (integers), ID (identifiers), FLOAT (floating-point numbers), and so on. The lexer groups vocabulary symbols into types when the parser cares only about the type, not the individual symbols. Tokens consist of at least two pieces of information: the token type (identifying the lexical structure) and the text matched for that token by the lexer.

parser takes the tokens to recognize the sentence structure.


                     LEXER                                    PARSER
          (Lexical Analyisis/ Tokenizing)           (By Default ANTLR generates a tree sturucture)
Input ===> Group into Characters/Tokens          ===>           takes tokens to recongnize the structure
