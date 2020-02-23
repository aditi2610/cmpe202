package starbucks;

public class Passcode6 implements ITouchEventHandler, IDisplayComponent, IKeyPadObserver{


    ITouchEventHandler nextHandler ;
    Device d;
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
            //System.err.println( "Passcode Touched at 6Passcode: (" + x + ", " + y + ")" ) ; 
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
     * Display "Echo Feedback" on Pins entered so far
     * @return Passcode String for Display
     */
    public String display() 
    {
    	d = Device.getInstance();
        String value = "" ;
        switch ( count )
        {
            case 0: value = "  _ _ _ _ _ _" ; break ;
            case 1: value = "  * _ _ _ _ _" ; break ;
            case 2: value = "  * * _ _ _ _" ; break ;
            case 3: value = "  * * * _ _ _" ; break ;
            case 4: value = "  * * * * _ _" ; break ;
            case 5: value = "  * * * * * _" ; break ;
            case 6: 
            	if  (d.isAuthenticated().equals("true")) {
            		value = "  	* * * * * *" ; 
            		break ;
            	}
            	else {
            			value += "  Invalid Pin\n\n";
            			value += "  _ _ _ _ _ _" ;			
            			break;
            	}
        }
         return value  ;
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
        //System.err.println( "Key: " + key ) ;
        count = c ;
    }

}
