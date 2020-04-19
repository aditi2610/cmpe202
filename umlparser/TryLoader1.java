import java.util.ArrayList;
import java.util.List;

public class TryLoader1 extends JavaParserBaseListener {

    static String className = "";
    static List<String> list;
    static String primitiveString;
    int flagForCollection = 0;
    String element = "";
    static List<String> extendingClasses;
    static List<String> implementingClasses;
    static List<String> usesList;

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        for (int i = 5; i < ctx.getText().length(); i++) {
            char c = ctx.getText().charAt(i);
            if (!(Character.isDigit(c) || Character.isLetter(c))) {
                break;
            } else {
                className += c;
            }
        }
        // className = ctx.getText().charAt(5);
        System.out.println("ClassName: " + className);
        extendingClasses = new ArrayList<String>();
        implementingClasses = new ArrayList<String>();
        usesList = new ArrayList<String>();
        if (ctx.getText().contains("extends")) {
            extendingClasses.add(ctx.typeType().classOrInterfaceType().getText());
        }
        if (ctx.getText().contains("implements")) {
            JavaParser.TypeListContext typeList = ctx.typeList();
            for (JavaParser.TypeTypeContext typeType : typeList.typeType()) {
                implementingClasses.add(typeType.classOrInterfaceType().getText());
            }

        }
        list = new ArrayList<String>();
        primitiveString = "";

    }

    @Override
    public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        for (String implementingClass : implementingClasses) {
            System.out.println("[<<interface>>; " + implementingClass + "]^-.-[" + className + "]");
        }
        for (String extendingClass : extendingClasses) {
            System.out.println("[" + extendingClass + "]^-[" + className + "]");
        }
        for (String useClasses : usesList) {
            System.out.println("[" + className + "]uses -.->[" + useClasses + "]");
        }
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                System.out.println("[" + className + " |" + primitiveString + "]" + list.get(i));
            } else {
                System.out.println("[" + className + "]" + "-" + list.get(i));
            }
        }

    }

    @Override
    public void enterMemberDeclaration(JavaParser.MemberDeclarationContext ctx) {
        flagForCollection = 0;
        element = "";
    }

    @Override
    public void enterClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx) {
        if (ctx.getText().equals("private")) {
            element = "-";
        }
    }

    @Override
    public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {

        if (ctx.typeType().primitiveType() != null) {
            primitiveString += element;
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
            element += "1[" + ctx.getText() + "]";
            list.add(element);
        }

    }

    @Override
    public void enterFormalParameterList(JavaParser.FormalParameterListContext ctx) {
        // System.out.println(ctx.getText());
        usesList.add(ctx.formalParameter(0).typeType().classOrInterfaceType().getText());

    }

}