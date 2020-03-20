/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card More Options Screen */
public class MyCardsMoreOptions extends Screen
{
  
    public MyCardsMoreOptions()
    {
//    	decorator = new LeftIndentationDecorator();
    }
    
    /**
	 * gives the name to the screen
	 * @return name of the screen
	 */
    public String name() {
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
	 * Displays the content of the screen.
	 * @return content of the screen
	 */
    public String display() {
    	StringBuffer out = new StringBuffer("");
        StringBuffer value = new StringBuffer(super.display()) ;
    	 out .append("Refresh\nReload\nAuto Reload\nTransactions");  
    	 out.append("\n");
    	 value.append(out);
    	 return value.toString();
//    	 this.decorator.setScreenContents(out.toString());
//         value.append(this.decorator.display());
//         return this.decorator.displayScreen(value); 
    }
    
}
