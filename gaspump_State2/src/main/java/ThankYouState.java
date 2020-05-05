
public class ThankYouState extends State {
    IScreen myscreen;

    public String message() {
        return "Thank you for your Business \n  Please visit us again";
    }

    public ThankYouState(IScreen screen) {
        myscreen = screen;
    }

}