/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card More Options Screen */
public class MyCardsMoreOptions extends Screen
{
  
    public MyCardsMoreOptions()
    {
    }
    /**
	 * gives the name to the screen
	 * @return name of the screen
	 */
    public String name() {
        return "My Cards" ; 
    }
    /**
	 * Displays the content of the screen.
	 * @return content of the screen
	 */
    public String display() {
    
    	StringBuffer value = new StringBuffer(super.display());
    	 value .append("Refresh\nReload\nAuto Reload\nTransactions");  
    	 value.append("\n");
    	return value.toString();
    }
    
}
