import java.util.HashMap;
import java.util.Map;

public static class MyVisitor extends JavaParserBaseVisitor<Void> {

    Map<String, String> props = new HashMap<String, String>();

    @Override
    public void visitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.println("Visitor : " + ctx.getText());
        props.put("Class", ctx.ctx.IDENTIFIER().getText());
        
    }

    @Override 
    public void visitCompilationUnit(JavaParser.CompilationUnitContext ctx) { 
        System.out.println("Isid");
        return visitChildren(ctx); 
    }

}