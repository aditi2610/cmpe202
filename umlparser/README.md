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



# WEEK 2

commands used By Antlr are: 
tokens prints out the token stream.
-tree prints out the parse tree in LISP form.
-gui displays the parse tree visually in a dialog box.
-ps file.ps generates a visual representation of the parse tree in PostScript and stores it in file.ps. The parse tree figures in this chapter were generated with -ps.
-encoding encodingname specifies the test rig input file encoding if the current locale would not read the input properly. For example, we need this option to parse a Japanese-encoded XML file in Section 12.4, Parsing and Lexing XML, on page 224.
-trace prints the rule name and current token upon rule entry and exit. -diagnostics turns on diagnostic messages during parsing. This generates mes-
sages only for unusual situations such as ambiguous input phrases.
-SLL uses a faster but slightly weaker parsing strategy.
