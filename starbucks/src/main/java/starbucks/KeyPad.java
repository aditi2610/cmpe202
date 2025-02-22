/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

import java.util.ArrayList;

/** Key Pad */
public class KeyPad implements ITouchEventHandler, IDisplayComponent, IKeyPadSubject
{
    ITouchEventHandler nextHandler ;
    private ArrayList<IKeyPadObserver> observers ;
    int countPinDigits = 0 ;
    String lastKey = "" ;
   

    public KeyPad()
    {
        observers = new ArrayList<IKeyPadObserver>() ;
        
    }

    /**
     * Touch Event
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y) {
    	if (countPinDigits >= Device.getInstance().getPinOption()) {
    		countPinDigits = 0;
    	}
    	
		if (isKeypad(x, y)) {
			this.lastKey = getKey(x, y);
			if (x == 3 && y == 8 && countPinDigits > 0) {
				countPinDigits--;
			}

			if (y < 8 || (x == 2 && y == 8)) {
				countPinDigits++;
			}
			notifyObservers();
			return;
		}

		if (nextHandler != null)
			nextHandler.touch(x, y);
    	
//		if (y > 4 && y < 9) {
//			if (x < 4 && x > 0) {
//				this.lastKey = getKey(x, y);
//				if (x == 3 && y == 8 && countPinDigits > 0) {
//					countPinDigits--;
//				} 
//				if (y < 8 || (x == 2 && y == 8)) {
//					countPinDigits++;
//				}
//				notifyObservers();
//			}
//		}
//        else
//        {
//            if ( nextHandler != null )
//                nextHandler.touch(x,y) ;
//        }
    }
    
    /**
     * Helper method to determine if a coordinate belongs to a keypad press
     * @param x coordinate x
     * @param y coordinate y
     * @return boolean if part of keypad
     */
    private boolean isKeypad(int x, int y) {
    	boolean ySatisfied = false;
    	boolean xSatisfied = false;
    	
    	if (y > 4 && y < 9) {
    		ySatisfied = true;
    	}
    	
    	if (x < 4 && x > 0) {
    		xSatisfied = true;
    	}

    	return ySatisfied && xSatisfied;
    }

    /**
     *  Get Last Key Pressed 
     * @return Lasy Key
     */
    public String lastKey() { 
        System.err.println( "Key Pressed: " + this.lastKey ) ;
        return this.lastKey ; 
    }

    /**
     * Get Key Number from (X,Y) Touch Coord's
     * @param  x [X is mapped to keys on keypad]
     * @param  y [Y mapped to keys on keypad]
     * @return   [Maps the XY coord on keyboard]
     */
    public String getKey( int x, int y )
    {
        int kx = 0, ky = 0 ;
        kx = x;
        ky = y-4 ;
        if ( kx==3 && ky ==4 )
            return "X" ;
        if ( kx==2 && ky == 4 )
            return "0" ;
        if ( kx==1 && ky ==4 )
            return " " ;
        else
            return Integer.toString(kx+3*(ky-1)) ;   
    }

    /*
    kx = 1, ky = 1  ==> 1
    kx = 1, ky = 2  ==> 4
    kx = 1, ky = 3  ==> 7

    kx = 2, ky = 1  ==> 2
    kx = 2, ky = 2  ==> 5
    kx = 2, ky = 3  ==> 8

    kx = 3, ky = 1  ==> 3
    kx = 3, ky = 2  ==> 6
    kx = 3, ky = 3  ==> 9

    n = kx + 3 * (ky-1)

    */

    /**
     * Set Next Touch Event Handler
     * @param next Event Handler
     */
    public void setNext( ITouchEventHandler next) { 
        nextHandler = next ;
    }

    /**
     * Get Key Pad Display
     * @return Key Pad View Contents
     */
    public String display() 
    {
        //return " [1] [2] [3]\n [4] [5] [6]\n [7] [8] [9]\n [_] [0] [X]"  ;
        
        StringBuffer output =  new StringBuffer("  [1] [2] [3]\n") ;
               output.append("  [4] [5] [6]\n") ;
               output.append("  [7] [8] [9]\n") ;
               output.append("  [_] [0] [X]") ;

        return output.toString() ;
    }

    /**
     * Add Sub Component (Not used)
     * @param c Display Component
     */
    public void addSubComponent( IDisplayComponent c ) 
    {
    }

    /**
     * Attach a Key Pad Observer
     * @param obj Observer
     */
    public void attach( IKeyPadObserver obj ) 
    {
        observers.add( obj ) ;
    }

    /**
     * Remove Key Pad Observer
     * @param obj Observer
     */
    public void removeObserver( IKeyPadObserver obj )
    {
        int i = observers.indexOf(obj) ;
        if ( i >= 0 )
            observers.remove(i) ;
    }

    /**
     * Notify all Observers of Update Event
     */
    public void notifyObservers( )
    {
        for (int i=0; i<observers.size(); i++)
        {
            IKeyPadObserver observer = observers.get(i) ;
            observer.keyEventUpdate( countPinDigits, lastKey ) ;
        }
    }    

}
