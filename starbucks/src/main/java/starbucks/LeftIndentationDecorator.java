package starbucks;

/** Left Indentation for Decorator**/
public class LeftIndentationDecorator extends IndentationDecorator {
	 /** CONSTRUCTOR **/
	public LeftIndentationDecorator(IScreen screen) {
		super(screen);
	}

	/**
     * Return Display Component Contents
     * @return Display Component Contents
     */
	@Override
	public String display() {
		return setLeftDisplay(screen.display());
	}

	/**
	 * Send Touch Events to the Chain
	 * @param x Touch X Coord.
	 * @param y Touch Y Coord.
	 */
	@Override
	public void touch(int x, int y) {
		screen.touch(x, y);
		
	}

	/**
	 * Get Class Name of Current Screen
	 * @return Class Name of Current Screen
	 */
	@Override
	public String name() {
		return screen.name();
	}
	/**
     * Navigate to next screen
     */
	@Override
	public void next() {
		screen.next();
		
	}
	/**
     * Navigate to previous screen
     */
	@Override
	public void prev() {
		screen.prev();
		
	}

	/**
     * Set next screen with action name
     * @param s Screen
     * @param n Action
     */
	@Override
	public void setNext(IScreen s, String n) {
		screen.setNext(s, n);
		
	}

	/**
     * Set previous screen with action name
     * @param s Screen
     * @param n Action
     */
	@Override
	public void setPrev(IScreen s, String n) {
		screen.setPrev(s, n);
		
	}
	/**
     * Return Left Display Component Contents
     * @param screenContents screenTobe indented
     * @return Display Component Contents
     */
	private String setLeftDisplay(String screenContents) {
		StringBuffer value= new StringBuffer("");
		int width = Device.portrait_screen_width-1;
		System.err.println("LeftIndentationDecorator => Screen Width is:"+ width);
		if (screenContents == null || screenContents.length()==0) {
			return "";
		}
		value.append(screenContents);
		return displayScreen(value);
	}
	


}
