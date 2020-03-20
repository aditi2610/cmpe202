package starbucks;

import starbucks.Device.ORIENTATION_MODE;

/**
 * This the abstract class for Left and Center alignment!
 */
public abstract class IndentationDecorator implements IDisplayComponent, IScreen {
	IScreen screen;
	/** Constructor */
	public IndentationDecorator(IScreen screen) {
		this.screen = screen;
	}
	 
	/**
     * Return Display Component Contents
     * @return Display Component Contents
     */
	public String display() {
		return  this.screen.display();
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
    	int length = Device.getInstance().getDeviceOrientation()== ORIENTATION_MODE.LANDSCAPE? Device.landscape_screen_length: Device.portrait_screen_length;
    	System.err.println("IndentationDecorator => Screen Length is:" + length);
    	StringBuffer screen= new StringBuffer();
		int countScreenContentLines = this.countLines(value ) ;
        int countLinesBeforeScreenContent = (length - countScreenContentLines)/2;
        screen.append(this.padLines( countLinesBeforeScreenContent )) ;
        screen .append( value ) ;
        int countLinesAfterScreenContent = this.countLines( screen ) ;
        int pad2 = length - countLinesAfterScreenContent ;
        String padlines = this.padLines( pad2 ) ;
        screen .append(padlines) ;
		return screen.toString() ;
    }


}
