/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** Settings Screen */
public class Settings extends Screen
{
	IApp app;
	/**
	 * Default constructor for the class
	 */
	public Settings()
	{
		this.app = (IApp)Device.getInstance() ; 
	}
	/**
	 * @return Gives the name of the screen
	 */
	public String name() {
		return "Settings" ; 
	} 

	/**
	 * Send Touch Events to the Chain
	 * @param x Touch X Coord.
	 * @param y Touch Y Coord.
	 */
	public void touch(int x, int y) {
		String s = x+","+y;
		switch(s) {
		case "1,1":
		case "2,1":
		case "3,1":
			app.setCardNumber("");
			app.setCvv("");
			app.setFocusCvv(false);
			this.app.execute("AddCard");
			break;
		}
		//    	if(y ==1 && (x ==1 || x==2 || x==3)) {
		//    		this.app.execute("AddCard");
		//    	}
	}

	/**
	 * @return Displays the contents of the screen
	 */
	public String display() { 
		StringBuffer out = new StringBuffer(super.display()) ;
		StringBuffer value = new StringBuffer();
		value .append("Add Card\n");
		value .append("Delete Card\n");
		value .append("Billing\n");
		value .append("Passcode\n\n");
		value .append("About|Terms\n");
		value .append("Help");
		out .append(super.formatSpacing(value.toString()));

		return out.toString() ; 
	}


}
