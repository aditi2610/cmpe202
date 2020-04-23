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
    Set<String> output  = new HashSet<String>();
    

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        // Get class Name by first splitting on implements and then on extends
        className = "";
        // System.out.println("Implements: " + ctx.getText().split("implements")[0]);
        // System.out.println("Extends: " + ctx.getText().split("extends")[0]);
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
        // primitiveString = "";

    }

    // @Override
    // public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext
    // ctx) {
    // abstractMethodsList = new ArrayList<String>();

    // }

    @Override
    public void enterClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
        element = "";
        classVariables = "";
        countVariable = false;
        countMethod = false;
        methodVariables = "";

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
                //System.out.println("Identifier is : " +classOrInterface.getText());
                if (classOrInterface.getText().contains("Collection") || classOrInterface.getText()
                        .contains("ArrayList")) {
                    classVariables += "1-0..*";
                    classVariables += "["
                            + classOrInterface.typeArguments().get(0).typeArgument().get(0).typeType().getText() + "]";
                    //System.out.println("nhgfdf "+ classVariables);
                            classVariablelist.add(classVariables);

                } else if (classOrInterface.getText().contains("String")) {
                    element += ctx.variableDeclarators().variableDeclarator(0).variableDeclaratorId().getText();
                    element += ":" + ctx.typeType().classOrInterfaceType().getText();

                    // i might have to check array condition here
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
                // System.out.println("Primitive type is: " + element);
                primitiveVariables.add(element);
            }
        }

    }

    @Override
    public void enterFormalParameterList(JavaParser.FormalParameterListContext ctx) {
        // this condition has to be matched for int as well
        if(null != ctx.formalParameter(0).typeType().classOrInterfaceType()){
            if (!ctx.formalParameter(0).typeType().classOrInterfaceType().getText().equals("String")) {
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
            // System.out.println("ctx method: " + ctx.getText());
            String returnType = ctx.typeTypeOrVoid().getText();
            // System.out.println("return Type: " + returnType);

            String methodName = ctx.IDENTIFIER().getText();
            methodName = getParameterTypeAndId(ctx.formalParameters(), methodName);
            methodVariables += methodName;
            methodList.add(methodVariables + ":" + returnType + ";");
        }

    }
    

    @Override
    public void enterMethodBody(JavaParser.MethodBodyContext ctx) {
        if(null != ctx.block()){
            if(null !=  ctx.block().blockStatement(0)){
                if(null != ctx.block().blockStatement(0).localVariableDeclaration()){
                    if(null != ctx.block().blockStatement(0).localVariableDeclaration().typeType().classOrInterfaceType()){
                        String s = ctx.block().blockStatement(0).localVariableDeclaration().typeType()
                                .classOrInterfaceType().getText();
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
            if(parameterType.contains("[]")){
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
        // String methodName = ctx.IDENTIFIER().getText() + "" +
        // ctx.formalParameters().getText();
        String methodName = ctx.IDENTIFIER().getText();
        // System.out.println("Method Name in Consturctor: " + methodName);
        methodName = getParameterTypeAndId(ctx.formalParameters(), methodName);

        methodVariables += methodName;
        // System.out.println("Constructor is: " + methodName);
        methodList.add(methodVariables + ";");
    }

    @Override
    public void enterInterfaceMethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx) {

        //System.out.println("Inside Interface Method declaration "+ ctx.IDENTIFIER().getText());
        String returnType = ctx.typeTypeOrVoid().getText();
        // System.out.println("Return type: " + returnType);
        String methodName = ctx.IDENTIFIER().getText() ;
        // System.out.println("Interface method: " +
        // getParameterTypeAndId(ctx.formalParameters(), methodName));
        methodName = getParameterTypeAndId(ctx.formalParameters(), methodName);
        //System.out.println("Method nmae: "+ methodName);

        methodVariables += methodName;
        // System.out.println("Interface : " + methodVariables + ":" + returnType +
        // ";");
        abstractMethodsList.add(methodVariables + ":" + returnType + ";");
        // System.out.println("@@@@@");
        // for(String s: abstractMethodsList){
        //     System.out.println(" " +s);
        // }

    }

    @Override
    public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        
        for (String implementingClass : implementingClasses) {
            String temp = "[<<interface>>;" + implementingClass + "]^-.-[" + className + "]";
            output.add(temp);
            //System.out.println(temp);
        }
        for (String extendingClass : extendingClasses) {
            String temp = "[" + extendingClass + "]^-[" + className + "]";
            //System.out.println(temp);
            output.add(temp);
        }
        for (String useClasses : usesList) {
            String temp ="[" + className + "]uses -.->[" + useClasses + "]";
            //System.out.println(temp);
            output.add(temp);
        }
        // System.out.println("primitives:" + primitiveVariables.size());
        String firstString ="[" + className;
        //System.out.print(firstString);
        // for (String primitive : primitiveVariables) {
        // System.out.print(primitive);
        // }

        for (int k = 0; k < primitiveVariables.size(); k++) {
            if (k == 0) {
                firstString +="|"; 
                //System.out.print(firstString);
            }
            firstString += primitiveVariables.get(k);
            //System.out.print(firstString);
            if (k != primitiveVariables.size() - 1) {
                firstString += ";";
                //System.out.print(firstString);
            }
        }
        if (methodList.size() > 0) {
            firstString += "|";
            //System.out.print(firstString);
        }
        for (String method : methodList) {
            firstString += method;
            //System.out.print(firstString);
        }
        firstString += "]";
        if(classVariablelist.size() == 0){
            output.add(firstString);
        }
        //System.out.print(firstString);
        for (int i = 0; i < classVariablelist.size(); i++) {
            if (i == 0) {
                firstString += classVariablelist.get(0);
                //System.out.println(classVariablelist.get(0));
                output.add(firstString);

            } else {
                String nextt = "[" + className + "]" + classVariablelist.get(i);
                //System.out.println(nextt);
                output.add(nextt);
            }
        }

    }

    @Override
    public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        // System.out.println("Enter Interface: " + ctx.IDENTIFIER().getText());
        className = ctx.IDENTIFIER().getText();
        usesList = new ArrayList<String>();
        abstractMethodsList = new ArrayList<String>();

    }

    @Override
    public void exitInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        // for (String useClasses : usesList) {
        //     System.out.println("[" + className + "]uses -.->[" + useClasses + "]");
        // }
        String temp = new String();
        temp = "[<<Interface>>" + className;
        //System.out.print(temp);
        for (int k = 0; k < abstractMethodsList.size(); k++) {
            if (k == 0) {
                //System.out.print("|");
                temp += "|";
            }
            temp += abstractMethodsList.get(k);
            //System.out.print(abstractMethodsList.get(k));
            // if (k != abstractMethodsList.size() - 1) {
            //     System.out.print(";");
            // }
        }
        temp += "]";
        output.add(temp);
        //System.out.print("]");
    }

}