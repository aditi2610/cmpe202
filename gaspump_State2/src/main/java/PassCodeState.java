
public class PassCodeState extends State {
    IScreen myscreen;

    public String message() {
        return "Enter PassCode";
    }

    public PassCodeState(IScreen screen) {
        myscreen = screen;
    }

    public void key(String keypad) {
        myscreen.setState(5);

    }

}