
public class TryLoader2 extends JavaParserBaseListener {

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.println("Context is:" + ctx.getText());
    }
}