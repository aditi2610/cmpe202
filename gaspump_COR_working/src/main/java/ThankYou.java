
public class ThankYou extends Screen {

    public ThankYou(IScreen s) {
        super();

    }

    public String message() {
        String name = "Thank for Your Business\n";
        name += "Please visit us again";
        return name;
    }

    // what to do next when someone touches something on this screen
    public void key(String c) {
        // System.out.println("inside key");
    }

    @Override
    public void setNext(IUserInputHandler userInput) {
        // this is the last one.. so do nothing.
    }

    public String display() {
        String output = "";
        return output;
    }

}