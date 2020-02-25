/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/**
 * Authentication Proxy for App Controller
 */
public class Device implements IApp, IPinAuthObserver {
    
    private static Device theDevice = null;   
    private boolean fourPin = true ;
    private boolean sixPin = false ;
    private String pin = "" ; 

    private IApp app ;
    private KeyPad kp ;
    private Passcode pc ;
    private Passcode6 pc6;
    private PinScreen ps ;

    private Spacer sp ;
    private boolean authenticated = false ;
    private PinEntryMachine pm ;

    public static final int screen_frame_header = 3 ;
    public static final int portrait_screen_width = 15 ;
    public static final int portrait_screen_length = 10 ;
    public static final int landscape_screen_width = 32 ;
    public static final int landscape_screen_length = 6 ;
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
        return this.device_orientation_state ;
    }
    
    /**
     * Sets Device to portraitOrientation
     */

    public void setPortraitOrientation() {
    	 System.err.println( "Setting Orientation to Portrait") ;
        this.device_orientation_state = ORIENTATION_MODE.PORTRAIT ;
    }
    
    /**
     * Sets Landscape to portraitOrientation
     */
    public void setLandscapeOrientation() {
    	 System.err.println( "Setting Orientation to Landscape") ;
        this.device_orientation_state = ORIENTATION_MODE.LANDSCAPE ;
    }

    private Device() { }

    /** Debug Device State */
    public static void debug()
    {
        Device d = Device.getInstance() ;
        System.err.println( "============================================" ) ;
        System.err.println( "--------- D E V I C E  S T A T E  ----------" ) ;
        System.err.println( "============================================" ) ;
        System.err.println( "Pin Option    = " + d.getPinOption() ) ;
        System.err.println( "Stored Pin    = " + d.getPin() ) ;
        System.err.println( "Authenticated = " + d.isAuthenticated() ) ;
        System.err.println( "Orientation   = " + d.getDeviceOrientation() ) ;
        System.err.println( "============================================" ) ;
    }

    /**
     * Get Current Auth State
     * @return Auth T/F
     */
    public String isAuthenticated() {
        return Boolean.toString( authenticated ) ;
    }    

    /**
     * Return the current Pin Option:
     *  0 = User Chosed No Pin
     *  4 = User Chosed 4-digit Pin
     *  6 = User Chosed 6-digit Pin
     * @return Pin Option
     */
    public int getPinOption() {
        if ( fourPin )
            return 4 ;
        else if ( sixPin )
            return 6 ;
        else
            return 0 ;
    }

    /**
     * Get Current Pin
     * @return Pin
     */
    public String getPin() {
        return pin ;
    }


    /**
     * Set Pin
     * @param p New Pin
     */
    private void setPin( String p ) {
        pin = p ;
        int len = p.length() ;
        switch ( len ) {
            case 0:
                fourPin = false ;
                sixPin = false ; 
                break;
            case 4:
                fourPin = true ;
                sixPin = false ;
                break ;
            case 6:
                fourPin = false ;
                sixPin = true ;
                break ;
            default:
                fourPin = false ;
                sixPin = false ;
                break;
        }
    }

    /**
     * Get Singleton Instance
     * @return Reference to Current Device Config (Create if none exists)
     */
    public synchronized static Device getInstance() {
        if (theDevice == null ) {
            return getNewInstance( "1234" ) ;
        }
        else {
        	if(theDevice.getPin().length() ==0)
        	{
        		if(theDevice.getPinOption() ==4)
        		{
        			theDevice.setPin("1234");
        		}else
        		{
        			theDevice.setPin("123456");
        		}
        	}
            return theDevice;
        }
    }

    /**
     * Get New Instance 
     * @return Reference to Device (Create New Singleton)
     */
    public synchronized static Device getNewInstance() {
        return getNewInstance( "1234" ) ;
    	//return getNewInstance("123456");
    }
    /**
     * 
     * @param pin the default pin
     * @return instance of device
     */
    public synchronized static Device getNewInstance( String pin ) {
        theDevice = new Device() ;
        theDevice.setPin( pin ) ;
        if(pin.length() == 4)
        	theDevice.startUp4Pin() ;
        else
        	theDevice.startUp6Pin();
        debug() ;
        return theDevice ;
    }

    /**
     * Device Starup Process.  
     * Starts Up with Default 4-Pin Option
     */
    public void startUp4Pin()
    {
        kp = new KeyPad() ;
        pc = new Passcode() ;
        sp = new Spacer() ;
        ps = new PinScreen() ;
        pm = new PinEntryMachine() ;

        // setup the composite pattern
        ps.addSubComponent( pc ) ;
        ps.addSubComponent( sp ) ;
        ps.addSubComponent( kp ) ;

        // setup the observer pattern
        ((IKeyPadSubject)kp).attach( pc ) ;
        ((IKeyPadSubject)kp).attach( pm ) ;
        ((IPinAuthSubject)pm).registerObserver(this) ;

        // get app controller reference
        app = new AppController() ;        

        // startup in portrait
        if (this.device_orientation_state == null) {
            this.device_orientation_state = ORIENTATION_MODE.PORTRAIT ;
        }
    }
    
    /**
     * Device Starup Process.  
     * Starts Up with Default 6-Pin Option
     */
    public void startUp6Pin()
    {
        kp = new KeyPad() ;
        pc6 = new Passcode6() ;
        sp = new Spacer() ;
        ps = new PinScreen() ;
        pm = new PinEntryMachine() ;

        // setup the composite pattern
        ps.addSubComponent( pc6 ) ;
        ps.addSubComponent( sp ) ;
        ps.addSubComponent( kp ) ;

        // setup the observer pattern
        ((IKeyPadSubject)kp).attach( pc6 ) ;
        ((IKeyPadSubject)kp).attach( pm ) ;
        ((IPinAuthSubject)pm).registerObserver(this) ;

        // get app controller reference
        app = new AppController() ;        

        // startup in portrait
        if (this.device_orientation_state == null) {
            this.device_orientation_state = ORIENTATION_MODE.PORTRAIT ;
        }
    }

    /**
    * Switch to Landscape View
    */
    public void landscape() {
        if ( authenticated ) {
            app.landscape() ;
            setLandscapeOrientation();
        }
    }

    /**
     * Switch to Portait View
     */
    public void portrait() {
        if ( authenticated ) {
            app.portrait() ;
            setPortraitOrientation();
        }
    }

    /**
     * User Touch at X,Y Coordinates
     * @param x X Coordinate
     * @param y Y Coordinate
     */
    public void touch(int x, int y) {
    	 System.err.println( "Device touched at    = " + x +  " " +y ) ;
        if ( authenticated )
            app.touch(x, y) ;
        else
            ps.touch(x, y) ;
    }

    /**
     * Display Screen Contents to Terminal
     */
    public void display() {
        System.out.println( screenContents() ) ;
    }

    /**
     * Get Class Name of Screen
     * @return Class Name of Current Screen
     */
    public String screen() {
        if ( authenticated )
            return app.screen() ;
        else
            return ps.name() ;
    }

    /**
     * Get Screen Contents as a String
     * @return Screen Contents of Current Screen
     */
    public String screenContents() {
        if ( authenticated ) {
            return app.screenContents() ;
        } else {
        	String psDisplay = ps.display();
        	boolean invalid = psDisplay.contains("Invalid");

            String out = "" ;
            out = "----------------\n" ;
            out += "   " + ps.name() + "  \n" ;
            if (invalid) {
            	out += "----------------\n" ;
            }
            else {
                out += "----------------\n\n\n" ;            	
            }
            out += psDisplay;
            out += "\n\n\n----------------\n" ;	
            return out ;
        }
    }


    /**
     * Select a Menu Command
     * @param c Menu Option (A, B, C, E, or E)
     */
    public void execute( String c ) {
    	 System.err.println( "Device executed at    = " + c) ;
        if ( authenticated )
            app.execute( c ) ;
    }

    /**
     * Navigate to Previous Screen
     */
    public void prev() {
    	 System.err.println( "Device executed at prev") ;
        if ( authenticated )
            app.prev() ;
    }

    /**
     * Navigate to Next Screen
     */
    public void next() {
    	 System.err.println( "Device executed at next") ;
        if ( authenticated )
            app.next() ;
    }

    /**
     * Receive Authenticated Event from Authenticator
     */
    public void authEvent() {
        this.authenticated = true ;
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
	 * @return the Card Number
	 */
	@Override
	public String getCardNumber() {
		return this.app.getCardNumber();
	}
	/**
	 * @param s Sets the CardNumber 
	 */
	@Override
	public void setCardNumber(String s) {
		this.app.setCardNumber(s);
		
	}
	/**
	 * @return the Cvv 
	 */
	@Override
	public String getCvv() {	
		return this.app.getCvv();
	}
	/**
	 * @param s Sets the Cvv 
	 */
	@Override
	public void setCvv(String s) {
		this.app.setCvv(s);
		
	}
	/**
 	 * return focus defualt is false
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
