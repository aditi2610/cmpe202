/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Store Screen */
public class Store extends Screen
{
	/**
	 * Default constructor for the class
	 */
    public Store()
    {
    	//decorator = new LeftIndentationDecorator();
    }
    /**
     * @return Gives the name to the screen
     */
    public String name(){
//        return "Find Store" ; 
		StringBuilder sb = new StringBuilder();
		String name = "Find Store";
		double width = 15.0d;
		int padding = (int) Math.ceil((width - (double)name.length())/2.0d);
		for(int i = 0; i < padding; i++) {
			sb.append(" ");
		}
		sb.append(name);
        return sb.toString();

    }
    
    /**
     * @return String for the screen
     */
    public String display(){
    	StringBuffer out = new StringBuffer("");
    	StringBuffer value = new StringBuffer(super.display());
    	int[] spaces= {9,3,7,6,2,11,2};
    	for(int space: spaces) {
    		createLine(space, out);
    	}	
    	
    	return value.append(out).toString();
//    	this.decorator.setScreenContents(out.toString());
//        value.append(this.decorator.display());
//        return this.decorator.displayScreen(value); 
    }
    
    /** 
     * 
     * @param space is the number of spaces for a line
     * @param value needs to be altered
     * @return line with right space of X
     */
	private void createLine(int space, StringBuffer value) {
		for(int i =0; i<space; i++) {
			value.append(" ");
		}
		value .append( "X\n");
		
	}

}
