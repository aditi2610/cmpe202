/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Payments Screen */
public class Payments extends Screen
{

    public Payments()
    {

    }
    public String name() {
        return "Payments" ; 
    }
    public String display() {
    	String value = super.display();
    	value = "Find Store\n Enable Payments";
    	value += "\n";
    	return value;
    }

}
