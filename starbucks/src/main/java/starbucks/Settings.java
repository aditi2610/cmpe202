/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** Settings Screen */
public class Settings extends Screen
{
	IApp app;
	/**
	 * Default constructor for the class
	 */
    public Settings()
    {
     this.app = (IApp)Device.getInstance() ; 
    }
    /**
     * Gives the name of the screen
     */
    public String name() {
        return "Settings" ; 
    } 
    /**
     * touch function specifies what happens 
     * when you touch at a specific coordinate
     *  on a screen
     */
    public void touch(int x, int y) {
    	if(y ==1 && (x ==1 || x==2 || x==3)) {
    		this.app.execute("AddCard");
    	}
    }
   
    /**
     * Displays the contents of the screen
     */
    public String display() { 
        StringBuffer out = new StringBuffer(super.display()) ;
        StringBuffer value = new StringBuffer();
        value .append("Add Card\n");
        value .append("Delete Card\n");
        value .append("Billing\n");
        value .append("Passcode\n\n");
        value .append("About|Terms\n");
        value .append("Help");
        out .append(super.formatSpacing(value.toString()));
 
        return out.toString() ; 
    }

   
}
