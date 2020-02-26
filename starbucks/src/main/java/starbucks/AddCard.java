/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/**
 * Add New Card Screen
 */
public class AddCard extends Screen
{
	Device d;
	IApp app;
	KeyPad kp;

	/**
	 * Dafault constructor
	 */
	public AddCard()
	{
		
		d = Device.getInstance();
		app = (IApp)d;
		this.kp= new KeyPad();
	}
	/**
	 * @return the name of the Screen
	 */
	public String name() {
//		return "Add Card" ; 
		StringBuilder sb = new StringBuilder();
		String name = "Add Card";
		double width = 15.0d;
		int padding = (int) Math.ceil((width - (double)name.length())/2.0d);
		for(int i = 0; i < padding; i++) {
			sb.append(" ");
		}
		sb.append(name);
        return sb.toString();
	} 
	/**
	 * Displays the content of the screen.
	 * @return screen content
	 */
	public String display() {
		StringBuffer out = new StringBuffer(super.display());
		StringBuffer value = new StringBuffer();
		value.append("["+app.getCardNumber()+ "]\n");
		value.append("["+app.getCvv()+"]\n");
		out.append(super.formatSpacing(value.toString()));
		out .append("\n");
		out.append(kp.display());
		out.append("\n");
		return out.toString();
	}
	/**
	 * prev method executes the Settings screen
	 */
	public void prev()  {
		app.execute("E"); 
		app.setCardNumber("");
		app.setCvv("");
		app.setBalance(0.00);
	
	}
	/**
	 * Clicking on next of Add card should
	 * go to My cards Page if card is successfully added.
	 */
	public void next()  {
		if(app.getCardNumber().length() == 9 && app.getCvv().length() == 3) {
			this.app.execute("A");
			app.setBalance(20.00);
		}else {
			app.setCardNumber("");
			app.setCvv("");
		}

	}

	/**
	 * implements the touch event
	 * @param x X Coord
	 * @param y Y Coord
	 */
	public void touch(int x, int y) {
		String s = x+ ","+y;
		switch(s) {
		case "1,2":
		case "2,2":
		case "3,2":
			app.setFocusCvv(false);
			break;
		case "2,3":
			app.setFocusCvv(true);
			break;
		default:
			if(!((x > 0 && x < 4) && (y > 4  && y < 9))) {
				return;
			}
			if(app.isFocusCvv()) {
				setCvv(x, y);
			}
			else if(app.getCardNumber().length() <=9) {
				setCard(x, y);
			}
			break;
		}
		

	}

	/**
	 * Sets the Cvv for the card
	 * @param x X Coord
	 * @param y Y Coord
	 */
	private void setCvv(int x, int y) {
		String s = kp.getKey(x, y);
		StringBuilder cvv = new StringBuilder(app.getCvv());
		switch (s) {
		case "X":
			if (cvv.length() == 0) {
				break;
			}
			cvv.deleteCharAt(cvv.length() - 1);
			break;
		case " ":
			break;
		default:
			if (cvv.length() == 3) {
				break;
			}
			cvv.append(s);
		}
		app.setCvv(cvv.toString());
	}

	/** 
	 * Set the card Number
	 * @param x X coord
	 * @param y Y coord
	 */
	private void setCard(int x, int y) {
		String s = this.kp.getKey(x, y);
		StringBuilder cardNumber = new StringBuilder(app.getCardNumber());
		switch (s) {
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
			cardNumber.append(s);
		}
		app.setCardNumber(cardNumber.toString());
	}


}
