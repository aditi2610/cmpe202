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
		StringBuilder sb = new StringBuilder();
		String name = "Payments";
		double width = 15.0d;
		int padding = (int) Math.ceil((width - (double)name.length())/2.0d);
		for(int i = 0; i < padding; i++) {
			sb.append(" ");
		}
		sb.append(name);
        return sb.toString();
//        return "Payments" ; 
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
