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
		decorator = new CenteredIndentationDecorator();
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
		default:
			break;
		}
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
		this.decorator.setScreenContents(out.toString());		
		value.append(this.decorator.display());
		return this.decorator.displayScreen(value);
	}


}
