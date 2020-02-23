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
	// boolean flagSupportLandscape =false;

	public MyCards() {
		this.d = Device.getInstance();
		this.app = (IApp) d;
		myPay= new MyCardsPay();
		// if(d.getDeviceOrientation()== ORIENTATION_MODE.LANDSCAPE)
		// 	flagSupportLandscape = true;
	}

	public String name() {
		return "My Cards";
	}

	public void touch(int x, int y) {
		if (x == 3 && y == 3) {
			this.app.execute("A1");        	
		}
		if(d.getDeviceOrientation() == ORIENTATION_MODE.PORTRAIT) {
			if (x == 2 && y == 4) {
				this.app.execute("MyCardOptions");
			}
		}

	}

	public String display() { 
		String value = super.display() ;
		String out = "$" +app.getBalance();
		//centered Layout
		value += super.formatSpacingLandscapeSupported(out);
		return value ; 
	}
}
