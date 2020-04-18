
public interface IScreen {
    String heading();

    void key(String keypad); // send key entry to screen

    String message(); // get screen message

    String display();

    void setNext(IUserInputHandler userInput);

    void setCurrentScreen(CenterOrientationDecorator s);

}
