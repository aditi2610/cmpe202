
public class PrintReceipt extends Screen {

    public String message() {
        String name = "Print Receipt?";
        return name;
    }

    // what to do next when someone touches something on this screen
    public void key(String c) {

    }

    public String getC() {
        return "Yes";
    }

    public String getD() {
        return "No";
    }

    public String getG() {
        return "Help";
    }

    public String getH() {
        return "Done";
    }
}