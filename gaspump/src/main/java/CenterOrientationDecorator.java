import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Decorator pattern used for all the screens.
 */
public class CenterOrientationDecorator implements IUserInputHandler {
    IScreen decScreen;
    List<String> list = new ArrayList<String>();

    CenterOrientationDecorator(IScreen screen) {
        decScreen = screen;
    }

    public void key(String c) {
        decScreen.key(c);
    }

    public String message() {
        return decScreen.message();
    }

    /**
     * Sets the current Screen
     * 
     * @param screen
     */
    public void setCurrentScreen(CenterOrientationDecorator screen) {
        decScreen = (IScreen) screen;
    }

    public String display() {
        return centeredDisplay(decScreen.display());
    }

    public String heading() {
        return decScreen.heading();
    }

    public void setNext(IUserInputHandler userInput) {
        decScreen.setNext(userInput);

    }

    /**
     * 
     * @param content Screen content received from the individual Screens
     * @return String formatted and padded.
     */
    private String centeredDisplay(String content) {
        String output = "";
        output += "\n";
        output += "========================================\n";
        output += "\n";
        output += calculateHeight(decScreen.message());
        output += "\n";
        output += "\n";
        output += "[A]                                  [E]\n";
        output += "\n";
        output += "[B]                                  [F]\n";
        output += "\n";
        output += setCenterContent(content);
        output += "\n";
        output += "\n";
        output += padSpaces(getAdvertisement());
        output += "\n";
        output += "\n";
        output += "========================================\n";
        return output;
        // return content;
    }

    private String calculateHeight(String content) {
        String value = "";
        String[] heading = content.split("\n");

        for (String head : heading) {
            value += padSpaces(head);
            value += "\n";
        }
        // String value = padSpaces(content);
        // if (!content.contains("/n")) {
        // value += "\n";
        // } else {
        // System.out.println("I contain 2 lines" + content);
        // }
        return value;
    }

    private String padSpaces(String content) {
        String value = "";
        int width = content.length();
        int spaces = (40 - width) / 2;
        for (int i = 0; i < spaces; i++) {
            value += " ";
        }
        value += content;
        return value;
    }

    private String setCenterContent(String content) {
        String output = "";
        String[] rows = content.split("\n");
        output += rows.length > 0 ? formRow("[C]", "[G]", rows[0]) : "[C]                                  [G]";
        output += "\n";
        output += "\n";
        output += rows.length > 1 ? formRow("[D]", "[H]", rows[1]) : "[D]                                  [H]";
        output += "\n";
        return output;
    }

    private String formRow(String a1, String a2, String row) {
        String rowContent = a1;
        String[] words = row.split("\\s+");
        String firstWord = "";
        String secondWord = "";
        if (words.length > 1) {
            firstWord = words[0];
            secondWord = words[1];
            System.out.println(firstWord + "  " + secondWord);
        } else if (words.length > 0) {
            firstWord = words[0];
        }
        rowContent += " " + firstWord;
        int length = rowContent.length() + secondWord.length() + 4;

        for (int i = 0; i < 40 - length; i++) {
            rowContent += " ";
        }
        rowContent += secondWord;
        rowContent += " ";
        rowContent += a2;

        return rowContent;
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

}