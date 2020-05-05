/** Menu Option */
public class MenuItem implements IMenuInvoker {
    private IMenuCommand cmd;

    /**
     * Set Command for Menu Option
     * 
     * @param c [Executes the passed menu command]
     */
    public void setCommand(IMenuCommand c) {
        cmd = c;
    }

    /** Invoke Menu Option */
    public void invoke() {
        cmd.execute();
    }

}
