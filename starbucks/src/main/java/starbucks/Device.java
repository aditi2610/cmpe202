/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/**
 * Authentication Proxy for App Controller
 */
public class Device implements IApp, IPinAuthObserver {

	private static Device theDevice = null;
	private boolean fourPin = false;
	private boolean sixPin = false;
	private String pin = "";

	private IApp app;
	private KeyPad kp;
	private Passcode pc;
	private Passcode6 pc6;
	private PinScreen ps;

	private Spacer sp;
	private boolean authenticated = false;
	private PinEntryMachine pm;

	public static final int screen_frame_header = 3;
	public static final int portrait_screen_width = 15;
	public static final int portrait_screen_length = 10;
	public static final int landscape_screen_width = 32;
	public static final int landscape_screen_length = 6;
	public static final double coffeeCharge = 1.50;

	/**
	 * 
	 * @return Enum as Portrait or Landscape
	 *
	 */
	public enum ORIENTATION_MODE {
		PORTRAIT, LANDSCAPE
	}

	private ORIENTATION_MODE device_orientation_state = null;

	/**
	 * 
	 * @return Return the current Orientation of the Device
	 */
	public ORIENTATION_MODE getDeviceOrientation() {
		return this.device_orientation_state;
	}

	/**
	 * Sets Device to portraitOrientation
	 */
	public void setPortraitOrientation() {
		//System.err.println("Setting Orientation to Portrait");
		this.device_orientation_state = ORIENTATION_MODE.PORTRAIT;
		app.portrait();
	}

	/**
	 * Sets Landscape to portraitOrientation
	 */
	public void setLandscapeOrientation() {
		//System.err.println("Setting Orientation to Landscape");
		this.device_orientation_state = ORIENTATION_MODE.LANDSCAPE;
		app.landscape();
	}

	private Device() {
	}

//	/** Debug Device State */
//	public static void debug() {
//		Device d = Device.getInstance();
//		System.err.println("============================================");
//		System.err.println("--------- D E V I C E  S T A T E  ----------");
//		System.err.println("============================================");
//		System.err.println("Pin Option    = " + d.getPinOption());
//		System.err.println("Stored Pin    = " + d.getPin());
//		System.err.println("Authenticated = " + d.isAuthenticated());
//		System.err.println("Orientation   = " + d.getDeviceOrientation());
//		System.err.println("============================================");
//	}

	/**
	 * Get Current Auth State
	 * 
	 * @return Auth T/F
	 */
	public String isAuthenticated() {
		return Boolean.toString(authenticated);
	}

	/**
	 * Return the current Pin Option: 0 = User Chosed No Pin 4 = User Chosed 4-digit
	 * Pin 6 = User Chosed 6-digit Pin
	 * 
	 * @return Pin Option
	 */
	public int getPinOption() {
		if (fourPin)
			return 4;
		else if (sixPin)
			return 6;
		else
			return 0;
	}

	/**
	 * Get Current Pin
	 * 
	 * @return Pin
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * Set Pin
	 * 
	 * @param p New Pin
	 */
	private void setPin(String p) {
		pin = p;
		int len = p.length();
		if (len == 4) {
			fourPin = true;
			sixPin = false;
			return;
		}
		
		if (len == 6) {
			fourPin = false;
			sixPin = true;
			return;
		}

		fourPin = false;
		sixPin = false;
		return;
	}

	/**
	 * Get Singleton Instance
	 * 
	 * @return Reference to Current Device Config (Create if none exists)
	 */
	public synchronized static Device getInstance() {
		if (theDevice == null) {
			// return getNewInstance("");
			return getNewInstance("1234");
			// return getNewInstance("000000");
		}
		if (theDevice.getPin().length() == 0) {
			if (theDevice.getPinOption() == 0) {
				System.err.println("Device => Device getInstance()");
				theDevice.setPin("");
				theDevice.authenticated = true;
			}
			if (theDevice.getPinOption() == 4) {
				theDevice.setPin("1234");
			}
			if (theDevice.getPinOption() == 6) {
				theDevice.setPin("123456");
			}
		}
		return theDevice;

	}

	/**
	 * Get New Instance
	 * 
	 * @return Reference to Device (Create New Singleton)
	 */
	public synchronized static Device getNewInstance() {
		return getNewInstance("1234");
		 //return getNewInstance("000000");
		 //return getNewInstance("");
	}

