
public class TryLoader2 extends JavaParserBaseListener {

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.println("Context is:" + ctx.getText());
    }

    // void exitImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
    // ctx.enterRule(listener).enterClassDeclaration();
    // }

    // public void enterTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {

    // ctx.getRuleIndex().ClassDeclarationContex;
    // }
}
