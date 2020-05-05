
public class GradeAndGasPumpState extends State {
    IScreen myscreen;

    public String message() {
        return "Grade and Gas Pump..";
    }

    public GradeAndGasPumpState(IScreen screen) {
        myscreen = screen;
    }

    @Override
    public void key(String keypad) {
        myscreen.setState(6);

    }

    // @Override
    // public String display() {
    // // TODO Auto-generated method stub
    // return null;
    // }
}