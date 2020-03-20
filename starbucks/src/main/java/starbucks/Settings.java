/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** Settings Screen */
public class Settings extends Screen
{
	IApp app;
	CardNumber cardNumber;
	Cvv cvv;
	/**
	 * Default constructor for the class
	 */
	public Settings(CardNumber cNumber, Cvv cvv)
	{
		this.app = (IApp)Device.getInstance() ;
		this.cardNumber = cNumber;
		this.cvv= cvv;
	}
	/**
	 * @return Gives the name of the screen
	 */
	public String name() {
		StringBuilder sb = new StringBuilder();
		String name = "Settings";
		double width = 15.0d;
		int padding = (int) Math.ceil((width - (double)name.length())/2.0d);
		for(int i = 0; i < padding; i++) {
			sb.append(" ");
		}
		sb.append(name);
        return sb.toString();

//		return "Settings" ; 
	} 

	/**
	 * Send Touch Events to the Chain
	 * @param x Touch X Coord.
	 * @param y Touch Y Coord.
	 */
	public void touch(int x, int y) {
		
		if(y ==1) {
			if(x > 0 && x < 4) {
				cardNumber.setCardNum(new StringBuilder(""));
				cvv.setCvv(new StringBuilder(""));
				this.app.setFocusCvv(false);
				this.app.execute("AddCard");
			}
		}
//		String s = x+","+y;
//		switch(s) {
//		case "1,1":
//		case "2,1":
//		case "3,1":
//			cardNumber.setCardNum(new StringBuilder(""));
//			cvv.setCvv(new StringBuilder(""));
//			this.app.setFocusCvv(false);
//			this.app.execute("AddCard");
//			break;
//		default:
//			break;
//		}
	}

	/**
	 * @return Displays the contents of the screen
	 */
	public String display() { 
		StringBuffer value = new StringBuffer(super.display()) ;
		StringBuffer out = new StringBuffer();
		out .append("Add Card\n");
		out .append("Delete Card\n");
		out .append("Billing\n");
		out .append("Passcode\n\n");
		out .append("About|Terms\n");
		out .append("Help");
		return value.append(out).toString();
	}


}
