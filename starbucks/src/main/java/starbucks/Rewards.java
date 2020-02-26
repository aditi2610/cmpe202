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
		StringBuilder sb = new StringBuilder();
		String name = "Rewards";
		double width = 15.0d;
		int padding = (int) Math.ceil((width - (double)name.length())/2.0d);
		for(int i = 0; i < padding; i++) {
			sb.append(" ");
		}
		sb.append(name);
        return sb.toString();

//    	return "Rewards";
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
