/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

import starbucks.Device.ORIENTATION_MODE;


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
		decorator = new CenteredIndentationDecorator();
	}
	
	/**
	 * gives the name to the screen
	 * @return name of the screen
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
	}
	
	/**
	 * Touch method specifies what happens when you touch on this screen
	 * at given coordinates
	 * @param x X coord
	 * @param y Y Coord
	 */
	public void touch(int x, int y) {
		String s = x+ ","+y;
		if(d.getDeviceOrientation() == ORIENTATION_MODE.PORTRAIT) {
			switch(s) {
			case "3,3":
				this.app.execute("A1");       
				break;
			case "2,4":
				this.app.execute("MyCardOptions");
				break;
			default:
				break;
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
		this.decorator.setScreenContents(out.toString());
		if(d.getDeviceOrientation() == ORIENTATION_MODE.LANDSCAPE) {
			this.decorator.setWidth(Device.landscape_screen_width-1);
			this.decorator.setLength(Device.landscape_screen_length);
		}else if(d.getDeviceOrientation() == ORIENTATION_MODE.PORTRAIT) {
			this.decorator.setWidth(Device.portrait_screen_width-1);
			this.decorator.setLength(Device.portrait_screen_length);
		}
		
		value.append(this.decorator.display());
		return this.decorator.displayScreen(value);
	}
}
