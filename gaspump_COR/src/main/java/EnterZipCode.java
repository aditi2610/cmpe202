
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
            appScreen.setCurrentScreen((IScreen) chain);
        } else {
            chain.key(c);
        }
    }

    @Override
    public void setNext(IUserInputHandler userInput) {
        this.chain = userInput;

    }

    public String display() {
        String output = "";
        output += "\n";
        output += "========================================\n";
        output += message();
        output += "\n";
        output += "\n";
        output += "\n";
        output += "\n";
        output += "[A]                                  [E]\n";
        output += "\n";
        output += "[B]                                  [F]\n";
        output += "\n";
        output += "[C]                                  [G]\n";
        output += "\n";
        output += "[D]                                  [H]\n";
        output += "\n";
        output += super.getAdvertisement();
        output += "\n";
        output += "========================================\n";
        return output;
    }
}