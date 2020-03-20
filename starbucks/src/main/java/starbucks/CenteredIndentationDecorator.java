package starbucks;

import starbucks.Device.ORIENTATION_MODE;

/** Centered Indentation for Decorator **/
public class CenteredIndentationDecorator extends IndentationDecorator {
	/** Constructor */
	public CenteredIndentationDecorator(IScreen screen) {
		super(screen);
	}

	/**
	 * Return Display Component Contents
	 * 
	 * @return Display Component Contents
	 */
	@Override
	public String display() {
		return centeredDisplay(screen.display());
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
		// TODO Auto-generated method stub
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
		s.setNext(s, n);
		
	}

	/**
     * Set previous screen with action name
     * @param s Screen
     * @param n Action
     */
	@Override
	public void setPrev(IScreen s, String n) {
		s.setPrev(s, n);
		
	}
	
	/**
     * Return Centered Display Component Contents
     * @param screenContents screenTobe indented
     * @return Display Component Contents
     */
	private String centeredDisplay( String screenContents ) {
		StringBuffer value = new StringBuffer();

		
		if (screenContents == null || screenContents.isEmpty()) {
			return "";
		}
		String lines[] = screenContents.split("\\r?\\n");
		for (String line : lines) {
			if (line == "\n")
				value.append("\n");
			else {
				int w = line.length();
				if (w < 13) {
					int width = Device.getInstance().getDeviceOrientation()==ORIENTATION_MODE.LANDSCAPE? Device.landscape_screen_width-1: Device.portrait_screen_width-1;
					System.err.println("CenteredIndentationDecorator => Screen Width is:" + width);
					int space = (width - w) / 2;
					value.append(this.padSpaces(space + 1));
				}
					value.append(line);
					value.append("\n");
				
			}
		}
		return displayScreen(value);
	}
}
