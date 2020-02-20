/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Pin Screen */
public class PinScreen extends Screen
{
    
	Device d;
    public PinScreen()
    {
    	d= Device.getInstance();
    }
    public String name() {
        return "" ; 
    } 
    
    public String display() { 
        String value = super.display() ;
        return value ; 
    }
}
