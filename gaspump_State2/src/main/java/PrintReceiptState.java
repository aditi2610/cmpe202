
public class PrintReceiptState extends State {
    IScreen myscreen;

    public String message() {
        return "Print Receipt? ";
    }

    public PrintReceiptState(IScreen screen) {
        myscreen = screen;
    }

    public void key(String keypad) {
        myscreen.setState(7);
    }

    public void doStateC() {
        // menuCommand1.execute();
        myscreen.setState(7);

    }

    public void doStateD() {
        myscreen.setState(7);

    }

    public void doStateE() {
        // menuCommand1.execute();
        myscreen.setState(7);

    }

    public void doStateF() {
        myscreen.setState(7);
        // menuCommand2.execute();

    }

    @Override
    public String C() {
        return "Yes";
    }

    @Override
    public String D() {
        return "No";
    }

    @Override
    public String G() {
        return "Help";
    }

    @Override
    public String H() {
        return "Done";
    }

}