import java.util.ArrayList;
import java.util.List;

public class TryLoader1 extends JavaParserBaseListener {

    static String className = "";
    static List<String> classVariablelist;
    // static String primitiveString;
    int flagForCollection = 0;
    String element = "";
    String classVariables = "";
    static List<String> extendingClasses;
    static List<String> implementingClasses;
    static List<String> usesList;
    List<String> memberVariables;
    List<String> primitiveVariables;
    boolean countVariable = false;

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        // Get class Name by first splitting on implements and then on extends
        className = "";
        // System.out.println("Implements: " + ctx.getText().split("implements")[0]);
        // System.out.println("Extends: " + ctx.getText().split("extends")[0]);
        String classn = ctx.getText().split("implements")[0].split("extends")[0];
        // System.out.println("final: " + classn);
        for (int i = 5; i < classn.length(); i++) {
            char c = classn.charAt(i);
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
        primitiveVariables = new ArrayList<String>();
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
        classVariablelist = new ArrayList<String>();
        // primitiveString = "";

    }

    @Override
    public void enterClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
        element = "";
        classVariables = "";
        countVariable = false;

        // if (modifier.equals("private") || modifier.equals("public")) {
        // element = "-";
        // }
    }

    @Override
    public void enterMemberDeclaration(JavaParser.MemberDeclarationContext ctx) {
        flagForCollection = 0;
    }

    @Override
    public void enterClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx) {
        if (ctx.getText().equals("private") || (ctx.getText().equals("public"))) {
            if (ctx.getText().equals("private")) {
                element = "-";
            }
            countVariable = true;
        }
    }

    @Override
    public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
        // String element = "";
        if (countVariable) {
            JavaParser.ClassOrInterfaceTypeContext classOrInterface = ctx.typeType().classOrInterfaceType();
            if (null != classOrInterface) {
                if (classOrInterface.getText().contains("Collection")) {
                    classVariables += "1-0..*";
                    classVariables += "["
                            + classOrInterface.typeArguments().get(0).typeArgument().get(0).typeType().getText() + "]";
                    classVariablelist.add(classVariables);

                } else if (classOrInterface.getText().contains("String")) {
                    element += ctx.variableDeclarators().variableDeclarator(0).variableDeclaratorId().getText();
                    element += ":" + ctx.typeType().classOrInterfaceType().getText();

                    // i might have to check array condition here
                    element += ";";
                    // System.out.println(element);
                    primitiveVariables.add(element);

                } else {
                    classVariables += "-[" + classOrInterface.getText() + "]";
                    classVariablelist.add(classVariables);
                }
                // System.out.println("Class Variable: " + classVariables);

            }
            if (ctx.typeType().primitiveType() != null) {
                String pElement = " ";

                element += ctx.variableDeclarators().variableDeclarator(0).variableDeclaratorId().getText();
                element += ":" + ctx.typeType().primitiveType().getText();
                if ((ctx.typeType().getText().charAt(ctx.typeType().getText().length() - 1)) == (']')) {
                    element += "(*)";
                }
                element += ";";
                // System.out.println("Primitive type is: " + element);
                primitiveVariables.add(element);
            }
        }

    }

    @Override
    public void enterFormalParameterList(JavaParser.FormalParameterListContext ctx) {
        // this condition has to be matched for int as well
        if (!ctx.formalParameter(0).typeType().classOrInterfaceType().getText().equals("String")) {
            usesList.add(ctx.formalParameter(0).typeType().classOrInterfaceType().getText());
        }

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
        // System.out.println("primitives:" + primitiveVariables.size());
        System.out.print("[" + className + "|");
        for (String primitive : primitiveVariables) {
            System.out.print(primitive);
        }
        System.out.print("]");
        for (int i = 0; i < classVariablelist.size(); i++) {
            if (i == 0) {

                System.out.println(classVariablelist.get(0));

            } else {
                System.out.println("[" + className + "]" + classVariablelist.get(i));
            }
        }

    }

}