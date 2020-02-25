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
	public String formatSpacing(String name,String str){
		StringBuffer value= new StringBuffer();
		StringBuffer out = new StringBuffer() ;
		int width =14;
		if (str == null || str.length()==0) {
			return "";
		}
		out .append("===============\n") ;
		int nameLen = name.length() ;
		if (nameLen < 14 ) {
			int pad = (14 - nameLen) / 2 ;
			out .append( padSpaces( pad )) ;
		}
		out .append( " " + name + "\n") ;
		out .append( "===============\n") ;
	
		//center body
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
		//end body
		int cnt1 = countLines( value.toString() ) ;
        int pad1 = (10 - cnt1) / 2;
        out .append( padLines( pad1 )) ;
        out .append( value ) ;
        int cnt2 = countLines( out.toString() ) ;
        int pad2 = 13 - cnt2 ;
        String padlines = padLines( pad2 ) ;
        out .append( padlines) ;
        out .append( "===============\n") ;
        out .append( "[A][B][C][D][E]\n" );
        dumpLines( out.toString() ) ;
        return out.toString();	
        }

	/**
	 * 
	 * @param str Takes the string input.. the contents of the screen
	 * @return a String for classes which supports landscape view
	 */
	public String formatSpacingLandscapeSupported(String name, String str){
		//StringBuffer finalOutput = new StringBuffer();
		StringBuffer value= new StringBuffer();
		StringBuffer out = new StringBuffer() ;
		int width =14;
		if(d.getDeviceOrientation() == ORIENTATION_MODE.LANDSCAPE) {
			width = 31;
			out .append("================================\n") ;
			int nameLen = name.length() ;
			if (nameLen < 31  ) {
				int pad = (31 - nameLen) / 2 ;
				out .append( padSpaces( pad ) );
			}
			out .append(  " " +name + "\n" );
			out .append( "================================\n" );
		}
		else {
			out .append("===============\n") ;
			int nameLen = name.length() ;
			if (nameLen < 14 ) {
				int pad = (14 - nameLen) / 2 ;
				out .append( padSpaces( pad )) ;
			}
			out .append( " " + name + "\n") ;
			out .append( "===============\n") ;
		}
		
		//build the center body
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
		//build the end body
		
		if(d.getDeviceOrientation() == ORIENTATION_MODE.LANDSCAPE) {
			int cnt1 = countLines( value.toString() ) ;
            int pad1 = (6 - cnt1) / 2;
            out .append( padLines( pad1 )) ;
            out .append( value ) ;
            int cnt2 = countLines( out.toString() ) ;
            int pad2 = 9 - cnt2 ;
            String padlines = padLines( pad2 ) ;
            out .append( padlines) ;
            out .append(  "================================\n") ;
            dumpLines( out.toString() ) ;
		}
		else {
			int cnt1 = countLines( value.toString() ) ;
            int pad1 = (10 - cnt1) / 2;
            out .append( padLines( pad1 )) ;
            out .append( value ) ;
            int cnt2 = countLines( out.toString() ) ;
            int pad2 = 13 - cnt2 ;
            String padlines = padLines( pad2 ) ;
            out .append( padlines) ;
            out .append( "===============\n") ;
            out .append( "[A][B][C][D][E]\n" );
            dumpLines( out.toString() ) ;
		}
		
		return out.toString() ;
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
	
	/**
     * Helper:  Count lines in a String 
     * @param  str Lines to Count
     * @return     Number of Lines Counted
     */
    private int countLines(String str){

        /*
          // this approach doesn't work in cases: "\n\n"
          String lines = str ;
          String[] split = lines.split("\r\n|\r|\n") ;
          return split.length ;
        */

        if (str == null || str.isEmpty()) {
                return 0;
            }

        int lines = 0;
        int pos = 0;
        while ((pos = str.indexOf("\n", pos) + 1) != 0) {
                lines++;
        }

        return lines ;
    }
    /** 
     * Helper:  Pad lines for a Screen 
     * @param  num Lines to Padd
     * @return     Padded Lines
     */
    private String padLines(int num) {
        StringBuffer lines = new StringBuffer() ;
        for ( int i = 0; i<num; i++ ) {
            System.err.print(".") ;
            lines .append( "\n") ;
        }
        System.err.println("") ;
        return lines.toString() ;
    }
    
    /**
     * Helper Debug Dump to STDERR
     * @param str Lines to print
     */
    private void dumpLines(String str) {
          String[] lines = str.split("\r\n|\r|\n");
          for ( int i = 0; i<lines.length; i++ ) {
            System.err.println( i + ": " + lines[i] ) ;
          }
    }

    
   

}
