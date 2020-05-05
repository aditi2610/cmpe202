
public class CreditOrDebitState extends State {
    IScreen myscreen;
    private IMenuCommand menuCommand1;
    private IMenuCommand menuCommand2;

    public String message() {
        return "Enter Credit or Debit Card";
    }

    public CreditOrDebitState(IScreen screen) {
        myscreen = screen;
        // menuCommand1 = new IMenuCommand() {

        // @Override
        // public void execute() {
        // myscreen.setState(1);
        // }
        // };

        // menuCommand2 = new IMenuCommand() {

        // @Override
        // public void execute() {
        // myscreen.setState(2);
        // }
        // };
    }

    public void doStateC() {
        // menuCommand1.execute();
        myscreen.setState(1);

    }

    public void doStateD() {
        myscreen.setState(2);
        // menuCommand2.execute();

    }

    @Override
    public String C() {
        return "Credit";
    }

    @Override
    public String D() {

        return "Debit";
    }

}