/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

import starbucks.Device.ORIENTATION_MODE;

// import starbucks.Device.ORIENTATION_MODE;

/**
 * My Cards Screen
 */
public class MyCards extends Screen {
	Device d;
	IApp app;
	MyCardsPay myPay;

	/**
	 * Default Constructor for the class
	 */
	public MyCards() {
		this.d = Device.getInstance();
		this.app = (IApp) d;
		myPay= new MyCardsPay();
	}
	
	/**
	 * gives the name to the screen
	 * @return name of the screen
	 */
	public String name() {
		return "My Cards";
	}
	
	/**
	 * Touch method specifies what happens when you touch on this screen
	 * at given coordinates
	 * @param x X coord
	 * @param y Y Coord
	 */
	public void touch(int x, int y) {
		if(d.getDeviceOrientation() == ORIENTATION_MODE.PORTRAIT) {
			if (x == 3 && y == 3) {
				this.app.execute("A1");        	
			}

			if (x == 2 && y == 4) {
				this.app.execute("MyCardOptions");
			}
		}

	}
	/**
	 * Displays the content of the screen.
	 * @return contents of the screen
	 */
	public String display() { 
		StringBuffer value = new StringBuffer(super.display()) ;
		StringBuffer out = new StringBuffer("$" +app.getBalance());
		//centered Layout
		value.append(super.formatSpacingLandscapeSupported(out.toString()));
		return value.toString() ; 
	}
}
