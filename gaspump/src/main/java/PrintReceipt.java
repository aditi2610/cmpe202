
public class PrintReceipt implements IState {
    IScreen state;

    public PrintReceipt(IScreen screen) {
        state = screen;
    }

    public String message() {
        return "Print Receipt ?";
    }

    public void key(String ch) {
        state.setState(7);
    }

    public String A() {
        return "";
    }

    public String B() {
        return "";
    }

    public String C() {
        return "Yes";
    };

    public String D() {
        return "No";
    };

    public String E() {
        return "";
    };

    public String F() {
        return "";
    };

    public String G() {
        return "Help";
    };

    public String H() {
        return "Done";
    };

    public void doActionA() {
    }

    public void doActionB() {
    }

    public void doActionC() {
        state.setState(7);
    }

    public void doActionD() {
        state.setState(7);
    }

    public void doActionE() {
    }

    public void doActionF() {
    }

    public void doActionG() {
        state.setState(7);
    }

    public void doActionH() {
        state.setState(7);
    }

}