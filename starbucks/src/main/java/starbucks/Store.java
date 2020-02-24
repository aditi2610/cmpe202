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
     * Gives the name to the screen
     */
    public String name(){
        return "Find Store" ; 
    }
    /**
     * Displays the contents of the screen
     */
    public String display(){
    	StringBuffer value = new StringBuffer(super.display());
    	int[] lines= {9,3,7,6,2,11,2};
    	for(int line: lines) {
    		createLine(line, value);
    	}
//    	for(int i =0; i<9; i++) {
//    		value .append( " ");
//    	}
//    	value .append( "X\n");
//    	for(int i=0; i<3; i++) {
//    		value.append(" ");
//    	}
//    	value .append("X\n");
//    	for(int i=0;i<7; i++) {
//    		value .append(" ");
//    	}
//    	value .append( "X\n");
//    	for(int i=0;i<6; i++) {
//    		value .append(" ");
//    	}
//    	value .append( "X\n");
//    	for(int i=0;i<2; i++) {
//    		value .append(" ");
//    	}
//    	value .append( "X\n");
//    	for(int i=0;i<11; i++) {
//    		value .append(" ");
//    	}
//    	value .append( "X\n");
//    	for(int i=0;i<2; i++) {
//    		value .append(" ");
//    	}
//    	value .append( "X"); 
//    	value .append( "\n");
    	return value.toString();
    }
	private void createLine(int line, StringBuffer value) {
		for(int i =0; i<line; i++) {
			value.append(" ");
		}
		value .append( "X\n");
		
	}

}
