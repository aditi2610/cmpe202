
public class ThankYouState implements IState {
    IScreen state;

    public ThankYouState(IScreen screen) {
        state = screen;
    }

    public String message() {
        return "Thank You for Your Business \n Please Visit us again";
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
        return "";
    };

    public String D() {
        return "";
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
    }

    public void doActionD() {
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
