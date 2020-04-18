
import java.util.HashMap;

/**
 * Main App Class for Displaying Screen.
 */
public class App {

    private IScreen s;
    private CenterOrientationDecorator creditorDebitScreen;
    private CenterOrientationDecorator credit;
    private CenterOrientationDecorator debit;
    private CenterOrientationDecorator enterNewZipCode;
    private CenterOrientationDecorator gradeAndGasPump;
    private CenterOrientationDecorator printReceipt;
    private CenterOrientationDecorator thankYou;
    private CenterOrientationDecorator enterPin;

    static HashMap<String, IMenuInvoker> menuMap;

    public App() {
        // list = new ArrayList<String>();
        s = new Screen();
        thankYou = new CenterOrientationDecorator((IScreen) new ThankYou(s));
        printReceipt = new CenterOrientationDecorator((IScreen) new PrintReceipt(s));
        gradeAndGasPump = new CenterOrientationDecorator((IScreen) new GradeAndGasPump(s));
        enterNewZipCode = new CenterOrientationDecorator((IScreen) new EnterZipCode(s));
        enterPin = new CenterOrientationDecorator((IScreen) new EnterYourPin(s));
        credit = new CenterOrientationDecorator((IScreen) new Credit(s));
        debit = new CenterOrientationDecorator((IScreen) new Debit(s));
        creditorDebitScreen = new CenterOrientationDecorator((IScreen) new CreditOrDebit(s));

        // setNext screen for the chain
        printReceipt.setNext((IUserInputHandler) thankYou);
        gradeAndGasPump.setNext((IUserInputHandler) printReceipt);

        enterNewZipCode.setNext((IUserInputHandler) gradeAndGasPump);
        enterPin.setNext((IUserInputHandler) gradeAndGasPump);

        debit.setNext((IUserInputHandler) enterPin);
        credit.setNext((IUserInputHandler) enterNewZipCode);

        ((Screen) s).setCurrentScreen(creditorDebitScreen);

        menuMap = new HashMap<String, IMenuInvoker>();
        // Command Pattern
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
