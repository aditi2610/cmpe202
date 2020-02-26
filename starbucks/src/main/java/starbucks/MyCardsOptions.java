/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

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
//        return "My Cards" ;
		StringBuilder sb = new StringBuilder();
		String name = "My Cards";
		double width = 15.0d;
		int padding = (int) Math.ceil((width - (double)name.length())/2.0d);
		for(int i = 0; i < padding; i++) {
			sb.append(" ");
		}
		sb.append(name);
        return sb.toString();
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
    	default:
    		break;
    	}
        
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
