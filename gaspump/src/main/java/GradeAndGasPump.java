
public class GradeAndGasPump extends Screen {
    private IUserInputHandler chain;
    private IScreen appScreen;

    public GradeAndGasPump(IScreen s) {
        super();
        appScreen = s;

    }

    public String message() {
        String name = "Select Grade and Pump Gas";
        return name;
    }

    // what to do next when someone touches something on this screen
    public void key(String c) {
        System.out.println("Inside grade and gas pump" + c);
        appScreen.setCurrentScreen((CenterOrientationDecorator) chain);
    }

    @Override
    public void setNext(IUserInputHandler userInput) {
        this.chain = userInput;

    }

    public String display() {
        return "";
    }

}
