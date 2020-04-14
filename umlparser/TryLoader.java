import java.util.List;

import java.util.ArrayList;
// public class B {

//     private int[] s;

// }

public class TryLoader extends JavaParserBaseListener {

    // static List<String> list = new ArrayList<String>();
    // String primitives;
    static String className = "";
    String primitiveList;
    List<String> classOrIntList;

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        // primitives = new String();
        primitiveList = "";
        classOrIntList = new ArrayList<String>();
        className = "[" + ctx.getText().charAt(5);
        // System.out.print(className);
        for (JavaParser.ClassBodyDeclarationContext body : ctx.classBody().classBodyDeclaration()) {

            JavaParser.ClassOrInterfaceTypeContext classOrInt = body.memberDeclaration().fieldDeclaration().typeType()
                    .classOrInterfaceType();

            JavaParser.ClassOrInterfaceModifierContext accessModifier = body.modifier(0).classOrInterfaceModifier();
            // JavaParser.MemberDeclarationContext memberDec = body.memberDeclaration();
            JavaParser.PrimitiveTypeContext primitiveType = body.memberDeclaration().fieldDeclaration().typeType()
                    .primitiveType();

            if (classOrInt != null) {
                // System.out.print("[" + ctx.getText().charAt(5));
                if (classOrIntList.size() == 1) {
                    System.out.println(primitiveList);
                }
                String classElement = "]";
                if (classOrInt.getText().contains("Collection")) {
                    classElement += "1-0..*";
                    classElement += "[" + (classOrInt.typeArguments().get(0).typeArgument(0).typeType().getText())
                            + "]";
                } else {
                    classElement += "- [" + classOrInt.getText() + "]";
                }
                classOrIntList.add(classElement);

            }

            if (primitiveType != null) {
                if ("private".equals(accessModifier.getText())) {
                    primitiveList += "-";
                }
                primitiveList += (body.memberDeclaration().fieldDeclaration().variableDeclarators().variableDeclarator()
                        .get(0).variableDeclaratorId().getText());
                primitiveList += (":" + primitiveType.getText() + ";");
            }

        }
    }

    @Override
    public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.print(className);
        if (primitiveList != null) {
            System.out.print("|");
        }

        for (String classOrInt : classOrIntList) {
            System.out.println(className + classOrInt);
        }
    }

    // public getVariableId()
    /**
     * this method gets the name for collections.
     * 
     * @param ctx is the Context passed.
     */
    public String getNameOfCollections(JavaParser.TypeArgumentContext ctx) {
        // System.out.println("Type Argument: " + ctx.typeType().getText());
        if (ctx.typeType().classOrInterfaceType() != null) {
            return ("[" + ctx.typeType().classOrInterfaceType().getText() + "]");
        } else
            return "";
    }

}
