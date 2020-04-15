
import java.util.HashMap;

/**
 * Main App Class for Displaying Screen.
 */
public class App {

    private IScreen s;
    private IScreen creditorDebitScreen;
    private IScreen credit;
    private IScreen debit;
    private IScreen enterNewZipCode;

    static HashMap<String, IMenuInvoker> menuMap;

    public App() {
        // list = new ArrayList<String>();
        s = new Screen();
        enterNewZipCode = new EnterZipCode(s);
        credit = new Credit(s);
        (credit).setNext((IUserInputHandler) enterNewZipCode);

        debit = new Debit();

        creditorDebitScreen = (IScreen) new CreditOrDebit(s);

        ((Screen) s).setCurrentScreen(creditorDebitScreen);

        menuMap = new HashMap<String, IMenuInvoker>();

        // set Receivers and invokers;
        IMenuInvoker creditMenuItem = new MenuItem();
        IMenuInvoker debitMenuItem = new MenuItem();

        IMenuCommand displayCredit = new MenuCommand();
        IMenuCommand displayDebit = new MenuCommand();

        displayCredit.setReceiver(new IMenuReceiver() {

            @Override
            public void doAction() {
                // System.out.println("Do Action of DisplayCredit");
                ((Screen) s).setCurrentScreen(credit);
            }
        });

        displayDebit.setReceiver(new IMenuReceiver() {

            @Override
            public void doAction() {
                ((Screen) s).setCurrentScreen(debit);

            }
        });

        // SetCommands for each Menu Item
        creditMenuItem.setCommand(displayCredit);
        debitMenuItem.setCommand(displayDebit);

        // create a Map to be accessed by other classes.
        menuMap.put("credit", creditMenuItem);
        menuMap.put("debit", debitMenuItem);

    }

    public String display() {
        return s.display();
    }

    public void key(String keypad) {
        s.key(keypad);
    }

    static HashMap<String, IMenuInvoker> getMenuMap() {
        return menuMap;
    }

}
