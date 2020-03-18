/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/**
 * Add New Card Screen
 */
public class AddCard extends Screen implements IDisplayComponent {
	Device d;
	IApp app;
	
	/**
	 * Dafault constructor
	 */
	public AddCard() {
		d = Device.getInstance();
		app = (IApp) d;
		this.decorator = new CenteredIndentationDecorator();
	}

	/**
	 * @return the name of the Screen
	 */
	public String name() {
		StringBuilder sb = new StringBuilder();
		String name = "Add Card";
		double width = 15.0d;
		int padding = (int) Math.ceil((width - (double) name.length()) / 2.0d);
		for (int i = 0; i < padding; i++) {
			sb.append(" ");
		}
		sb.append(name);
		return sb.toString();
	}

	/**
	 * Displays the content of the screen.
	 * 
	 * @return screen content
	 */
	public String display() {
		StringBuffer value = new StringBuffer();
		StringBuffer out = new StringBuffer();
		out.append(super.display());

		this.decorator.setScreenContents(out.toString());
		value.append(this.decorator.display());
		return this.decorator.displayScreen(value);
	}

	/**
	 * prev method executes the Settings screen
	 */
	public void prev() {
		app.execute("E");
		CardNumber.cardNum =new StringBuilder("");
		Cvv.cvv=new StringBuilder("");
		app.setBalance(0.00);
	}

	/**
	 * Clicking on next of Add card should go to My cards Page if card is
	 * successfully added.
	 */
	public void next() {
		if (CardNumber.cardNum.length() == 9 && Cvv.cvv.length() == 3) {
			this.app.execute("A");
			app.setBalance(20.00);
//			}
		} else {
			CardNumber.cardNum = new StringBuilder("");
			Cvv.cvv= new StringBuilder("");
		}

	}


}
