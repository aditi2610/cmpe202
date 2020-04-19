
import java.util.*;

public class Screen implements IScreen {

    private IState creditOrDebitScreen;
    private IState creditScreen;
    private IState debitScreen;
    private IState zipCodeScreen;
    private IState pinScreen;
    private IState printReceiptScreen;
    private IState gasPumpScreen;
    private IState current;
    private IState thankyouScreen;

    public Screen() {

        // setAdvertisement();
        creditOrDebitScreen = (IState) new CreditOrDebitState(this);

        creditScreen = new Credit(this);
        debitScreen = new DebitState(this);
        zipCodeScreen = new ZipCodeScreen(this);
        pinScreen = new PinCodeState(this);
        gasPumpScreen = new GradeAndGasPump(this);
        printReceiptScreen = new PrintReceipt(this);
        thankyouScreen = new ThankYouState(this);
        current = creditOrDebitScreen;

    }

    public void key(String keypad) {
        switch (keypad) {
            case "A":
                selectA();
                break;
            case "B":
                selectB();
                break;
            case "C":
                selectC();
                break;
            case "D":
                selectD();
                break;
            case "E":
                selectE();
                break;
            case "F":
                selectF();
                break;
            case "G":
                selectG();
                break;
            default:
                current.key(keypad);
                break;

        }
    }

    public String message() {
        return current.message();
    }

    public void setState(int state) {
        switch (state) {
            case 0:
                current = creditOrDebitScreen;
                break;
            case 1:
                current = creditScreen;
                break;
            case 2:
                current = debitScreen;
                break;
            case 3:
                current = zipCodeScreen;
                break;
            case 4:
                current = pinScreen;
                break;
            case 5:
                current = gasPumpScreen;
                break;
            case 6:
                current = printReceiptScreen;
                break;
            case 7:
                current = thankyouScreen;
                break;
        }

    }

    private IMenuCommand menuA = new IMenuCommand() {
        @Override
        public void execute() {
            ((IState) current).doActionA();

        }
    };

    private IMenuCommand menuB = new IMenuCommand() {
        @Override
        public void execute() {
            ((IState) current).doActionB();

        }
    };

    private IMenuCommand menuC = new IMenuCommand() {
        @Override
        public void execute() {
            ((IState) current).doActionC();

        }
    };

    private IMenuCommand menuD = new IMenuCommand() {
        @Override
        public void execute() {
            ((IState) current).doActionD();

        }
    };
    private IMenuCommand menuE = new IMenuCommand() {
        @Override
        public void execute() {
            ((IState) current).doActionE();

        }
    };

    private IMenuCommand menuF = new IMenuCommand() {
        @Override
        public void execute() {
            ((IState) current).doActionF();

        }
    };
    private IMenuCommand menuG = new IMenuCommand() {
        @Override
        public void execute() {
            ((IState) current).doActionG();

        }
    };
    private IMenuCommand menuH = new IMenuCommand() {
        @Override
        public void execute() {
            ((IState) current).doActionH();

        }
    };

    public String A() {
        return ((IState) current).A();
    }

    public String B() {
        return ((IState) current).B();
    }

    public String C() {
        return ((IState) current).C();
    };

    public String D() {
        return ((IState) current).D();
    };

    public String E() {
        return ((IState) current).E();
    };

    public String F() {
        return ((IState) current).F();
    };

    public String G() {
        return ((IState) current).G();
    };

    public String H() {
        return ((IState) current).H();
    };

    private void selectA() {
        menuA.execute();
    }

    private void selectB() {
        menuB.execute();
    }

    private void selectC() {
        menuC.execute();
    }

    private void selectD() {
        menuD.execute();
    }

    private void selectE() {
        menuE.execute();
    }

    private void selectF() {
        menuF.execute();
    }

    public void selectG() {
        menuG.execute();
    }

    public void selectH() {
        menuH.execute();
    }

    public String getAdvertisement() {

        return "";
    }

    // public String getAdvertisement() {
    // return current.getAdvertisement();
    // }

}
