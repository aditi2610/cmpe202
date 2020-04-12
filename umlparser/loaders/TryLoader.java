
public class TryLoader extends JavaParserBaseListener {
    @Override
    public void enterTypeType(JavaParser.TypeTypeContext ctx) {
        if (ctx.getText().charAt(3).equ)
            System.out.print(ctx.getText());
    }

    @Override
    public void exitTypeType(JavaParser.TypeTypeContext ctx) {
        // System.out.print(ctx.getText());
    }

}