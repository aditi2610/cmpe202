
/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
// import ANTLR's runtime libraries
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
//import org.antlr.v4.runtime.tree.gui.TreeViewer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Test {
    public static void main(String[] args) throws Exception {
        // create a CharStream that reads from standard input
        File folder = new File("umlparser/test/");
        File[] inputFiles = folder.listFiles();
        Map<String, String> props = new HashMap<String, String>();
        PropertyFileVisitor visitor = new PropertyFileVisitor();
        // System.out.println("No. of files are: " + inputFiles.length);
        for (File f : inputFiles) {
            if (f.isFile() && f.getName().endsWith(".java")) {
                //System.out.println("----------------");
                //System.out.println();
                //System.out.println("File is: " + f);
                CharStream input = CharStreams.fromFileName(f.toString());
                // CharStream input = CharStreams.fromFileName(f);
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
                //System.out.println(tree.toStringTree(parser)); // print LISP-style tree
                ParseTreeWalker walker = new ParseTreeWalker();
                // create listener then feed to walker PropertyFileLoader loader = new
                // CustomeJavaParserLoader loader = new CustomeJavaParserLoader();
                
                visitor.visit(tree);
                //System.out.println("Props:  " + visitor.props);


                TryLoader1 loader = new TryLoader1();
                System.out.println();
                walker.walk(loader, tree); // walk parse tree
                //System.out.println(loader.props); // print results

            }
           
             //System.out.println();
        }
        System.out.println();

        //uncomment this to get the interface and classes
        //System.out.println("Final Props:" + visitor.props);
    
    }

    public static class PropertyFileVisitor extends JavaParserBaseVisitor<Void> {
        List<String> list = new ArrayList<String>();
        List<String> listInterfaces = new ArrayList<String>();
        Map<String, List> props = new HashMap<String, List>();

        // PropertyFileVisitor(Map<String, String> props1){
        //     props = props1;

        // }
        @Override
        public Void visitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
            //System.out.println(" Hello  " + ctx.getText());
            list.add(ctx.IDENTIFIER().getText());
            props.put("Class", list);
            return null;
        }

        @Override
        public Void visitInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
            //System.out.println(" Interface  " + ctx.getText());
            listInterfaces.add(ctx.IDENTIFIER().getText());
            props.put("Interface", listInterfaces);
            return null;
        }

        

    }
   //visit interface
}
