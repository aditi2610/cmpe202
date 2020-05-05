public class Credit implements IState {
    IScreen state;

    public Credit(IScreen screen) {
        state = screen;
    }

    public String message() {
        return "Enter Credit Card Number";
    }

    public void key(String ch) {
        state.setState(3);
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