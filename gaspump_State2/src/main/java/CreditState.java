
public class CreditState extends State {
    IScreen myscreen;

    public String message() {
        return "Enter Credit Card";
    }

    public CreditState(IScreen screen) {
        myscreen = screen;
    }

    @Override
    public void key(String keypad) {
        myscreen.setState(3);

    }
}