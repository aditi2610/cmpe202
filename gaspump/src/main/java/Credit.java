
public class Credit extends Screen {
    private IUserInputHandler chain;
    private EnterZipCode zipCode;
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
        if (c.length() == 10) {
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