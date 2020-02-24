/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

import java.util.ArrayList;

import starbucks.Device.ORIENTATION_MODE;

/**
 * Base Class for Screens.
 * 
 * Provides Common Functionality
 * For Setting Up the Composite and 
 * Chain of Responsibility Patterns.
 * 
 */
public class Screen implements IScreen, IDisplayComponent
{
	/** Display Components */
	private ArrayList<IDisplayComponent> components = new ArrayList<IDisplayComponent>() ;

	/** Front of Event Chain */
	private ITouchEventHandler chain ;
	private Device d;

	/** Constructor */
	public Screen()
	{
		d = Device.getInstance();
	}

	/**
	 * Send Touch Events to the Chain
	 * @param x Touch X Coord.
	 * @param y Touch Y Coord.
	 */
	public void touch(int x, int y) {
		if(chain != null)
			chain.touch(x, y) ;
	}

	/** Next Screen - Not Used */
	public void next() {	
	}

	/** Previous Screen - Not Used */
	public void prev()  {
	}

	/**
	 * Set Next Screen - Not Used 
	 * @param s Next Screen Object
	 * @param n Next Screen Label
	 */
	public void setNext(IScreen s, String n )  {
		// add code here
	}

	/**
	 * Send Previous Screen - Not Used
	 * @param s Previous Screen Object
	 * @param n Previous Screen Label
	 */
	public void setPrev(IScreen s, String n )  {
		// add code here
	}    

	/**
	 * Add Display Component to Screen
	 * @param c Display Component
	 */
	public void addSubComponent( IDisplayComponent c )
	{
		components.add( c ) ;
		if (components.size() == 1 )
		{
			chain = (ITouchEventHandler) c ;
		}
		else
		{
			ITouchEventHandler prev = (ITouchEventHandler) components.get(components.size()-2) ;
			prev.setNext( (ITouchEventHandler) c ) ;
		}
	}

	/**
	 * Get Display Contents
	 * @return Display Contents
	 */
	public String display() { 
		StringBuffer value = new StringBuffer("") ;
		for (IDisplayComponent c : components )
		{
			System.err.println( "Screen: " + c.getClass().getName() ) ;
			value.append(c.display());
			value .append("\n");
		}
		return value.toString() ; 
	}

	/**
	 * Get Class Name of Current Screen
	 * @return Class Name of Current Screen
	 */
	public String name() {
		return (this.getClass().getName()).split("\\.")[1] ; 
	}
	
	/**
	 * formats the String for display in portrait mode
	 * @param str Takes the string input.. the contents of the screen
	 * @return a String for classes which supports Portrait view
	 */
	public String formatSpacing(String str){
		StringBuffer value= new StringBuffer("");
		int width =14;
		if (str == null || str.length()==0) {
			return "";
		}
		String lines[] = str.split("\\r?\\n");
		for(String line: lines) {
			if (line == "\n")
				value.append("\n");
			else {
				int w = line.length();
				int space =(width-w)/2;
				value.append(padSpaces(space+1));
				value.append(line);
				value.append("\n");
			}
		}
		return value.toString() ;
	}
	
	/**
	 * 
	 * @param str Takes the string input.. the contents of the screen
	 * @return a String for classes which supports landscape view
	 */
	public String formatSpacingLandscapeSupported(String str){
		StringBuffer value= new StringBuffer();
		int width =14;
		if(d.getDeviceOrientation() == ORIENTATION_MODE.LANDSCAPE)
		 width = 31;
		if (str == null || str.isEmpty()) {
			return "";
		}
		String lines[] = str.split("\\r?\\n");
		for(String line: lines) {
			if (line == "\n")
				value.append("\n");
			else {
				int w = line.length();
				int space = (width-w)/2;
				value.append(padSpaces(space+1));
				value.append(line);
				value.append("\n");
			}
		}
		return value.toString() ;
	}
	/**
	 * 
	 * @param num , takes the number of spaces to be padded to the string
	 * @return a string with spaces added
	 */
	private String padSpaces(int num) {
		StringBuffer spaces = new StringBuffer("") ;
		for ( int i = 0; i<num; i++ )
			spaces .append(" ") ;           
		return spaces.toString() ;     
	} 

}
