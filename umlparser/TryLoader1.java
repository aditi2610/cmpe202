import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.HashSet;

public class TryLoader1 extends JavaParserBaseListener {

    private class StackElem {
        String className = "";
        List<String> classVariablelist;
        // static String primitiveString;
        int flagForCollection = 0;
        String element = "";
        String classVariables = "";
        String methodVariables = "";
        List<String> extendingClasses;
        List<String> implementingClasses;
        List<String> usesList;
        List<String> memberVariables;
        List<String> abstractMethodsList;
        List<String> primitiveVariables;
        boolean countVariable = false;
        boolean countMethod = false;
        List<String> methodList;
        // Set<String> output = new HashSet<String>();
    }

    static Stack<StackElem> inProgress = new Stack<>();
    static List<Set<String>> completed = new ArrayList<>();

    String className = "";
    List<String> classVariablelist;
    // static String primitiveString;
    int flagForCollection = 0;
    String element = "";
    String classVariables = "";
    String methodVariables = "";
    List<String> extendingClasses;
    List<String> implementingClasses;
    List<String> usesList;
    List<String> memberVariables;
    List<String> abstractMethodsList;
    List<String> primitiveVariables;
    boolean countVariable = false;
    boolean countMethod = false;
    List<String> methodList;
    Set<String> output = new HashSet<String>();

    private void clearContext() {
        this.className = "";
        this.classVariablelist = new ArrayList<String>();
        this.flagForCollection = 0;
        this.element = "";
        this.classVariables = "";
        this.methodVariables = "";
        this.extendingClasses = new ArrayList<String>();
        this.implementingClasses = new ArrayList<String>();
        this.usesList = new ArrayList<String>();
        this.memberVariables = new ArrayList<String>();
        this.abstractMethodsList = new ArrayList<String>();
        this.primitiveVariables = new ArrayList<String>();
        this.countVariable = false;
        this.countMethod = false;
        this.methodList = new ArrayList<String>();
        // this.output = new HashSet<String>();
    }

    private StackElem returnContext() {
        StackElem elem = new StackElem();
        elem.className = this.className;
        elem.classVariablelist = this.classVariablelist;
        elem.flagForCollection = this.flagForCollection;
        elem.element = this.element;
        elem.classVariables = this.classVariables;
        elem.methodVariables = this.methodVariables;
        elem.extendingClasses = this.extendingClasses;
        elem.implementingClasses = this.implementingClasses;
        elem.usesList = this.usesList;
        elem.memberVariables = this.memberVariables;
        elem.abstractMethodsList = this.abstractMethodsList;
        elem.primitiveVariables = this.primitiveVariables;
        elem.countVariable = this.countVariable;
        elem.countMethod = this.countMethod;
        elem.methodList = this.methodList;
        // elem.output = this.output;
        return elem;
    }

    private void saveContext() {
        StackElem elem = this.returnContext();
        inProgress.push(elem);
    }

    private void loadLastContext() {
        this.clearContext();
        StackElem elem = inProgress.pop();
        this.className = elem.className;
        this.classVariablelist = elem.classVariablelist;
        this.flagForCollection = elem.flagForCollection;
        this.element = elem.element;
        this.classVariables = elem.classVariables;
        this.methodVariables = elem.methodVariables;
        this.extendingClasses = elem.extendingClasses;
        this.implementingClasses = elem.implementingClasses;
        this.usesList = elem.usesList;
        this.memberVariables = elem.memberVariables;
        this.abstractMethodsList = elem.abstractMethodsList;
        this.primitiveVariables = elem.primitiveVariables;
        this.countVariable = elem.countVariable;
        this.countMethod = elem.countMethod;
        this.methodList = elem.methodList;
        // this.output = elem.output;
    }

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        // Get class Name by first splitting on implements and then on extends
       // System.out.printf("NEW CLASS -> old: %s, new: %s\n", this.className, ctx.IDENTIFIER().getText());

        if (this.className.length() != 0) {
            this.saveContext();
            this.clearContext();
        }

        // prepare for the new class

        className = ctx.IDENTIFIER().getText();
        // // System.out.println("final: " + classn);
        extendingClasses = new ArrayList<String>();
        implementingClasses = new ArrayList<String>();
        primitiveVariables = new ArrayList<String>();
        methodList = new ArrayList<String>();
        usesList = new ArrayList<String>();
        classVariablelist = new ArrayList<String>();
        if (ctx.getText().contains("extends")) {
            extendingClasses.add(ctx.typeType().classOrInterfaceType().getText());
        }
        if (ctx.getText().contains("implements")) {
            JavaParser.TypeListContext typeList = ctx.typeList();
            for (JavaParser.TypeTypeContext typeType : typeList.typeType()) {
                implementingClasses.add(typeType.classOrInterfaceType().getText());
            }

        }
       
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

        //System.out.println("enterClassOrInterfaceModifier "+ ctx.getText());
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
       //System.out.println("enterFieldDeclaration countVAribale is: "+ countVariable);
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
                     //System.out.println(" $$$$$$$$$$$ ClassVaribales: "+ classVariables + " classORiNterface "+ classOrInterface.getText());
                    classVariables += "-[" + classOrInterface.getText() + "]";

                    //System.out.println("ClassVaribale getting added into list : "+ classVariables);
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
                    if (methodName.substring(3, methodName.indexOf("(")).equalsIgnoreCase(variableName)) {
                        primitiveVariables.remove(variable);
                        String newVar = variable.replace("-", "+");
                        primitiveVariables.add(newVar);
                        return;
                    }
                }
            }
            // if(methodVariables != "-" || methodVariables != "+"){
            //    return;
            // }
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

        //System.out.println("Method Name: " + methodName + " methodvariable: " + methodVariables);
        if (methodVariables.contains("-") ||  methodVariables.contains("+")) {
            methodVariables += methodName;
            abstractMethodsList.add(methodVariables + ":" + returnType + ";");
        }else{
            return;
        }
      
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

        //System.out.println(" classVariablelist.size" +classVariablelist.size());
        if (classVariablelist.size() == 0) {
            output.add(firstString);
        }
        for (int i = 0; i < classVariablelist.size(); i++) {
            if (i == 0) {
                String temp = classVariablelist.get(0).substring(classVariablelist.get(0).indexOf("[") + 1,
                        classVariablelist.get(0).indexOf("]"));
                //System.out.println("Class Name is: " +className + " classVariable " + temp);

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

        // completed.add(output);

        if (inProgress.isEmpty()) {
            for (String s : output) {
                // System.out.println(s);
            }
        }
        else {
            this.loadLastContext();
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