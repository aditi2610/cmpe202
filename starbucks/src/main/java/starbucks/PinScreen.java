/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

import java.util.ArrayList;

/** Pin Screen */
public class PinScreen extends Screen
{
    private ArrayList<IDisplayComponent> components = new ArrayList<IDisplayComponent>() ;
   Spacer sp;
    public PinScreen()
    {
    	sp = new Spacer();
    }
    public String name() {
        return "" ; 
    } 
    
    public String display() { 
        String value = "" ;
        for (IDisplayComponent c : components )
        {
            System.err.println( "Screen: " + c.getClass().getName() ) ;
            if(c.getClass().getName().equalsIgnoreCase("Spacer")) {
            	value = value+ "Invalid Pin\n";
            }
            value = value + c.display() + "\n" ;
        }
        return value ; 
    }
}
