/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Pin Screen */
public class PinScreen extends Screen
{
    
	Device d;
	/**
	 * Default constructor for the class
	 */
    public PinScreen()
    {
    	d= Device.getInstance();
    }
    /**
     * returns the name of the screen
     */
    public String name() {
        return "" ; 
    } 
    /**
	 * Displays the content of the screen.
	 */
    public String display() { 
       return  super.display() ;
    }
}
