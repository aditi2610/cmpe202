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

    }
    /**
     * @return Gives the name to the screen
     */
    public String name(){
        return "Find Store" ; 
    }
    
    /**
     * @return String for the screen
     */
    public String display(){
    	StringBuffer value = new StringBuffer(super.display());
    	int[] spaces= {9,3,7,6,2,11,2};
    	for(int space: spaces) {
    		createLine(space, value);
    	}
    	return value.toString();
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
