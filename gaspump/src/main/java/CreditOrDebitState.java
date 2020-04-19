
public class CreditOrDebitState implements IState {
    IScreen state;

    public CreditOrDebitState(IScreen screen) {
        state = screen;
    }

    public String message() {
        return "Enter Credit Or Debit Card";
    }

    public void key(String ch) {

    }

    public String A() {
        return "";
    }

    public String B() {
        return "";
    }

    public String C() {
        return "Credit";
    };

    public String D() {
        return "Debit";
    };

    public String E() {
        return "";
    };

    public String F() {
        return "";
    };

    public String G() {
        return "";
    };

    public String H() {
        return "";
    };

    public void doActionA() {
    }

    public void doActionB() {
    }

    public void doActionC() {
        state.setState(1);
    }

    public void doActionD() {
        state.setState(2);
    }

    public void doActionE() {
    }

    public void doActionF() {
    }

    public void doActionG() {
    }

    public void doActionH() {
    }

}