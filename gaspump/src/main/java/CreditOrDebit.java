
public class CreditOrDebit extends Screen {
    // IMenuCommand displayCredit;
    App app;
    // private IScreen appScreen;

    public CreditOrDebit(IScreen s) {

        super();
        // appScreen = s;

    }

    public String message() {
        String name = "Credit or Debit?";
        return name;
    }

    // what to do next when someone touches something on this screen
    public void key(String c) {
        System.out.println("inside key");
        MenuItem m;
        if (c.equalsIgnoreCase("C")) {
            m = (MenuItem) App.getMenuMap().get("credit");
            m.invoke();
        } else if (c.equalsIgnoreCase("D")) {
            m = (MenuItem) App.getMenuMap().get("debit");
            m.invoke();
        }

    }

    public String display() {
        String output = "";
        output += "Credit\n";
        output += "Debit\n";
        return output;
    }

    public void setNext(IUserInputHandler userInput) {

    }

}