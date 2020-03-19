package starbucks;

/** Card Number */
public class CardNumber implements IDisplayComponent, ITouchEventHandler,IKeyPadObserver {

	ITouchEventHandler nextHandler ;
	private StringBuilder cardNum;
	
	IApp app;
	//private StringBuilder cardNumber="";
	
	CardNumber(){
		cardNum = new StringBuilder("");
		app = (IApp) Device.getInstance();
	}
	
	/**
	 * Displays the content of the screen.
	 * 
	 * @return screen content
	 */
	@Override
	public String display() {
		return "["+getCardNum()+"]";
	}

	 /**
     * Add Sub Component (Not used)
     * @param c Sub Component to Add
     */
	@Override
	public void addSubComponent(IDisplayComponent c) {
		
	}

	/**
	 * Send In Touch Events
	 *
	 * @param x X Coord
	 * @param y Y Coord
	 */
	@Override
	public void touch(int x, int y) {
		if(x ==2 && y==3)
			app.setFocusCvv(true);
		if(nextHandler!= null) {
			nextHandler.touch(x,y) ;
		}
	}

	  /**
     * Set next screen with action name
     * @param next ITouchEventHandler
     */
	@Override
	public void setNext(ITouchEventHandler next) {
		 nextHandler = next ;
	}
	
		/**
	     * Set next screen with action name
	     * @return cardNum
	     */
	public StringBuilder getCardNum() {
		return cardNum;
	}
	
	/**
     * Set next screen with action name
     * @param cardNum set
     */
	public void setCardNum(StringBuilder cardNum) {
		this.cardNum = cardNum;
	}

	 /**
     * Key Event Update
     * @param numKeys   Count of Keys So Far
     * @param key Last key Pressed
     */
	@Override
	public void keyEventUpdate(int numKeys, String key) {
		if(!app.isFocusCvv())
			setCardNum(convertKeyEvent(key));
		
	}
	
	/**
	 * Set the card Number
	 * 
	 * @param key X coord
	 * @return cardNumber
	 */
	private StringBuilder convertKeyEvent(String key) {
		StringBuilder cardNumber = new StringBuilder(getCardNum());
		if(key.equals("X") && cardNumber.length() == 0) {
			return cardNumber;
		}
		
		if(key.equals("X") && cardNumber.length() != 0) {
			cardNumber.deleteCharAt(cardNumber.length() - 1);
			return cardNumber;
		}
		
		if(key.equals(" ")) {
			return cardNumber;
		}
		
		if(cardNumber.length() == 9) {
			return cardNumber;
		}
		
		cardNumber.append(key);
		return cardNumber;
	}
	
}
