import java.util.ArrayList;
import java.util.List;

public class TryLoader1 extends JavaParserBaseListener {

    static char className = ' ';
    static List<String> list;
    static String primitiveString;
    int flagForCollection = 0;

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        className = ctx.getText().charAt(5);
        list = new ArrayList<String>();
        primitiveString = "";

    }

    @Override
    public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {

        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                System.out.println("[" + className + " |" + primitiveString + "]" + list.get(i));
            } else {
                System.out.println("[" + className + "]" + list.get(i));
            }
        }

    }

    @Override
    public void enterMemberDeclaration(JavaParser.MemberDeclarationContext ctx) {
        flagForCollection = 0;
    }

    @Override
    public void enterClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx) {
        if (ctx.getText().equals("private")) {
            primitiveString += "-";
        }
    }

    @Override
    public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {

        if (ctx.typeType().primitiveType() != null) {
            primitiveString += ctx.variableDeclarators().variableDeclarator(0).variableDeclaratorId().getText();
            primitiveString += ":" + ctx.typeType().primitiveType().getText();
            if ((ctx.typeType().getText().charAt(ctx.typeType().getText().length() - 1)) == (']')) {
                primitiveString += "(*)";
            }
            primitiveString += ";";
        }
    }

    @Override
    public void enterClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx) {
        String element = "";
        if (ctx.getText().contains("Collection")) {
            element += "1-0..*";
            element += "[" + ctx.typeArguments().get(0).typeArgument().get(0).typeType().getText() + "]";
            flagForCollection = 1;
            list.add(element);
        } else if (flagForCollection == 0) {
            element += "- 1[" + ctx.getText() + "]";
            list.add(element);
        }

    }

}