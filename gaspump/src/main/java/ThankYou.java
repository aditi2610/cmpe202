
public class ThankYou extends Screen {

    public ThankYou(IScreen s) {
        super();

    }

    public String message() {
        String name = "Thank You ";
        return name;
    }

    // what to do next when someone touches something on this screen
    public void key(String c) {
        System.out.println("inside key");
    }

    @Override
    public void setNext(IUserInputHandler userInput) {
        // this is the last one.. so do nothing.
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