	/**
	 * 
	 * @param pin the default pin
	 * @return instance of device
	 */
	public synchronized static Device getNewInstance(String pin) {
		//System.err.println("Device => getNewInstance(" + pin + ")");
		theDevice = new Device();
		theDevice.setPin(pin);
		theDevice.startUp();
		//debug();
		return theDevice;
	}

	/**
	 * Device Starup Process. Starts Up with Default 6-Pin Option
	 */
	public void startUp() {
		if (pin.length() == 4 || pin.length() == 6) {
			deviceWithPin();
		}
		else {
			this.authenticated = true;
		}
		// get app controller reference
		app = new AppController();
		// startup in portrait
		this.device_orientation_state = this.device_orientation_state != null?this.device_orientation_state: ORIENTATION_MODE.PORTRAIT;
	}
	/**
	 * Device Starup Process. Starts Up with Default 6-Pin Option
	 */
	private void deviceWithPin() {
		kp = new KeyPad();
		sp = new Spacer();
		ps = new PinScreen();
		pm = new PinEntryMachine();

		if (pin.length() == 6) {
			pc6 = new Passcode6();
			((PinScreen)ps).addSubComponent(pc6);
			((IKeyPadSubject) kp).attach(pc6);
		} else {
			pc = new Passcode();
			((PinScreen)ps).addSubComponent(pc);
			((IKeyPadSubject) kp).attach(pc);
		}
		((PinScreen)ps).addSubComponent(sp);
		((PinScreen)ps).addSubComponent(kp);
		((IKeyPadSubject) kp).attach(pm);
		((IPinAuthSubject) pm).registerObserver(this);
	}


	/**
	 * Switch to Landscape View
	 */
	public void landscape() {
		//System.err.println("Landscape was called");
		if (authenticated) {
			setLandscapeOrientation();
		}
	}

	/**
	 * Switch to Portait View
	 */
	public void portrait() {
		//System.err.println("Portrait was called");
		if (authenticated) {
			setPortraitOrientation();
		}
	}

	/**
	 * User Touch at X,Y Coordinates
	 * 
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 */
	public void touch(int x, int y) {

//		System.err.println("Device touch method  Orientation is:s" + Device.getInstance().getDeviceOrientation());
//
//		System.err.println("Device touched at    = " + x + " " + y);
		if (authenticated)
			app.touch(x, y);
		else
			ps.touch(x, y);
	}

	/**
	 * Display Screen Contents to Terminal
	 */
	public void display() {
		System.out.println(screenContents());
	}

	/**
	 * Get Class Name of Screen
	 * 
	 * @return Class Name of Current Screen
	 */
	public String screen() {
		if (authenticated)
			return app.screen();
		else
			return ps.name();
	}

	/**
	 * Get Screen Contents as a String
	 * 
	 * @return Screen Contents of Current Screen
	 */
	public String screenContents() {
		if (authenticated) {
			return app.screenContents();
		} else {
			String psDisplay = ps.display();
			boolean invalid = psDisplay.contains("Invalid");

			String out = "";
			out = "---------------\n";
			out += "   " + ps.name() + "  \n";
			if (invalid) {
				out += "---------------\n";
			} else {
				out += "---------------\n\n\n";
			}
			out += psDisplay;
			out += "\n\n\n---------------\n";
			return out;
		}
	}

	/**
	 * Select a Menu Command
	 * 
	 * @param c Menu Option (A, B, C, E, or E)
	 */
	public void execute(String c) {
		//System.err.println("Device executed at    = " + c);
		if (authenticated)
			app.execute(c);
	}

	/**
	 * Navigate to Previous Screen
	 */
	public void prev() {
		//System.err.println("Device executed at prev");
		if (authenticated)
			app.prev();
	}

	/**
	 * Navigate to Next Screen
	 */
	public void next() {
		//System.err.println("Device executed at next");
		if (authenticated)
			app.next();
	}

	/**
	 * Receive Authenticated Event from Authenticator
	 */
	public void authEvent() {
		this.authenticated = true;
	}

	/**
	 * @return the Balance
	 */
	@Override
	public String getBalance() {

		return this.app.getBalance();
	}

	/**
	 * @param d Sets the balance of a card
	 */
	@Override
	public void setBalance(Double d) {
		this.app.setBalance(d);

	}

	/**
	 * return focus defualt is false
	 * 
	 * @return if the focus is on Cvv or not
	 */
	@Override
	public boolean isFocusCvv() {
		return app.isFocusCvv();
	}

	/**
	 * 
	 * @param focusCvv sets the focus
	 */
	@Override
	public void setFocusCvv(boolean focusCvv) {
		app.setFocusCvv(focusCvv);

	}

}
