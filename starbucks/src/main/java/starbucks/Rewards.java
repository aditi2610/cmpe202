/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Rewards Screen */
public class Rewards extends Screen
{

    public Rewards()
    {
    	
    }
    public String name() {
    	return "Rewards";
    }	
    public String display() {
    	String value = super.display();
    	value += "Make Every";
    	value +="\n";
    	value+= "Visit Count";
    	return value;
    }
}
