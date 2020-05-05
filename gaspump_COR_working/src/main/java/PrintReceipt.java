
public class PrintReceipt extends Screen {
    private IUserInputHandler chain;
    private IScreen appScreen;

    public PrintReceipt(IScreen s) {
        super();
        appScreen = s;

    }

    public String message() {
        String name = "Print Receipt ?";
        return name;
    }

    // what to do next when someone touches something on this screen
    public void key(String c) {
        // System.out.println("inside key");
        // if (c.contains("\n")) {
        appScreen.setCurrentScreen((CenterOrientationDecorator) chain);
        // } else {
        // chain.key(c);
        // }
    }

    @Override
    public void setNext(IUserInputHandler userInput) {
        this.chain = userInput;

    }

    public String display() {
        String output = "";

        output += "Yes Help\n";
        output += "No Done\n";
        return output;
    }

}
