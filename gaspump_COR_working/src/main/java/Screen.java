
public class Screen implements IScreen, IUserInputHandler {

    private CenterOrientationDecorator currentScreen;

    public Screen() {
        // list = new ArrayList<String>();
    }

    public void key(String c) {
        currentScreen.key(c);
    }

    public String message() {
        return currentScreen.message();
    }

    public void setCurrentScreen(CenterOrientationDecorator screen) {
        currentScreen = screen;
    }

    public String display() {
        return currentScreen.display();
    }

    @Override
    public String heading() {
        return currentScreen.heading();
    }

    @Override
    public void setNext(IUserInputHandler userInput) {
        currentScreen.setNext(userInput);

    }

}
