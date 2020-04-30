import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import java.util.HashSet;

public class TryLoader1 extends JavaParserBaseListener {

    static String className = "";
    static List<String> classVariablelist;
    // static String primitiveString;
    int flagForCollection = 0;
    String element = "";
    String classVariables = "";
    String methodVariables = "";
    static List<String> extendingClasses;
    static List<String> implementingClasses;
    static List<String> usesList;
    List<String> memberVariables;
    List<String> abstractMethodsList;
    List<String> primitiveVariables;
    boolean countVariable = false;
    boolean countMethod = false;
    List<String> methodList;
    Set<String> output = new HashSet<String>();

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        // Get class Name by first splitting on implements and then on extends
        className = "";
        className = ctx.IDENTIFIER().getText();
        // // System.out.println("final: " + classn);
        extendingClasses = new ArrayList<String>();
        implementingClasses = new ArrayList<String>();
        primitiveVariables = new ArrayList<String>();
        methodList = new ArrayList<String>();
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
    }

    @Override
    public void enterClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
        element = "";
        classVariables = "";
        countVariable = false;
        countMethod = false;
        methodVariables = "";

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
            if (ctx.getText().equals("public")) {
                methodVariables = "+";
                countMethod = true;

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
                // System.out.println("Identifier is : " +classOrInterface.getText());
                if (classOrInterface.getText().contains("Collection")
                        || classOrInterface.getText().contains("ArrayList")) {
                    classVariables += "1-0..*";
                    classVariables += "["
                            + classOrInterface.typeArguments().get(0).typeArgument().get(0).typeType().getText() + "]";

                    classVariablelist.add(classVariables);

                } else if (classOrInterface.getText().contains("String")) {
                    element += ctx.variableDeclarators().variableDeclarator(0).variableDeclaratorId().getText();
                    element += ":" + ctx.typeType().classOrInterfaceType().getText();

                    // i might have to check array condition here
                    // System.out.println(element);
                    primitiveVariables.add(element);

                } else {
                    if (classOrInterface.getText().contains("String")
                            || classOrInterface.getText().contains("Double")) {
                        return;
                    }
                    // System.out.println(" $$$$$$$$$$$ ClassVaribales: "+ classVariables + "
                    // classORiNterface "+ classOrInterface.getText());
                    classVariables += "-[" + classOrInterface.getText() + "]";
                    classVariablelist.add(classVariables);
                }

            }
            if (ctx.typeType().primitiveType() != null) {
                String pElement = " ";

                element += ctx.variableDeclarators().variableDeclarator(0).variableDeclaratorId().getText();
                element += ":" + ctx.typeType().primitiveType().getText();
                if ((ctx.typeType().getText().charAt(ctx.typeType().getText().length() - 1)) == (']')) {
                    element += "(*)";
                }
                primitiveVariables.add(element);
            }
        }

    }

    @Override
    public void enterFormalParameterList(JavaParser.FormalParameterListContext ctx) {
        if (null != ctx.formalParameter(0).typeType().classOrInterfaceType()) {
            String s = ctx.formalParameter(0).typeType().classOrInterfaceType().getText();
            if (!s.contains("String") && !s.contains(("Double"))) {
                usesList.add(ctx.formalParameter(0).typeType().classOrInterfaceType().getText());
            }
        }

    }

    @Override
    public void enterInterfaceBodyDeclaration(JavaParser.InterfaceBodyDeclarationContext ctx) {
        methodVariables = "";
    }

    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        if (countMethod) {
            String returnType = ctx.typeTypeOrVoid().getText();

            String methodName = ctx.IDENTIFIER().getText();
            methodName = getParameterTypeAndId(ctx.formalParameters(), methodName);
            if (methodName.startsWith("get") || methodName.startsWith("set")) {
                for (String variable : primitiveVariables) {
                    String variableName = variable.split(":")[0].substring(1);
                    // System.out.println("Variable name: " + variableName + " Method Name: " +
                    // methodName.substring(3, methodName.indexOf("(")));
                    if (methodName.substring(3, methodName.indexOf("(")).equalsIgnoreCase(variableName)) {
                        // System.out.println(" found!!! Method Name is: " + methodName + " variable is:
                        // " + variableName
                        // + " get" + variableName);
                        primitiveVariables.remove(variable);
                        String newVar = variable.replace("-", "+");
                        primitiveVariables.add(newVar);
                        return;
                    }
                }
            }

            methodVariables += methodName;
            methodList.add(methodVariables + ":" + returnType + ";");
        }

    }

    @Override
    public void enterMethodBody(JavaParser.MethodBodyContext ctx) {
        if (null != ctx.block()) {
            if (null != ctx.block().blockStatement(0)) {
                if (null != ctx.block().blockStatement(0).localVariableDeclaration()) {
                    if (null != ctx.block().blockStatement(0).localVariableDeclaration().typeType()
                            .classOrInterfaceType()) {
                        String s = ctx.block().blockStatement(0).localVariableDeclaration().typeType()
                                .classOrInterfaceType().getText();
                        if (s.contains("String") || s.contains("Double")) {
                            return;
                        }
                        usesList.add(s);
                    }

                }

            }
        }
    }

    private String getParameterTypeAndId(JavaParser.FormalParametersContext ctx, String methodName) {
        String parameterType = "";
        String parameterName = "";
        if (ctx != null && ctx.formalParameterList() != null) {
            parameterType = ctx.formalParameterList().formalParameter(0).typeType().getText();

            parameterName = ctx.formalParameterList().formalParameter(0).variableDeclaratorId().getText();
            if (parameterType.contains("[]")) {
                parameterType = parameterType.replace("[]", "(*)");
            }

            methodName += "(" + parameterName + ":" + parameterType + ")";
        } else {
            methodName += "()";
        }
        return methodName;
    }

    @Override
    public void enterConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx) {
        String parameterType = "";
        String parameterName = "";
        String methodName = ctx.IDENTIFIER().getText();
        methodName = getParameterTypeAndId(ctx.formalParameters(), methodName);

        methodVariables += methodName;
        methodList.add(methodVariables + ";");
    }

    @Override
    public void enterInterfaceMethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx) {

        String returnType = ctx.typeTypeOrVoid().getText();
        String methodName = ctx.IDENTIFIER().getText();
        methodName = getParameterTypeAndId(ctx.formalParameters(), methodName);

        methodVariables += methodName;
        abstractMethodsList.add(methodVariables + ":" + returnType + ";");
    }

    @Override
    public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {

        for (String implementingClass : implementingClasses) {
            String temp = "[<<interface>>;" + implementingClass + "]^-.-[" + className + "]";
            output.add(temp);
        }
        for (String extendingClass : extendingClasses) {
            String temp = "[" + extendingClass + "]^-[" + className + "]";
            output.add(temp);
        }
        for (String useClasses : usesList) {
            String temp = "[" + className + "]uses -.->[" + useClasses + "]";
            output.add(temp);
        }
        String firstString = "[" + className;

        for (int k = 0; k < primitiveVariables.size(); k++) {
            if (k == 0) {
                firstString += "|";
            }
            firstString += primitiveVariables.get(k);
            if (k != primitiveVariables.size() - 1) {
                firstString += ";";
            }
        }
        if (methodList.size() > 0) {
            firstString += "|";
        }
        for (String method : methodList) {
            firstString += method;
        }
        firstString += "]";
        if (classVariablelist.size() == 0) {
            output.add(firstString);
        }
        for (int i = 0; i < classVariablelist.size(); i++) {
            if (i == 0) {
                String temp = classVariablelist.get(0).substring(classVariablelist.get(0).indexOf("[") + 1,
                        classVariablelist.get(0).indexOf("]"));
                // System.out.println("Class Name is: " +className + " classVariable " + temp);

                firstString += classVariablelist.get(0);
                output.add(firstString);

            } else {
                String nextt = "";
                int comp = sortString(className, classVariablelist.get(i));

                // System.out.println("Compare " + comp);
                if (comp > 0) {
                    // System.out.println("If");
                    nextt = "[" + className + "]" + classVariablelist.get(i);
                } else {
                    // System.out.println("Else");
                    nextt = classVariablelist.get(i) + "[" + className + "]";
                }
                output.add(nextt);
            }
        }

    }

    private int sortString(String str1, String str2) {
        return str1.compareTo(str2);
    }

    @Override
    public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        className = ctx.IDENTIFIER().getText();
        usesList = new ArrayList<String>();
        abstractMethodsList = new ArrayList<String>();

    }

    @Override
    public void exitInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        // for (String useClasses : usesList) {
        // System.out.println("[" + className + "]uses -.->[" + useClasses + "]");
        // }
        String temp = new String();
        temp = "[<<Interface>>" + className;
        // System.out.print(temp);
        for (int k = 0; k < abstractMethodsList.size(); k++) {
            if (k == 0) {
                // System.out.print("|");
                temp += "|";
            }
            temp += abstractMethodsList.get(k);
        }
        temp += "]";
        output.add(temp);
        // System.out.print("]");
    }

}