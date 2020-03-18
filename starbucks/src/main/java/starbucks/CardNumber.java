package starbucks;

public class CardNumber implements IDisplayComponent, ITouchEventHandler,IKeyPadObserver {

	ITouchEventHandler nextHandler ;
	static StringBuilder cardNum;
	IApp app;
	
	CardNumber(){
		cardNum = new StringBuilder("");
		app = (IApp) Device.getInstance();
	}
	@Override
	public String display() {
		return "["+getCardNum()+"]";
	}

	@Override
	public void addSubComponent(IDisplayComponent c) {
		
	}

	@Override
	public void touch(int x, int y) {
		if(x ==2 && y==3)
			app.setFocusCvv(true);
		if(nextHandler!= null) {
			nextHandler.touch(x,y) ;
		}
//		System.err.println("CardNumber => touch "+s + " "+ nextHandler);
//		
//		switch(s) {
////		case "1,2":
////		case "2,2":
////		case "3,2":
////			break;
//		case "2,3":
//			setNext(nextHandler);    
//			break;
//		default:
//			if(!((x > 0 && x < 4) && (y > 4  && y < 9))) {
//				return;
//			}
//			else if(getCardNum().length() <=9) {
//				setCardNum(cardNum.append(key));
//				System.err.println("CardNumber => touch");
//			}
//			break;
//		}
	}

	@Override
	public void setNext(ITouchEventHandler next) {
		 nextHandler = next ;
		
	}
	public StringBuilder getCardNum() {
		return cardNum;
	}

	@Override
	public void keyEventUpdate(int numKeys, String key) {
		if(!app.isFocusCvv())
			cardNum=(convertKeyEvent(key));
		
	}
	
	/**
	 * Set the card Number
	 * 
	 * @param x X coord
	 * @param y Y coord
	 */
	private StringBuilder convertKeyEvent(String key) {
		StringBuilder cardNumber = new StringBuilder(getCardNum());
		switch (key) {
		case "X":
			if (cardNumber.length() == 0) {
				break;
			}
			cardNumber.deleteCharAt(cardNumber.length() - 1);
			break;
		case " ":
			break;
		default:
			if (cardNumber.length() == 9) {
				break;
			}
			cardNumber.append(key);
		}
		return cardNumber;
	}
	
}
