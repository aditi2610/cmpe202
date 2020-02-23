/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

import starbucks.Device.ORIENTATION_MODE;

/** My Card Pay Screen */
public class MyCardsPay extends Screen
{
	Device d;
	IApp app;

	private static final double coffeeCharge = 1.50;
	public MyCardsPay()
	{
		this.d = Device.getInstance();
		this.app = (IApp)d;
	}
	public String name() {
		return "My Cards" ; 
	}

	public void touch(int x, int y) {
		if(d.getDeviceOrientation() == ORIENTATION_MODE.PORTRAIT) {
			if(x ==3 && y ==3) {
				this.app.execute("MyCard");
			}

			if( y==2 && (x==2 || x==3)) {
				double balance = Double.parseDouble(app.getBalance());
				if(balance > coffeeCharge) {
					app.setBalance((balance- coffeeCharge));
				}
			}
		}

	}

	public String display() {
		String out = super.display();
		String value = "";
		if(app.getCardNumber().length() ==0)
			value += "[000000000]\n";
		else
			value += "[" +app.getCardNumber()+ "]\n";
		value += "\n";
		value += "\n";
		value += "Scan Now";
		out+= super.formatSpacingLandscapeSupported(value);
		return out;
	}


}

