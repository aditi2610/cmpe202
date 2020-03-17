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
		decorator = new CenteredIndentationDecorator();
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
		displayScreenContent();
		value.append(this.decorator.display());
		return this.decorator.displayScreen(value);
		//return value.toString();
	}
	
	/**
	 * Displays the screen contents
	 */
	private void displayScreenContent() {
		StringBuffer out = new StringBuffer("");
		if(app.getCardNumber().length() ==0)
			out .append("[000000000]\n");
		else
			out.append("[" +app.getCardNumber()+ "]\n");
		out .append("\n");
		out .append("\n");
		out.append("Scan Now");
		
		this.decorator.setScreenContents(out.toString());
		if(d.getDeviceOrientation() == ORIENTATION_MODE.LANDSCAPE) {
			this.decorator.setWidth(Device.landscape_screen_width-1);
			this.decorator.setLength(Device.landscape_screen_length);
		}else if(d.getDeviceOrientation() == ORIENTATION_MODE.PORTRAIT) {
			this.decorator.setWidth(Device.portrait_screen_width-1);
			this.decorator.setLength(Device.portrait_screen_length);
		}
	}


}

