/** Menu Command Interface */
public interface IMenuCommand {
    /** Execute the Command */
    void execute();

    /**
     * Configure the Receiver for the Command
     * 
     * @param target Receiver
     */
    void setReceiver(IMenuReceiver target);
}
