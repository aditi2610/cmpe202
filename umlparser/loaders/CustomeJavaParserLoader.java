
public class CustomeJavaParserLoader extends JavaParserBaseListener {
    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.print("[" + ctx.getText().charAt(5));
    }

    @Override
    public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.print("]");
    }

    @Override
    public void enterPrimitiveType(JavaParser.PrimitiveTypeContext ctx) {
        System.out.print(ctx.getText());
    }

    @Override
    public void exitPrimitiveType(JavaParser.PrimitiveTypeContext ctx) {

    }

    @Override
    public void enterClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx) {
        // System.out.print("[" + ctx.getText());
    }

    @Override
    public void exitClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx) {
        // System.out.print("]");
    }

    @Override
    public void enterVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx) {
        // System.out.print(ctx.getText() + ":");
    }

    @Override
    public void exitVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx) {

    }

    @Override
    public void enterTypeType(JavaParser.TypeTypeContext ctx) {
        System.out.print("[" + ctx.getText());
    }

    @Override
    public void exitTypeType(JavaParser.TypeTypeContext ctx) {
        System.out.print(ctx.getText());
    }

    @Override
    public void enterModifier(JavaParser.ModifierContext ctx) {
        if (ctx.getText().equals("private")) {
            System.out.print("-");
        }
    }
}