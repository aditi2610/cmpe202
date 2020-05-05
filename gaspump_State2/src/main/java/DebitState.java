
public class DebitState extends State {
    IScreen myscreen;

    public String message() {
        return "Enter Debit Card";
    }

    public DebitState(IScreen screen) {
        myscreen = screen;
    }

    @Override
    public void key(String keypad) {
        myscreen.setState(4);

    }
}