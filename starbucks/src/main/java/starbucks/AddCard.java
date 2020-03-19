/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/**
 * Add New Card Screen
 */
public class AddCard extends Screen implements IDisplayComponent {
	private static final String _000000000 = "000000000";
	Device d;
	IApp app;
	CardNumber cardNumber;
	Cvv cvv;

	/**
	 * Dafault constructor
	 */
	public AddCard(CardNumber cardNumber, Cvv cvv) {
		d = Device.getInstance();
		app = (IApp) d;
		this.decorator = new CenteredIndentationDecorator();
		this.cardNumber = cardNumber;
		this.cvv = cvv;
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
		this.app.execute("E");
		this.cardNumber.setCardNum(new StringBuilder(""));
		this.cvv.setCvv(new StringBuilder(""));
		this.app.setBalance(0.00);
	}

	/**
	 * Clicking on next of Add card should go to My cards Page if card is
	 * successfully added.
	 */
	public void next() {
		if (this.cardNumber.getCardNum().length() == 9 && this.cvv.getCvv().length() == 3) {
			if (this.cardNumber.getCardNum().toString().equals(_000000000)) {
				this.cardNumber.setCardNum(new StringBuilder(""));
				this.cvv.setCvv(new StringBuilder(""));
			}else {
				this.app.execute("A");
				app.setBalance(20.00);
			}
		} else {
			this.cardNumber.setCardNum(new StringBuilder(""));
			this.cvv.setCvv(new StringBuilder(""));
		}

	}

}
