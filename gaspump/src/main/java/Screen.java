import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Screen implements IScreen, IUserInputHandler {

    private Screen currentScreen;
    List<String> list;

    public Screen() {
        list = new ArrayList<String>();
    }

    public void key(String c) {
        // if (chain != null) {
        // chain.key(c);
        // } else
        currentScreen.key(c);
    }

    public String message() {
        return currentScreen.message();
    }

    public void setCurrentScreen(IScreen screen) {
        currentScreen = (Screen) screen;
    }

    public String display() {
        return currentScreen.display();
    }

    @Override
    public String heading() {
        return currentScreen.heading();
    }

    public String getAdvertisement() {
        list.add("Save With a car wash");
        list.add("Star Wars Movie Preview");
        list.add("New IPhone 13 at AT&T");
        list.add("Join our Rewards Program");
        list.add("Macy's Summer Clearance");
        return getRandomElement(list);
        // return null;
    }

    private String getRandomElement(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    @Override
    public void setNext(IUserInputHandler userInput) {
        currentScreen.setNext(userInput);

    }

}
