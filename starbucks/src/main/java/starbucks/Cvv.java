package starbucks;

/**Cvv */
public class Cvv implements IDisplayComponent, ITouchEventHandler, IKeyPadObserver {
	private StringBuilder cvv;
	ITouchEventHandler nextHandler;
	IApp app;

	Cvv() {
		cvv = new StringBuilder("");
		app = (IApp) Device.getInstance();
	}

	/**
     * Returns Cvv
     * @return cvv
     */
	public StringBuilder getCvv() {
		return cvv;
	}
	/**
     * Set next screen with action name
     * @param cvv set
     */
	public void setCvv(StringBuilder cvv) {
		this.cvv = cvv;
	}

	 /**
     * Key Event Update
     * @param numKeys   Count of Keys So Far
     * @param key Last key Pressed
     */
	@Override
	public void keyEventUpdate(int numKeys, String key) {
		if (app.isFocusCvv()) {
			setCvv(convertKeyEvent(key));
		}

	}
	
	/**
	 * Send In Touch Events
	 *
	 * @param x X Coord
	 * @param y Y Coord
	 */
	@Override
	public void touch(int x, int y) {
		if(y ==2) {
			if(x>0 && x <4) {
				app.setFocusCvv(false);
			}
		}
		if (nextHandler != null) {
			nextHandler.touch(x, y);
		}

	}
	  /**
     * Set next screen with action name
     * @param next Screen
     * 
     */
	@Override
	public void setNext(ITouchEventHandler next) {
		nextHandler = next;

	}

	/**
	 * Displays the content of the screen.
	 * 
	 * @return screen content
	 */
	@Override
	public String display() {
		System.err.println("Cvv => display");
		return "[" + getCvv() + "]";
	}

	 /**
     * Add Sub Component (Not used)
     * @param c Sub Component to Add
     */
	@Override
	public void addSubComponent(IDisplayComponent c) {

	}

	/**
	 * Sets the Cvv for the cardx
	 * 
	 * @param key X Coord
	 * @return cvv
	 */
	private StringBuilder convertKeyEvent(String key) {
		StringBuilder cvv = new StringBuilder(getCvv());
		
		while(true) {
			if(key.equals("X")) {
				if(cvv.length() == 0)
					break;
				cvv.deleteCharAt(cvv.length() - 1);
				break;
			}
			if(key.equals(" "))
				break;
			if(cvv.length() ==3)
				break;
			else
				cvv.append(key);
		}
//		switch (key) {
//		case "X":
//			if (cvv.length() == 0) {
//				break;
//			}
//			cvv.deleteCharAt(cvv.length() - 1);
//			break;
//		case " ":
//			break;
//		default:
//			if (cvv.length() == 3) {
//				break;
//			}
//			cvv.append(key);
//		}
		return cvv;
	}

}
