
public class ZipCodeState extends State {
    IScreen myscreen;

    public String message() {
        return "Enter Zip Code";
    }

    public ZipCodeState(IScreen screen) {
        myscreen = screen;
    }

    public void key(String keypad) {
        myscreen.setState(5);

    }

}