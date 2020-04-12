
/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
// import ANTLR's runtime libraries
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Test {
    public static void main(String[] args) throws Exception {
        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromFileName("umlparser/uml-parser-test-1/B.java");
        // System.out.println("Input is: " + input);

        // create a lexer that feeds off of input CharStream
        JavaLexer lexer = new JavaLexer(input);

        // System.out.println("Lexer is: " + lexer);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // System.out.println("Tokens is: " + tokens);

        // create a parser that feeds off the tokens buffer
        JavaParser parser = new JavaParser(tokens);
        // System.out.println("parser is: " + parser);

        ParseTree tree = parser.compilationUnit(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree

        ParseTreeWalker walker = new ParseTreeWalker();
        // create listener then feed to walker PropertyFileLoader loader = new
        // CustomeJavaParserLoader loader = new CustomeJavaParserLoader();
        TryLoader loader = new TryLoader();
        walker.walk(loader, tree); // walk parse tree System.out.println(loader.props); // print results
        // System.out.println(loader);
    }
}
