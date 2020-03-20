/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

import starbucks.Device.ORIENTATION_MODE;

/** My Card Pay Screen */
public class MyCardsPay extends Screen
{
	Device d;
	IApp app;
	CardNumber cardNumber;
	
	/**
	 * Default constructor for the class
	 */
	public MyCardsPay(CardNumber cardNumber)
	{
		this.d = Device.getInstance();
		this.app = (IApp)d;
		//decorator = new CenteredIndentationDecorator();
		this.cardNumber = cardNumber;
	}
	/**
	 * 
	 * @return name to the screen
	 */
	public String name() {
		StringBuilder sb = new StringBuilder();
		String name = "My Cards";
		double width = Device.getInstance().getDeviceOrientation() == ORIENTATION_MODE.LANDSCAPE ? 32.0d : 15.0d;
		int padding = (int) Math.ceil((width - (double)name.length())/2.0d);
		for(int i = 0; i < padding; i++) {
			sb.append(" ");
		}
		sb.append(name);
        return sb.toString();
//		return "My Cards" ; 
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
		StringBuffer value = new StringBuffer(super.display());
		StringBuffer out = new StringBuffer("");
		if(cardNumber.getCardNum().length() ==0)
			out .append("[000000000]\n");
		else
			out.append("[" +cardNumber.getCardNum()+ "]\n");
		out .append("\n");
		out .append("\n");
		out.append("Scan Now");
		return value.append(out).toString();
	}
	



}

