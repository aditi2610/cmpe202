/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

import starbucks.Device.ORIENTATION_MODE;

/** My Card Options Screen */
public class MyCardsOptions extends Screen
{
   Device d;
   IApp app;
    public MyCardsOptions()
    {
       this.d = Device.getInstance();
       this.app =(IApp)d;
    }
    /**
	 * @return name to the screen
	 */
    public String name() {
        return "My Cards" ; 
    }
  /**
   * executes the touch event
   * @param x X Coord
   * @param y Y Coord
   */
    public void touch(int x, int y) {
    	String s = x+","+y;
    	switch(s) {
    	case "1,7":
    	case "2,7":
    	case "3,7":
    		this.app.execute("MyCardMoreOptions");
    		break;
    	}
//        if((x ==1 || x==2 || x==3) && y==7) {	  
//        	this.app.execute("MyCardMoreOptions");
//        }
        
     }
    /**
	 * Displays the content of the screen.
	 * @return displays the content of the screen
	 */
    public String display() { 
    	if(d.getDeviceOrientation() == ORIENTATION_MODE.LANDSCAPE)
    	{
    		d.setPortraitOrientation();
    	}
        StringBuffer value = new StringBuffer(super.display()) ;
        value .append("\nReload\n");
        value .append("Refresh\n");
        value .append("More Options\n");
        value .append("Cancel");  
        value .append("\n");
        return value.toString() ; 
    }
}
