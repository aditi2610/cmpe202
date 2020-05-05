
import java.util.*;

public class Screen implements IScreen {
    State currentState;
    State creditState;
    State debitState;
    State zipCodeState;
    State passCodeState;
    State gradeAndGasPumpState;
    State printReceiptState;
    State thankYouState;
    IMenuCommand menuCommandC;
    IMenuCommand menuCommandD;
    IMenuCommand menuCommandG;
    IMenuCommand menuCommandH;

    public Screen() {
        currentState = new CreditOrDebitState(this);
        creditState = new CreditState(this);
        debitState = new DebitState(this);
        zipCodeState = new ZipCodeState(this);
        passCodeState = new PassCodeState(this);
        gradeAndGasPumpState = new GradeAndGasPumpState(this);
        printReceiptState = new PrintReceiptState(this);
        thankYouState = new ThankYouState(this);

        menuCommandC = new IMenuCommand() {
            @Override
            public void execute() {
                currentState.doStateC();
            }
        };
        menuCommandD = new IMenuCommand() {
            @Override
            public void execute() {
                currentState.doStateD();

            }
        };
        menuCommandG = new IMenuCommand() {
            @Override
            public void execute() {
                currentState.doStateG();
            }
        };
        menuCommandH = new IMenuCommand() {
            @Override
            public void execute() {
                currentState.doStateH();

            }
        };
    }

    public void key(String keypad) {
        switch (keypad) {
            case "C":
                menuCommandC.execute();
                // currentState.doStateC();
                break;
            case "D":
                menuCommandD.execute();
                // currentState.doStateD();
                break;
            case "G":
                menuCommandG.execute();
                // currentState.doStateG();
                break;
            case "H":
                menuCommandH.execute();
                // currentState.doStateH();
                break;
            default:
                currentState.key(keypad);
                break;
        }
    }

    public String message() {
        return currentState.message();
    }

    public void setState(int state) {
        switch (state) {
            case 1:
                // menuCommandC.execute();
                currentState = creditState;
                break;
            case 2:
                currentState = debitState;
                break;
            case 3:
                currentState = zipCodeState;
                break;
            case 4:
                currentState = passCodeState;
                break;
            case 5:
                currentState = gradeAndGasPumpState;
                break;
            case 6:
                currentState = printReceiptState;
                break;
            case 7:
                currentState = thankYouState;
                break;

        }
    }

    @Override
    public String getAdvertisement() {
        return "";
    }

    @Override
    public String A() {
        // TODO Auto-generated method stub

        return currentState.A();
    }

    @Override
    public String B() {
        // TODO Auto-generated method stub
        return currentState.B();
    }

    @Override
    public String C() {
        return currentState.C();

    }

    @Override
    public String D() {
        // TODO Auto-generated method stub
        return currentState.D();
    }

    @Override
    public String E() {
        // TODO Auto-generated method stub
        return currentState.E();
    }

    @Override
    public String F() {
        // TODO Auto-generated method stub
        return currentState.F();
    }

    @Override
    public String G() {
        // TODO Auto-generated method stub
        return currentState.G();
    }

    @Override
    public String H() {
        // TODO Auto-generated method stub
        return currentState.H();
    }

}
