
public class Credit extends Screen {
    private IUserInputHandler chain;
    // private EnterZipCode zipCode;
    private IScreen appScreen;

    public Credit(IScreen s) {
        super();
        appScreen = s;

    }

    public String message() {
        String name = "Scan Credit Card";
        return name;
    }

    // what to do next when someone touches something on this screen
    public void key(String c) {
        if (c.length() >= 10) {
            appScreen.setCurrentScreen((CenterOrientationDecorator) chain);
        } else {
            chain.key(c);
        }
    }

    @Override
    public void setNext(IUserInputHandler userInput) {
        this.chain = userInput;

    }

    public String display() {
        return "";
    }

}