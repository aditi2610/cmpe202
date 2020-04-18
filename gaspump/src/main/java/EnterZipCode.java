
public class EnterZipCode extends Screen {

    private IScreen appScreen;
    private IUserInputHandler chain;

    EnterZipCode(IScreen s) {
        super();
        appScreen = s;
    }

    public String message() {
        String name = "Enter your Zip code";
        return name;
    }

    // what to do next when someone touches something on this screen
    public void key(String c) {
        if (c.length() == 5) {
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