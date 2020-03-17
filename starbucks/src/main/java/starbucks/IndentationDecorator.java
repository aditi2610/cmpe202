package starbucks;

/**
 * This the abstract class for Left and Center alignment!
 */
public abstract class IndentationDecorator implements IDisplayComponent {
	String screenContents;
	int width = Device.portrait_screen_width-1;
	int length = Device.portrait_screen_length;
	 
	/**
     * Return Display Component Contents
     * @return Display Component Contents
     */
	public abstract String display(); 
	
	public void setScreenContents(String s) {
		this.screenContents = s;
	}
	
	public void setWidth(int w) {
		this.width= w;
	}
	
	public void setLength(int l) {
		this.length= l;
	}
	
	/**
	 * 
	 * @param num , takes the number of spaces to be padded to the string
	 * @return a string with spaces added
	 */
	protected String padSpaces(int num) {
		StringBuffer spaces = new StringBuffer("") ;
		for ( int i = 0; i<num; i++ )
			spaces .append(" ") ;           
		return spaces.toString() ;     
	} 
	/**
     * Add A Child Component
     * @param c Child Component
     */
	@Override
	public void addSubComponent(IDisplayComponent c) {
		
	}
	
	/**
     * Helper:  Count lines in a String 
     * @param  str Lines to Count
     * @return     Number of Lines Counted
     */
    protected int countLines(StringBuffer str){
        /*
          // this approach doesn't work in cases: "\n\n"
          String lines = str ;
          String[] split = lines.split("\r\n|\r|\n") ;
          return split.length ;
        */

        if (str == null || str.length() == 0) {
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
    protected String padLines(int num) {
        StringBuffer lines = new StringBuffer() ;
        for ( int i = 0; i<num; i++ ) {
            System.err.print(".") ;
            lines .append( "\n") ;
        }
        System.err.println("") ;
        return lines.toString() ;
    }
    
    /**
     * it adds line pads for the screen
     * @param value that needs to be 
     * @return    Screen
     */
    public String displayScreen(StringBuffer value ) {
    	StringBuffer screen= new StringBuffer();
		int countScreenContentLines = this.countLines(value ) ;
		System.err.println("IndentationDecorator => countScreenCountentLines "+ countScreenContentLines + " , length "+ this.length);
        int countLinesBeforeScreenContent = (this.length - countScreenContentLines)/2;
		System.err.println("IndentationDecorator => countLinesBeforeScreenContent "+ countLinesBeforeScreenContent);
        screen.append(this.padLines( countLinesBeforeScreenContent )) ;
        screen .append( value ) ;
        int countLinesAfterScreenContent = this.countLines( screen ) ;
        int pad2 = this.length - countLinesAfterScreenContent ;
        System.err.println("IndentationDecorator =>countLinesAfterScreenContent " +countLinesAfterScreenContent+  " pad2 "+ pad2);
        String padlines = this.padLines( pad2 ) ;
        screen .append(padlines) ;
		return screen.toString() ;
    }


}
