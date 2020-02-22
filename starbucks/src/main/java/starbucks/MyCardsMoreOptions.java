/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card More Options Screen */
public class MyCardsMoreOptions extends Screen
{
  
    public MyCardsMoreOptions()
    {
    }
    public String name() {
        return "My Cards" ; 
    }
    
    public String display() {
    
    	String value = super.display();
    	 value +="Refresh\nReload\nAuto Reload\nTransactions";  
    	 value += "\n";
    	return value;
    }
    
}
