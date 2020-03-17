/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card Options Screen */
public class MyCardsOptions extends Screen
{
   IApp app;

    public MyCardsOptions()
    {
       this.app =(IApp)Device.getInstance();
       decorator = new LeftIndentationDecorator();
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
//    	if(d.getDeviceOrientation() == ORIENTATION_MODE.LANDSCAPE)
//    	{
//    		d.setPortraitOrientation();
//    	}

		StringBuffer out = new StringBuffer("");
        StringBuffer value = new StringBuffer(super.display()) ;
        out .append("\nReload\n");
        out .append("Refresh\n");
        out .append("More Options\n");
        out .append("Cancel");  
        out .append("\n");
    	this.decorator.setScreenContents(out.toString());
        value.append(this.decorator.display());
        return this.decorator.displayScreen(value); 
    }
    
    
}
