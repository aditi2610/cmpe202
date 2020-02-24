/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Rewards Screen */
public class Rewards extends Screen
{

    public Rewards()
    {
    	
    }
    /**
     * @return the name of the screen
     */
    public String name() 
    {
    	return "Rewards";
    }	
    
    /**
     * @return the contents of the screen
     */
    public String display() 
    {
    	StringBuffer value = new StringBuffer(super.display());
    	value .append( "Make Every");
    	value .append("\n");
    	value.append("Visit Count");
    	value .append( "\n");
    	return value.toString();
    }
}
