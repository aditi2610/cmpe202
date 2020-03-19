/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Passcode Screen Component */
public class Passcode implements ITouchEventHandler, IDisplayComponent, IKeyPadObserver
{
    ITouchEventHandler nextHandler ;

    private int count = 0;
    /**
     * Touch Event Ignored by Passcode
     * @param x Touch X
     * @param y Touch Y
     */
    public void touch(int x, int y) 
    {
        if ( y==2 )
        {
            System.err.println( "Passcode Touched at (" + x + ", " + y + ")" ) ; 
        }
        else
        {
            if ( nextHandler != null )
                nextHandler.touch(x,y) ;
        }
    }
    
    /**
     * Set Next Touch Handler
     * @param next Touch Event Handler
     */
    public void setNext( ITouchEventHandler next) 
    { 
        nextHandler = next ;
    }
    
    
    /**
     * Display "Echo Feedback" on Pins enterred so far
     * @return Passcode String for Display
     */
    public String display() 
    {
        String value = "" ;
        
       if(count == 0) {
    	   value = "  [_][_][_][_]" ;
       }
       if(count ==1) {
    	   value = "  [*][_][_][_]" ;
       }
       if(count ==2) {
    	   value = "  [*][*][_][_]" ;
       }
       if(count ==3) {
    	   value = "  [*][*][*][_]" ;
       }
       if(count ==4) {
    	   value = authenticatPin(value);
       }
//        switch ( count )
//        {
//            case 0: value = "  [_][_][_][_]" ; break ;
//            case 1: value = "  [*][_][_][_]" ; break ;
//            case 2: value = "  [*][*][_][_]" ; break ;
//            case 3: value = "  [*][*][*][_]" ; break ;
//            case 4: value = authenticatPin(value);
//            	break ;
//            default :
//            	break;
//        }
         return value  ;
    }
    /**
     * 
     * authenticates the  value and appends the string
     * @param value to be authenticated
     * @return String
     */
	private String authenticatPin(String value) {
		if  (Device.getInstance().isAuthenticated().equals("true")) {
			value = "  [*][*][*][*]" ; 
		
		}
		else {
			value += "  Invalid Pin\n\n";
			value += "  [_][_][_][_]" ;
		
		}
		return value;
	}
    
    /**
     * Add Sub Component (Not used)
     * @param c Sub Component to Add
     */
    public void addSubComponent( IDisplayComponent c ) 
    {
        
    }   
    
    /**
     * Key Event Update
     * @param c   Count of Keys So Far
     * @param key Last key Pressed
     */
    public void keyEventUpdate( int c, String key ) 
    {
        System.err.println( "Key: " + key ) ;
        count = c ;
    }
}
