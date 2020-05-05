
public class ZipCodeScreen implements IState {
    IScreen state;

    public ZipCodeScreen(IScreen screen) {
        state = screen;
    }

    public String message() {
        return "Enter ZipCode";
    }

    public void key(String ch) {
        state.setState(5);

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