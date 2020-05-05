
/**
 * Main App Class for Displaying Screen.
 */
public class App {

    private IScreen s;

    public App() {
        s = new CenterOrientationDecorator(new Screen());
    }

    public String display() {
        String output = "";
        output += "========================================\n";
        output += "\n";
        output += s.message();
        output += "\n";
        output += "[A] " + s.A() + "                    " + s.E() + " [F]\n";
        output += "\n";
        output += "[B] " + s.B() + "                    " + s.F() + " [G]\n";
        output += "\n";
        output += "[C] " + s.C() + "                    " + s.G() + " [H]\n";
        output += "\n";
        output += "[D] " + s.D() + "                    " + s.H() + " [I]\n";
        output += "\n";
        output += "\n";
        output += s.getAdvertisement();
        output += "========================================\n";
        return output;
    }

    public void key(String keypad) {
        s.key(keypad);
    }

}
