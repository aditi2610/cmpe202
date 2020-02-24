/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Payments Screen */
public class Payments extends Screen
{

    public Payments()
    {

    }
    
    /** 
     * @return the name of the screen
     */
    public String name() {
        return "Payments" ; 
    }
    /**
	 * @return Displays the content of the screen.
	 */
    public String display() {
    	StringBuffer value = new StringBuffer(super.display());
    	value .append("Find Store\nEnable Payments");
    	value.append("\n");
    	return value.toString();
    }

}
