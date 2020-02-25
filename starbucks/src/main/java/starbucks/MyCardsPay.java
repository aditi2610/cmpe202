/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

import starbucks.Device.ORIENTATION_MODE;

/** My Card Pay Screen */
public class MyCardsPay extends Screen
{
	Device d;
	IApp app;

	
	/**
	 * Default constructor for the class
	 */
	public MyCardsPay()
	{
		this.d = Device.getInstance();
		this.app = (IApp)d;
	}
	/**
	 * 
	 * @return name to the screen
	 */
	public String name() {
		return "My Cards" ; 
	}
	/**
     * Define what happens when you touch this screen at given coordinates
     * @param x X coord
     * @param y Y coord
     */
	public void touch(int x, int y) {
		if(d.getDeviceOrientation() == ORIENTATION_MODE.PORTRAIT) {
			if(x ==3 && y ==3) {
				this.app.execute("MyCard");
			}

			if( y==2 && (x==2 || x==3)) {
				double balance = Double.parseDouble(app.getBalance());
				if(balance > Device.coffeeCharge) {
					app.setBalance((balance- Device.coffeeCharge));
				}
			}
		}

	}
	/**
	 * Displays the content of the screen.
	 * @return content of the screen
	 */
	public String display() {
		StringBuffer out = new StringBuffer(super.display());
		StringBuffer value = new StringBuffer("");
		if(app.getCardNumber().length() ==0)
			value .append("[000000000]\n");
		else
			value.append("[" +app.getCardNumber()+ "]\n");
		value .append("\n");
		value .append("\n");
		value.append("Scan Now");
		out.append(super.formatSpacingLandscapeSupported(value.toString()));
		return out.toString();
	}


}

