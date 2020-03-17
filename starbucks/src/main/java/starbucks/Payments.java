/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Payments Screen */
public class Payments extends Screen
{

    public Payments()
    {
    	decorator = new LeftIndentationDecorator();
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
    }
    /**
	 * @return Displays the content of the screen.
	 */
    public String display() {
    	StringBuffer out = new StringBuffer("");
    	StringBuffer value = new StringBuffer(super.display());
    	out .append("Find Store\nEnable Payments");
    	out.append("\n");
    	this.decorator.setScreenContents(out.toString());
        value.append(this.decorator.display());
        return this.decorator.displayScreen(value); 
    }

}
